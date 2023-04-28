package com.example.loanapplication.service;

import com.example.loanapplication.model.MonthlySummary;
import com.example.loanapplication.model.DecisionEngineResponse;
import com.example.loanapplication.model.LoanApplication;

import java.util.List;

public interface LoanApplicationService {

    /**
     * Initiate a new loan application
     *
     * @param request the LoanApplicationRequest object containing the details of the loan application
     * @return the LoanApplicationResponse object with the status and message of the initiated loan application
     */
  //  LoanApplicationResponse initiateLoanApplication(LoanApplicationRequest request);

    /**
     * Retrieve a loan application by its ID
     *
     * @param id the ID of the loan application to retrieve
     * @return the LoanApplication object with the specified ID
     */
    List<MonthlySummary> getBalanceSheetById(Long id) throws Exception;


    /**
     * Update the status of a loan application
     *
     * @param loanApplication the ID of the loan application to update
     * @return the updated LoanApplication object
     */
    DecisionEngineResponse submitLoanApplication(LoanApplication loanApplication);

}
