package com.example.simulate.decision.service;

import com.example.loanapplication.model.DecisionEngineRequest;
import com.simulate.decision.exception.DecisionServiceException;

public interface DecisionService {
    /**
     * Evaluates the loan application and returns a pre-assessment value between 0 and 100.
     * If the pre-assessment value is 100, it means the loan is highly likely to be approved.
     * If the pre-assessment value is 0, it means the loan is highly likely to be rejected.
     * @param decisionEngineRequest Balance sheet of the business
     * @throws DecisionServiceException if an error occurs while evaluating the loan application
     */
    String evaluateLoanApplication(DecisionEngineRequest decisionEngineRequest) throws DecisionServiceException;
}