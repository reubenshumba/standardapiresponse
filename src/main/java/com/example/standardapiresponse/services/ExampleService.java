package com.example.standardapiresponse.services;

import com.example.standardapiresponse.exceptions.ApplicationException;
import com.example.standardapiresponse.models.User;
import com.example.standardapiresponse.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {

    public Response<List<User>> getUsers(){

        //Do your business logic or get data from Repository
        User helloWorld = new User("Hello World");
        User sample = new User("Sample of ");
        User standardized = new User("standardized");
        User responses = new User("responses");
        List<User> users = List.of(helloWorld, sample, standardized, responses);

       // =============Sample for Exception throwing response via exception =======================/*
       /**
         // sample of throwing exceptions
         // Please note that this response will be handled by global exceptions
        if(users.isEmpty()){
          throw new ApplicationException(HttpStatus.NOT_FOUND,"No user found", null);
        }
        */

       // =============Sample for failed response =======================
       /**
         Sample of return a failed response
        return  Response.failedResponse(HttpStatus.NOT_FOUND.value(),"No user found", null);
        */

        return  Response.successfulResponse("Successful", users);
    }
}
