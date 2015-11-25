package com.globanttest.money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globanttest.domain.events.AccountEvent;
import com.globanttest.domain.events.AccountEventType;
import com.globanttest.money.domain.MoneyTransfer;
import com.globanttest.money.domain.repositories.AccountEventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MsbApplication.class)
@WebIntegrationTest("server.port:8081")
public class MoneyTransferControllerIntegrationTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private RestTemplate restTemplate = new TestRestTemplate();
    
    @Mock
    private AccountEventRepository accountEventRepository;
  
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    	final MoneyTransfer moneyTransfer = this.context.getBean(MoneyTransfer.class);
    	ReflectionTestUtils.setField(moneyTransfer, "accountEventRepository", accountEventRepository);
    	
    }
    
    @Test
    public void transferAccounts() throws Exception {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", BigDecimal.valueOf(10));
        requestBody.put("fromAccount", 1L);
        requestBody.put("toAccount", 2L);

       
        
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8081/transfer",HttpMethod.POST,
                        httpEntity,String.class
                       );

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
        AccountEvent acountEventdebit = new AccountEvent(new BigDecimal(-10), 1L, AccountEventType.DEBIT);
        AccountEvent acountEventcredit = new AccountEvent(new BigDecimal(10), 2L, AccountEventType.CREDIT);
        
        Mockito.verify(accountEventRepository).persist(acountEventdebit);
        Mockito.verify(accountEventRepository).persist(acountEventcredit);
        
    }
}
