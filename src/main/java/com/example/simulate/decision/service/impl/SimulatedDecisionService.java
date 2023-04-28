package com.example.simulate.decision.service.impl;

import com.example.loanapplication.model.DecisionEngineRequest;
import com.example.simulate.decision.service.DecisionService;
import com.simulate.decision.exception.DecisionServiceException;

import org.springframework.stereotype.Service;

@Service
public class SimulatedDecisionService implements DecisionService {
    @Override
    public String evaluateLoanApplication(DecisionEngineRequest decisionEngineRequest) throws DecisionServiceException {
        return "Success";
    }
}
