package com.accelerate.web.service;


import com.accelerate.web.service.cinode.CinodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CinodeServiceTest {

    @Autowired
    CinodeService service;

    private final static String input = "{\"Version\":\"v1\",\"WebhookId\":\"66e28435-46ce-ed11-abf5-02bba7c77742\",\"Meta\":{\"Id\":\"6771\",\"Object\":\"PublicAnnouncement\",\"Action\":\"Updated\",\"EventChanges\":[\"Role\"]},\"Payload\":{\"RequestId\":6770,\"DeadlineDate\":\"\",\"Title\":\"Projektledare\",\"Description\":\"Fiskateamavskickligaocherfarnasystemutvecklaresomihuvudsakarbetarmed\\nvidareutvecklingavSCB:sgenerellaIT-plattformförstatistik-ochregisterproduktion.\\nDeIT-komponentersomteametfrämstfokuserarpåunder2023utgörstödfördatainsamling,t.ex.\\nviakontraktsbaseradeautomatiseradeindataleveranser,hämtningavdatafrånexternaapi:erochIT-\\nstödförmetadatahanteringochdokumentation.\\nTeametansvararävenförförvaltningavSCB:ssystemförintervjuinsamlingsamtintegrationmot\\nwebinsamlingsplattformen.SomsystemutvecklarepåkonsultbasisdeltardupålikavillkorsomSCB-\\nanställdaiteamet,dvsmedfokusisammautsträckningsomövrigaiteametpånyutveckling,\\nförvaltningochivissafallävenkvalificeradsupport(om1:alinjenssupportintekanhanteraett\\närende).\\nTeametkanävenhasomuppgiftattagerahandledareåtdeltidsanställdastudentersom\\nunderstödjerteametivissauppgifter.\\n\\nUtbildning:Systemvetenskapligtprogramellermotsvarandeochgärnamedkompletterande\\ncertifieringt.ex.inomt.ex.leaddeveloperområdet.\\n\\nMångårigarbetslivserfarenhetsomsystemutvecklaresamtävenerfarenhetävenavolikaledarroller\\ninomsystemutvecklingsomt.ex.leaddeveloperochscrummaster.\\n\\nMycketgodkunskapoerfarenhetav:\\nIT-utvecklingiMicrosoftmiljösåsom.NET.\\nSystemutvecklingiC#•\\nRamverksomMVC,AngularochReact•\\nBootstrap•\\nAzureDevOps•\\nWeb-Api•\\nArbeteiSQLServersamtversionshanteringt.ex.med.GITs\\nAllamedlemmariteamethartätkontaktmedbeställandeprojekt,kravanlytiker,it-arkitekter,\\ntestledareochspecialistanvändareavdesystemsomutvecklas.\",\"AnnouncerCompanyName\":\"ForefrontConsultingGroupAB\",\"IsEndCustomerAssignment\":false,\"AllowRemote\":true,\"Price\":null,\"AnnouncedDate\":\"2023-04-23T15:28:04.828833\",\"StartDate\":\"\",\"EndDate\":\"\",\"MarketURL\":\"https://cinode.market/requests/6768\",\"Skills\":[{\"KeywordId\":38050,\"Name\":\".NET\",\"Description\":\".NET\",\"IsMandatory\":false,\"Level\":3}],\"Currency\":{\"Id\":1,\"CurrencyCode\":\"SEK\"},\"Location\":{\"DisplayName\":\"\",\"City\":\"\",\"Country\":\"Sverige\"},\"ContractType\":{\"ContractTypeId\":0,\"ContractTypeTitle\":\"Hourly\"},\"ExtentType\":{\"ExtentTypeId\":0,\"ExtentTypeTitle\":\"Percent\"}}}";


    @Test
    void requestIsCreatedForTheFirstTime() throws JsonProcessingException {


        String response = service.handleRequestFromCinode(input);
        System.out.println(response);
    }

}
