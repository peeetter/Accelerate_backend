package com.accelerate.web.controller;

import com.accelerate.web.dto.AssignmentResponse;
import com.accelerate.web.jpa.Assignment;
import com.accelerate.web.jpa.AssignmentRepository;
import com.accelerate.web.mapper.WebhookJsonMapper;
import com.accelerate.web.service.AssignmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.*;


@RestController
public class WebHookController {

    @Autowired
    WebhookJsonMapper webhookJsonMapper;

    @Autowired
    AssignmentRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AssignmentService assignmentService;


    @PostMapping(path="/call")
    public ResponseEntity<String> print(@RequestBody String requestBody) throws JsonProcessingException {
        System.out.println("#### Webhooks: ####" + requestBody);

        String webHookInJson = requestBody;

        com.accelerate.web.dto.Assignment assignment1 = webhookJsonMapper.mapWebhookToJson(webHookInJson);
        Assignment assignmentEntity = convertToEntity(assignment1);

        assignmentEntity.setSurrogateId(assignment1.getMeta().getCinodeId());
        assignmentEntity.setAction(assignment1.getMeta().getAction());
        assignmentEntity.setDeadlineDate(assignment1.getPayload().getDeadlineDate());
        assignmentEntity.setTitle(assignment1.getPayload().getTitle());
        assignmentEntity.setDescription(assignment1.getPayload().getDescription());
        assignmentEntity.setAllowRemote(assignment1.getPayload().isAllowRemote());
        assignmentEntity.setStartDate(assignment1.getPayload().getStartDate());
        assignmentEntity.setEndDate(assignment1.getPayload().getEndDate());
        assignmentEntity.setCity(assignment1.getPayload().getLocation().getCity());
        assignmentEntity.setDisplayName(assignment1.getPayload().getLocation().getDisplayName());


        if (assignment1.getMeta().getAction() == "Created") {
            Assignment createdEntity = repository.save(assignmentEntity);
            System.out.println("Created with: " + createdEntity);
        } else if (assignment1.getMeta().getAction() == "Updated") {
            Assignment createdEntity = repository.save(assignmentEntity);
            System.out.println("Upadated " + createdEntity);
        } else if (assignment1.getMeta().getAction() == "Deleted") {
            repository.delete(assignmentEntity);
        }

        // String newObject = "{\"Version\":\"v1\",\"WebhookId\":\"ca3c5432-5ecd-ed11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"6744\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":6744,\"DeadlineDate\":\"2023-05-01T00:00:00\",\"Title\":\"Systemutvecklare\",\"Description\":\"Ingå i ett team av skickliga och erfarna systemutvecklare som i huvudsak arbetar med\\nvidareutveckling av SCB:s generella IT-plattform för statistik- och registerproduktion.\\nDe IT-komponenter som teamet främst fokuserar på under 2023 utgör stöd för datainsamling, t.ex.\\nvia kontraktsbaserade automatiserade indataleveranser, hämtning av data från externa api:er och IT-\\nstöd för metadatahantering och dokumentation.\\nTeamet ansvarar även för förvaltning av SCB:s system för intervjuinsamling samt integration mot\\nwebinsamlingsplattformen. Som systemutvecklare på konsultbasis deltar du på lika villkor som SCB-\\nanställda i teamet, dvs med fokus i samma utsträckning som övriga i teamet på nyutveckling,\\nförvaltning och i vissa fall även kvalificerad support (om 1:a linjens support inte kan hantera ett\\närende).\\nTeamet kan även ha som uppgift att agera handledare åt deltidsanställda studenter som\\nunderstödjer teamet i vissa uppgifter.\\n\\nUtbildning: Systemvetenskapligt program eller motsvarande och gärna med kompletterande\\ncertifiering t.ex. inom t.ex. lead developer området.\\n\\nMångårig arbetslivserfarenhet som systemutvecklare samt även erfarenhet även av olika ledarroller\\ninom systemutveckling som t.ex. lead developer och scrummaster.\\n\\nMycket god kunskap o erfarenhet av:\\nIT-utveckling i Microsoftmiljö såsom .NET.\\nSystemutveckling i C#•\\nRamverk som MVC, Angular och React•\\nBootstrap•\\nAzure DevOps•\\nWeb-Api•\\nArbete i SQL Server samt versionshantering t.ex. med .GIT s\\nAlla medlemmar i teamet har tät kontakt med beställande projekt, kravanlytiker, it-arkitekter,\\ntestledare och specialistanvändare av de system som utvecklas.\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-03-28T12:07:08.8702472\",\"StartDate\":\"2023-05-01T00:00:00\",\"EndDate\":\"2024-04-30T00:00:00\",\"MarketURL\":\"https://cinode.market/requests/6744\",\"Skills\":[{\"KeywordId\":38050,\"Name\":\".NET\",\"Description\":\".NET\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Örebro, Sverige\",\"City\":\"Örebro\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}\n";
       // com.accelerate.web.dto.Assignment assignment = webhookJsonMapper.mapWebhookToJson(newObject);
       // Assignment assignmentEntity = convertToEntity(assignment);
       // System.out.println(assignmentEntity);

        return new ResponseEntity<String>(requestBody, HttpStatus.OK);
    }

    @PostMapping(path="/saveToDb")
    public String saveToDB(com.accelerate.web.dto.Assignment assignment) throws JsonProcessingException {

        String newObject = "{\"Version\":\"v1\",\"WebhookId\":\"ca3c5432-5ecd-ed11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"6744\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":6744,\"DeadlineDate\":\"2023-05-01T00:00:00\",\"Title\":\"Systemutvecklare\",\"Description\":\"Ingå i ett team av skickliga och erfarna systemutvecklare som i huvudsak arbetar med\\nvidareutveckling av SCB:s generella IT-plattform för statistik- och registerproduktion.\\nDe IT-komponenter som teamet främst fokuserar på under 2023 utgör stöd för datainsamling, t.ex.\\nvia kontraktsbaserade automatiserade indataleveranser, hämtning av data från externa api:er och IT-\\nstöd för metadatahantering och dokumentation.\\nTeamet ansvarar även för förvaltning av SCB:s system för intervjuinsamling samt integration mot\\nwebinsamlingsplattformen. Som systemutvecklare på konsultbasis deltar du på lika villkor som SCB-\\nanställda i teamet, dvs med fokus i samma utsträckning som övriga i teamet på nyutveckling,\\nförvaltning och i vissa fall även kvalificerad support (om 1:a linjens support inte kan hantera ett\\närende).\\nTeamet kan även ha som uppgift att agera handledare åt deltidsanställda studenter som\\nunderstödjer teamet i vissa uppgifter.\\n\\nUtbildning: Systemvetenskapligt program eller motsvarande och gärna med kompletterande\\ncertifiering t.ex. inom t.ex. lead developer området.\\n\\nMångårig arbetslivserfarenhet som systemutvecklare samt även erfarenhet även av olika ledarroller\\ninom systemutveckling som t.ex. lead developer och scrummaster.\\n\\nMycket god kunskap o erfarenhet av:\\nIT-utveckling i Microsoftmiljö såsom .NET.\\nSystemutveckling i C#•\\nRamverk som MVC, Angular och React•\\nBootstrap•\\nAzure DevOps•\\nWeb-Api•\\nArbete i SQL Server samt versionshantering t.ex. med .GIT s\\nAlla medlemmar i teamet har tät kontakt med beställande projekt, kravanlytiker, it-arkitekter,\\ntestledare och specialistanvändare av de system som utvecklas.\",\"AnnouncerCompanyName\":\"Forefront Consulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":false,\"Price\":null,\"AnnouncedDate\":\"2023-03-28T12:07:08.8702472\",\"StartDate\":\"2023-05-01T00:00:00\",\"EndDate\":\"2024-04-30T00:00:00\",\"MarketURL\":\"https://cinode.market/requests/6744\",\"Skills\":[{\"KeywordId\":38050,\"Name\":\".NET\",\"Description\":\".NET\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"Örebro, Sverige\",\"City\":\"Örebro\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}\n";
        com.accelerate.web.dto.Assignment assignment1 = webhookJsonMapper.mapWebhookToJson(newObject);

        Assignment assignmentEntity = convertToEntity(assignment);
        assignmentEntity.setSurrogateId(assignment1.getMeta().getCinodeId());
        assignmentEntity.setAction(assignment1.getMeta().getAction());
        assignmentEntity.setDeadlineDate(assignment1.getPayload().getDeadlineDate());
        assignmentEntity.setTitle(assignment1.getPayload().getTitle());
        assignmentEntity.setDescription(assignment1.getPayload().getDescription());
        assignmentEntity.setAllowRemote(assignment1.getPayload().isAllowRemote());
        assignmentEntity.setStartDate(assignment1.getPayload().getStartDate());
        assignmentEntity.setEndDate(assignment1.getPayload().getEndDate());
        assignmentEntity.setCity(assignment1.getPayload().getLocation().getCity());
        assignmentEntity.setDisplayName(assignment1.getPayload().getLocation().getDisplayName());

        repository.save(assignmentEntity);

        System.out.println("assig.getId: " + assignmentEntity.getSurrogateId());
        System.out.println("assig.getAction: " + assignmentEntity.getAction());
        System.out.println("assig.getDeadlineDate: " + assignmentEntity.getDeadlineDate());
        System.out.println("assig.getTitle: " + assignmentEntity.getTitle());
        System.out.println("assig.getDescription: " + assignmentEntity.getDescription());
        System.out.println("assig.isAllowRemote: " + assignmentEntity.isAllowRemote());
        System.out.println("assig.getStartDate: " + assignmentEntity.getStartDate());
        System.out.println("assig.getEndDate: " + assignmentEntity.getEndDate());
        System.out.println("assig.getLocation: " + assignmentEntity.getCity());
        System.out.println("assig.getDisplayName: " + assignmentEntity.getDisplayName());
        System.out.println("assig.getDisplayName: " + assignmentEntity.getAnnouncedDate());
        return "assignment1.getMeta().getAction()";
    }

    @PostMapping(path="/testToDb")
    public String testDb(@RequestBody String requestBody) throws JsonProcessingException {

        System.out.println("Requestbody: " + requestBody);
        com.accelerate.web.dto.Assignment assignment1 = webhookJsonMapper.mapWebhookToJson(requestBody);
        System.out.println("Assignment1: " + assignment1.getPayload().isAllowRemote());
        Assignment assignmentEntity = convertToEntity(assignment1);
        assignmentEntity.setCinodeId(assignment1.getMeta().getCinodeId());
        assignmentEntity.setAction(assignment1.getMeta().getAction());
        assignmentEntity.setDeadlineDate(assignment1.getPayload().getDeadlineDate());
        assignmentEntity.setTitle(assignment1.getPayload().getTitle());
        assignmentEntity.setDescription(assignment1.getPayload().getDescription());
        assignmentEntity.setAllowRemote(assignment1.getPayload().isAllowRemote());
        assignmentEntity.setStartDate(assignment1.getPayload().getStartDate());
        assignmentEntity.setEndDate(assignment1.getPayload().getEndDate());
        assignmentEntity.setCity(assignment1.getPayload().getLocation().getCity());
        assignmentEntity.setDisplayName(assignment1.getPayload().getLocation().getDisplayName());
        assignmentEntity.setAnnouncedDate(assignment1.getPayload().getAnnouncedDate());

        System.out.println("Allow remote: " + assignmentEntity.isAllowRemote());
        String returnString = assignmentService.decideDbMethodBasedOnAction(assignmentEntity);
        System.out.println(returnString);

        return returnString;
    }

    @GetMapping(path="/getAssignment/{id}")
    @CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    public AssignmentResponse getAssignmentById(@PathVariable("id") Long id) {
        AssignmentResponse assignmentResponse = assignmentService.getAssignmentResponseById(id);
        return assignmentResponse;
    }

    @GetMapping(path="/getAllAssignments")
    @CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    public List<AssignmentResponse> getAllAssignments() {
        ModelMapper mapper = new ModelMapper();
        List<Assignment> assignments = new ArrayList<>();
        repository.findAll().forEach(assignments::add);

        List<AssignmentResponse> assignmentsOfDtos = mapper.map(assignments, new TypeToken<List<AssignmentResponse>>(){}.getType());

        assignmentsOfDtos.stream().forEach(s -> System.out.println("cinodeId: " + s.getCinodeId() + ", " + "description: " + s.getDescription() + ", " + "datelineDate: " + s.getDeadlineDate() + ", " + "title: " + s.getTitle() + ", " + "allowRemote: " + s.isAllowRemote() + ", " + "startDate: " + s.getStartDate() + ", " + "endDate: " + s.getEndDate() + ", " + "city: " + s.getCity() + ", " + "displayName: " + s.getDisplayName()));
        return compareCreatedDates(assignmentsOfDtos);
    }

    private Assignment convertToEntity(com.accelerate.web.dto.Assignment assignmentDto) {
        Assignment assignment = modelMapper.map(assignmentDto, Assignment.class);
        return assignment;
    }



    private List<AssignmentResponse>  compareCreatedDates(List<AssignmentResponse> assignmentsOfDtos) {

        Collections.sort(assignmentsOfDtos, new Comparator<AssignmentResponse>() {
            @Override
            public int compare(AssignmentResponse o1, AssignmentResponse o2) {
                return o1.getAnnouncedDate().compareTo(o2.getAnnouncedDate());
            }
        });
        return assignmentsOfDtos;
    }
}
