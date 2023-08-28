package com.accelerate.web.service.cinode;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CinodeService {

    String handleRequestFromCinode(String requestBody) throws JsonProcessingException;
}
