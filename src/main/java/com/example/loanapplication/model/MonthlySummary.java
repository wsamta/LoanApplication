package com.example.loanapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySummary {
    private int year;
    private int month;
    private double profitOrLoss;
    private double assetsValue;

}