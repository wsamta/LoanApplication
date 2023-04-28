package com.example.loanapplication.controller;

import com.example.loanapplication.model.MonthlySummary;
import com.example.loanapplication.model.DecisionEngineResponse;
import com.example.loanapplication.model.LoanApplication;
import com.example.loanapplication.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.loanapplication.config.WebResource.API_BASE_URL;


@Slf4j
@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping(value = API_BASE_URL + "/loan-applications", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

/*   @PostMapping("")
    public LoanApplicationResponse initiateLoanApplication(@RequestBody LoanApplicationRequest request) {
        log.info("Initiating new loan application: {}", request);
        // Perform loan application processing and generate response
        LoanApplicationResponse response = new LoanApplicationResponse();
        response.setStatus("pending");
        response.setMessage("Loan application received");
        return response;
    }*/


    @GetMapping("/{id}")
    public List<MonthlySummary> getBalanceSheetById(@PathVariable Long id) throws Exception {
        log.info("Getting Balance Sheet by id: {}", id);
        return loanApplicationService.getBalanceSheetById(id);
    }

    @PostMapping("/submit")
    public DecisionEngineResponse submitLoanApplication(@RequestBody LoanApplication loanApplication) throws Exception {
        loanApplication.setLoanAppId(123);
        log.info("Submitting Loan Application for id: {}", loanApplication.getLoanAppId());
        try {
            return loanApplicationService.submitLoanApplication(loanApplication);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }


}
