package com.example.simulate.accounting.service;

import com.example.loanapplication.model.MonthlySummary;
import com.simulate.accounting.exception.AccountingServiceException;

import java.util.List;

public interface AccountingService {
    /**
     * Returns the balance sheet for the given business ID
     * @param businessId ID of the business
     * @return Balance sheet as a string
     * @throws AccountingServiceException if an error occurs while fetching the balance sheet
     */
    List<MonthlySummary> getBalanceSheet(String businessId) throws AccountingServiceException;
}