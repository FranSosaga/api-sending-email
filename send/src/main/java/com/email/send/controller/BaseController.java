package com.email.send.controller;

import com.email.send.dto.GenericResponseDTO;
import com.email.send.exceptions.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    private static final Logger LOG = LogManager.getLogger(BaseController.class);

    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    protected ResponseEntity<String> handleException(ApiException exception) {

        switch (exception.getErrorCode()) {
            case NOT_FOUND -> {
                return new ResponseEntity<>(GSON.toJson(exception), HttpStatus.NOT_FOUND);
            }
            case INTERNAL_ERROR -> {
                return new ResponseEntity<>(GSON.toJson(exception), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            case MAPPING_ERROR -> {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            case BAD_PARAMETER -> {
                return new ResponseEntity<>(GSON.toJson(exception), HttpStatus.BAD_REQUEST);
            }
            case NOT_AUTHORIZED -> {
                return new ResponseEntity<>(GSON.toJson(exception), HttpStatus.UNAUTHORIZED);
            }
            case UNPROCESSABLE -> {
                return new ResponseEntity<>(GSON.toJson(exception), HttpStatus.UNPROCESSABLE_ENTITY);
            }
            default -> {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    protected String mapToJson(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOG.error("Error mapping object to json", e);
            return null;
        }
    }

    public ResponseEntity<String> handleResponse(ApiException e) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new ResponseEntity<>(mapper.writeValueAsString(new GenericResponseDTO(e.getCode(), e.getMessage())), e.getStatus());
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

