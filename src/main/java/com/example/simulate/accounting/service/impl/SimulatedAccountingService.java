package com.example.simulate.accounting.service.impl;

import com.example.loanapplication.model.MonthlySummary;
import com.example.simulate.accounting.service.AccountingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulate.accounting.exception.AccountingServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class SimulatedAccountingService implements AccountingService {

    @Override
    //convert to
    //1. Logging
   // 2. append Success mesg with preassesment val
    //3. convert to object and string
    public List<MonthlySummary> getBalanceSheet(String businessId) {
        // Simulate fetching the balance sheet for the given business ID
        // In this case, we will always return a balance sheet with a profit
        log.info("Retrieving balance sheet for id: {}", businessId);
        List<MonthlySummary> balanceSheet=null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("static/AccountingService.json");
            balanceSheet = mapper.readValue(is,
                    new TypeReference<List<MonthlySummary>>() {
                    });
        } catch ( AccountingServiceException | IOException e) {
            throw new AccountingServiceException("SimulatedAccountingService :: getBalanceSheet :: Error getting decision from decision engine", e);
        }
        log.info("Successfully retrieved balance sheet for id: {}", businessId);
        return balanceSheet;
    }
}