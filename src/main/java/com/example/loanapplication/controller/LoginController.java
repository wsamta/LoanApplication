package com.example.loanapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.example.loanapplication.config.WebResource.API_BASE_URL;

@Slf4j
@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping(value = API_BASE_URL + "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})

public class LoginController {
    @GetMapping("/{userName}")
    public String sendForValidation(@PathVariable String userName){
        log.info("Getting Balance Sheet by id: {}", userName);
        return userName;
    }

}
