package com.mynotes.springcloud.contract.consumer;


import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringCloudContractConsumerApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(ids = "com.mynotes.spring-cloud:spring-cloud-contract-producer:+:stubs:8081", workOffline = true)
public class ConsumerTestContract {

    @Autowired
    ConsumerClient consumerClient;

    @Test
    public void clientShouldRetrunPersonForGivenID_checkFirsttName() throws Exception {

        BDDAssertions.then(this.consumerClient.getPerson(1).getFirstname()).isEqualTo("Jane");
    }
    
    @Test
    public void clientShouldRetrunPersonForGivenID_checkLastName() throws Exception {

        BDDAssertions.then(this.consumerClient.getPerson(1).getLname()).isEqualTo("Doe");
    }

}

