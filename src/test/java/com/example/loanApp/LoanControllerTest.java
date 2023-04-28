package com.example.loanApp;

import com.example.loanapplication.controller.LoanApplicationController;
import com.example.loanapplication.model.MonthlySummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanApplicationController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private LoanApplicationController loanApplicationController;

    Properties properties=new Properties();

    @Before
    public void setup() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("loanApplication.properties");
        properties.load(is);
    }
    @Test
    public void testAccountServiceShouldReturn200SuccessRequest() throws Exception {
        String END_POINT_PATH="http://localhost:8082/api/v1/loan/loan-applications/1";
        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
        ;
    }

    @Test
    public void testGetBalanceSheet() throws Exception {
        String request_url="http://localhost:8082/api/v1/loan/loan-applications/1";

        List<MonthlySummary> monthlySummaryList= Arrays.asList(new MonthlySummary(2022,12,250000.0,1234.0),new MonthlySummary(2020,11,1150.0,5789.0));
        Mockito.when(loanApplicationController.getBalanceSheetById(1L)).thenReturn(monthlySummaryList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(request_url).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedResponse="[ { \"year\": 2022, \"month\": 12, \"profitOrLoss\": 250000.0, \"assetsValue\": 1234.0 }, { \"year\": 2020, \"month\": 11, \"profitOrLoss\": 1150.0, \"assetsValue\": 5789.0 }]";
        JSONAssert.assertEquals(expectedResponse, result.getResponse()
                .getContentAsString(), false);
    }
}
