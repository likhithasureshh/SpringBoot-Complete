package com.springboot.prod_ready_features.clients.impl;


import com.springboot.prod_ready_features.clients.RestClientIn;
import com.springboot.prod_ready_features.dtos.EmployeeDto;
import com.springboot.prod_ready_features.exceptions.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestClientImpl implements RestClientIn {
    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(RestClientImpl.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try
        {
            log.trace("Attempting to fetch All Employees in getAllEmployees");
            ApiResponse<List<EmployeeDto>> employeeDtoApiResponse = restClient.get()
                    .uri("/employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {});
            log.trace(" Employees fetched in getAllEmployees: {}",employeeDtoApiResponse.getData());
            return employeeDtoApiResponse.getData();
        }
        catch (Exception e)
        {
            log.error("Error occured in getAllEmployees");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        try
        {
            log.trace("Attempting to fetch  Employee with id : {}  in getEmployeeById",id);
            ApiResponse<EmployeeDto> employeeDtoApiResponse = restClient.get()
                    .uri("/employees/{id}",id)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.trace(" Employee fetched in getEmployeeById: {}",employeeDtoApiResponse.getData());
            return employeeDtoApiResponse.getData();
        }

        catch(Exception e)
        {
            log.error("Error occured in getEmployeeById");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try
        {
            log.trace("Attempting to create new employee  in createNewEmployee");
            ApiResponse<EmployeeDto> employeeDtoApiResponse = restClient.post()
                    .uri("/employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
                    });
            log.trace(" Employee created in createNewEmployee: {}",employeeDtoApiResponse.getData());
            return employeeDtoApiResponse.getData();
        }
        catch(Exception e)
        {
            log.error("Error occured in createNewEmployee");
            throw new RuntimeException(e);
        }
    }
}
