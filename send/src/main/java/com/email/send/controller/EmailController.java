package com.email.send.controller;

import com.email.send.dto.GenericResponseDTO;
import com.email.send.dto.MailDTO;
import com.email.send.exceptions.ApiException;
import com.email.send.service.MailSerivce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController extends BaseController {

    private static final Logger LOG = LogManager.getLogger(EmailController.class);
    private final MailSerivce mailSerivce;

    public EmailController(MailSerivce mailSerivce) {
        this.mailSerivce = mailSerivce;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mailSerivce.sendEmail(mailDTO);
            return new ResponseEntity<>(mapper.writeValueAsString(new GenericResponseDTO("mail_sent", String.format("Email sent, to: %s, orderId: %s", mailDTO.getTo(), mailDTO.getOrderId()))), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            LOG.error("Error parsing response JSON");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ApiException e) {
            LOG.error(e.getMessage(), e);
            return handleException(e);
        } catch (Exception e) {
            LOG.error("Exception unhandled", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
