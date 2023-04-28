package com.example.loanapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecisionEngineRequest {
    private String name;
    private int yearEstablished;
    private Map<Integer, Double> profitLossSummary;
    private int preAssessment;

}