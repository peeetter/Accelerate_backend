package com.accelerate.web;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MockJsonProcessingException extends JsonProcessingException {

    public MockJsonProcessingException(String msg) {
        super(msg);
    }

    @Override
    public Object getProcessor() {
        return null;
    }
}