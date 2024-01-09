package com.example.standardapiresponse.controllers;

import com.example.standardapiresponse.models.User;
import com.example.standardapiresponse.responses.Response;
import com.example.standardapiresponse.services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/users")
    public ResponseEntity<Response<List<User>>> getUsers(){
        //Validate your request for other end point first

        // call your service
        Response<List<User>> response = exampleService.getUsers();

        //return Response entity.
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
