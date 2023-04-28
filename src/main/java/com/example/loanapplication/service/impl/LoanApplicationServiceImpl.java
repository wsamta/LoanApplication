package com.example.loanapplication.service.impl;

import com.example.loanapplication.model.*;
import com.example.loanapplication.service.LoanApplicationService;
import com.example.simulate.accounting.service.AccountingService;

import com.example.simulate.decision.service.DecisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {


    public static final String SUCCESS_MESSAGE = " and Loan is favoured to be approved %d percent of the requested value.";

    @Autowired
    private AccountingService simulatedAccountingService;

    @Autowired
    DecisionService decisionService;

    @Override
    public List<MonthlySummary> getBalanceSheetById(Long id) throws Exception {
        try {
            log.info("Retrieving balance sheet for id: {}", id);
            List<MonthlySummary> balanceSheet = simulatedAccountingService.getBalanceSheet(id.toString());
            log.info("Successfully retrieved balance sheet for id: {}", id);
            return balanceSheet;
        } catch (Exception e) {
            log.error("Error occurred while retrieving balance sheet for id: {}", id, e);
            throw new Exception(e.getLocalizedMessage());
        }
    }


    @Override
    //add logger
    public DecisionEngineResponse submitLoanApplication(LoanApplication loanApplication) {
        log.info("Submitting Loan Application for the id: {}", loanApplication.getLoanAppId());
        BusinessDetails businessDetails = loanApplication.getBusinessDetails();
        List<MonthlySummary> monthlySummaries = loanApplication.getMonthlySummaries();
        Map<Integer, Double> profitLossSummary = getProfitOrLossSummaryByYear(monthlySummaries);
        int preAssesment = calculatePreAssessment(monthlySummaries, businessDetails.getLoanAmount());
        log.info("Pre-assessment value Loan Application is ", preAssesment);

        DecisionEngineRequest decisionEngineRequest = new DecisionEngineRequest();
        decisionEngineRequest.setName(businessDetails.getBusinessName());
        decisionEngineRequest.setYearEstablished(businessDetails.getYearEstablished());
        decisionEngineRequest.setProfitLossSummary(profitLossSummary);
        decisionEngineRequest.setPreAssessment(preAssesment);

        DecisionEngineResponse decisionEngineResponse = new DecisionEngineResponse();
        StringBuilder responseMessage = new StringBuilder(decisionService.evaluateLoanApplication(decisionEngineRequest));
        String successMessage = String.format(SUCCESS_MESSAGE, preAssesment);
        responseMessage.append(successMessage);
        decisionEngineResponse.setLoanResult(responseMessage.toString());
        log.info("Submitted Loan Application for the id: {} ", loanApplication.getLoanAppId());
        return decisionEngineResponse;
    }

    public Map<Integer, Double> getProfitOrLossSummaryByYear(List<MonthlySummary> list) {
        Map<Integer, Double> summary = new HashMap<>();
        for (MonthlySummary obj : list) {
            int year = obj.getYear();
            double profit = obj.getProfitOrLoss();
            summary.put(year, summary.getOrDefault(year, 0.0) + profit);
        }
        return summary;
    }

    public int calculatePreAssessment(List<MonthlySummary> monthlySummaries, double loanAmount) {
        int totalProfitOrLoss = 0;
        int totalAssetsValue = 0;
        int numMonths = 0;

        // Calculate total profit/loss and assets value for the last 12 months
        for (int i = 0; i < monthlySummaries.size() && numMonths < 12; i++) {
            MonthlySummary monthlySummary = monthlySummaries.get(i);
            totalProfitOrLoss += monthlySummary.getProfitOrLoss();
            totalAssetsValue += monthlySummary.getAssetsValue();
            numMonths++;
        }

        // If the business made a profit in the last 12 months, return 60% pre-assessment
        if (totalProfitOrLoss > 0) {
            return 60;
        }

        // If the average assets value across the last 12 months is greater than the loan amount, return 100% pre-assessment
        int avgAssetsValue = totalAssetsValue / numMonths;
        if (avgAssetsValue > loanAmount) {
            return 100;
        }

        // Default pre-assessment value is 20%
        return 20;
    }


}
