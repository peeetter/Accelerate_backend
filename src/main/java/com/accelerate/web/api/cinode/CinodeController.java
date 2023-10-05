package com.accelerate.web.api.cinode;

import com.accelerate.web.service.cinode.CinodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinodeController {

    @Autowired
    CinodeService cinodeService;

    private static final Logger logger = LoggerFactory.getLogger(CinodeController.class);

    @PostMapping(path = "/call")
    public ResponseEntity<String> getCinodeMarketNotification(@RequestBody @Nullable String requestBody) throws JsonProcessingException {
        logger.info("Webhook received with body: {} ", requestBody);
        String responseBody = cinodeService.handleRequestFromCinode(requestBody);
        return ResponseEntity.ok(responseBody);
    }
}