package com.example.loanapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails {
    
    private String businessName;
    private String email;
    private double loanAmount;
    private int yearEstablished;
    private String accountingProvider;

}
