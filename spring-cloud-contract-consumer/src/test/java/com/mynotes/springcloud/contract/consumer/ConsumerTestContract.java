package com.mynotes.springcloud.contract.consumer;


import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringCloudContractConsumerApplication.class)
@RunWith(SpringRunner.class)
public class ConsumerTestContract {

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.mynotes.spring-cloud", "spring-cloud-contract-producer", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8081)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Autowired
    ConsumerClient consumerClient;

    @Test
    public void clientShouldRetrunPersonForGivenID_checkFirsttName() throws Exception {

        BDDAssertions.then(this.consumerClient.getPerson(1).getFname()).isEqualTo("Jane");
    }
    
    @Test
    public void clientShouldRetrunPersonForGivenID_checkLastName() throws Exception {

        BDDAssertions.then(this.consumerClient.getPerson(1).getLname()).isEqualTo("Doe");
    }

}

