package com.lambda.controller;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DynamoService {

    private AWSCredentialsProviderChain credentialsProvider;
    private Table table;
    private DynamoDB dynamoDB;
    private static final String schedule = "schedule";


    @PostConstruct
    public void initialize(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        dynamoDB = new DynamoDB(client);
        table=dynamoDB.getTable(schedule);
    }


    public Table getTable(){
        return table;
    }
}
