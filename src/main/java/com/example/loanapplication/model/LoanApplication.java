package com.example.loanapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplication {

    Integer loanAppId;
    private BusinessDetails businessDetails;
    private List<MonthlySummary> monthlySummaries;
}