package com.accelerate.web.service;


import com.accelerate.web.service.cinode.CinodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class CinodeServiceTest {



    @Autowired
    CinodeService service;

    private final static String INPUT = "{\"Version\":\"v1\",\"WebhookId\":\"66e28435-46ce-ed11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"6771\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":6770,\"DeadlineDate\":\"\",\"Title\":\"Projektledare\",\"Description\":\"Fiskateamavskickligaocherfarnasystemutvecklaresomihuvudsakarbetarmed\\nvidareutvecklingavSCB:sgenerellaIT-plattformförstatistik-ochregisterproduktion.\\nDeIT-komponentersomteametfrämstfokuserarpåunder2023utgörstödfördatainsamling,t.ex.\\nviakontraktsbaseradeautomatiseradeindataleveranser,hämtningavdatafrånexternaapi:erochIT-\\nstödförmetadatahanteringochdokumentation.\\nTeametansvararävenförförvaltningavSCB:ssystemförintervjuinsamlingsamtintegrationmot\\nwebinsamlingsplattformen.SomsystemutvecklarepåkonsultbasisdeltardupålikavillkorsomSCB-\\nanställdaiteamet,dvsmedfokusisammautsträckningsomövrigaiteametpånyutveckling,\\nförvaltningochivissafallävenkvalificeradsupport(om1:alinjenssupportintekanhanteraett\\närende).\\nTeametkanävenhasomuppgiftattagerahandledareåtdeltidsanställdastudentersom\\nunderstödjerteametivissauppgifter.\\n\\nUtbildning:Systemvetenskapligtprogramellermotsvarandeochgärnamedkompletterande\\ncertifieringt.ex.inomt.ex.leaddeveloperområdet.\\n\\nMångårigarbetslivserfarenhetsomsystemutvecklaresamtävenerfarenhetävenavolikaledarroller\\ninomsystemutvecklingsomt.ex.leaddeveloperochscrummaster.\\n\\nMycketgodkunskapoerfarenhetav:\\nIT-utvecklingiMicrosoftmiljösåsom.NET.\\nSystemutvecklingiC#•\\nRamverksomMVC,AngularochReact•\\nBootstrap•\\nAzureDevOps•\\nWeb-Api•\\nArbeteiSQLServersamtversionshanteringt.ex.med.GITs\\nAllamedlemmariteamethartätkontaktmedbeställandeprojekt,kravanlytiker,it-arkitekter,\\ntestledareochspecialistanvändareavdesystemsomutvecklas.\",\"AnnouncerCompanyName\":\"ForefrontConsulting Group AB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":true,\"Price\":null,\"AnnouncedDate\":\"2023-04-23T15:28:04.828833\",\"StartDate\":\"\",\"EndDate\":\"\",\"MarketURL\":\"https://cinode.market/requests/6768\",\"Skills\":[{\"KeywordId\":38050,\"Name\":\".NET\",\"Description\":\".NET\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"\",\"City\":\"\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";
    private final static String INPUT_NOT_FROM_FOREFRONT = "{\"Version\":\"v1\",\"WebhookId\":\"66e28435-46ce-ed11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"6771\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Created\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":6770,\"DeadlineDate\":\"\",\"Title\":\"Projektledare\",\"Description\":\"Fiskateamavskickligaocherfarnasystemutvecklaresomihuvudsakarbetarmed\\nvidareutvecklingavSCB:sgenerellaIT-plattformförstatistik-ochregisterproduktion.\\nDeIT-komponentersomteametfrämstfokuserarpåunder2023utgörstödfördatainsamling,t.ex.\\nviakontraktsbaseradeautomatiseradeindataleveranser,hämtningavdatafrånexternaapi:erochIT-\\nstödförmetadatahanteringochdokumentation.\\nTeametansvararävenförförvaltningavSCB:ssystemförintervjuinsamlingsamtintegrationmot\\nwebinsamlingsplattformen.SomsystemutvecklarepåkonsultbasisdeltardupålikavillkorsomSCB-\\nanställdaiteamet,dvsmedfokusisammautsträckningsomövrigaiteametpånyutveckling,\\nförvaltningochivissafallävenkvalificeradsupport(om1:alinjenssupportintekanhanteraett\\närende).\\nTeametkanävenhasomuppgiftattagerahandledareåtdeltidsanställdastudentersom\\nunderstödjerteametivissauppgifter.\\n\\nUtbildning:Systemvetenskapligtprogramellermotsvarandeochgärnamedkompletterande\\ncertifieringt.ex.inomt.ex.leaddeveloperområdet.\\n\\nMångårigarbetslivserfarenhetsomsystemutvecklaresamtävenerfarenhetävenavolikaledarroller\\ninomsystemutvecklingsomt.ex.leaddeveloperochscrummaster.\\n\\nMycketgodkunskapoerfarenhetav:\\nIT-utvecklingiMicrosoftmiljösåsom.NET.\\nSystemutvecklingiC#•\\nRamverksomMVC,AngularochReact•\\nBootstrap•\\nAzureDevOps•\\nWeb-Api•\\nArbeteiSQLServersamtversionshanteringt.ex.med.GITs\\nAllamedlemmariteamethartätkontaktmedbeställandeprojekt,kravanlytiker,it-arkitekter,\\ntestledareochspecialistanvändareavdesystemsomutvecklas.\",\"AnnouncerCompanyName\":\"Castra\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":true,\"Price\":null,\"AnnouncedDate\":\"2023-04-23T15:28:04.828833\",\"StartDate\":\"\",\"EndDate\":\"\",\"MarketURL\":\"https://cinode.market/requests/6768\",\"Skills\":[{\"KeywordId\":38050,\"Name\":\".NET\",\"Description\":\".NET\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"\",\"City\":\"\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";


    @Test
    void requestIsFromForefrontAndIsCreatedForTheFirstTime() throws JsonProcessingException {

        String response = service.handleRequestFromCinode(INPUT);
        assertEquals("Created", response);
    }

    @Test
    void requestIsNotFromForefrontAndNotCreatedForTheFirstTime() throws JsonProcessingException {

        String response = service.handleRequestFromCinode(INPUT_NOT_FROM_FOREFRONT);
        System.out.println(response);
        assertNotEquals("Created", response);
    }
}