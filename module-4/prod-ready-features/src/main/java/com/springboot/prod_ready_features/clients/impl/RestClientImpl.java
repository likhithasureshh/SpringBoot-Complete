package com.springboot.prod_ready_features.clients.impl;


import com.springboot.prod_ready_features.clients.RestClientIn;
import com.springboot.prod_ready_features.dtos.EmployeeDto;
import com.springboot.prod_ready_features.exceptions.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestClientImpl implements RestClientIn {
    private final RestClient restClient;
    @Override
    public List<EmployeeDto> getAllEmployees() {
        try
        {
            ApiResponse<List<EmployeeDto>> employeeDtoApiResponse = restClient.get()
                    .uri("/employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        System.out.println(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {});
            return employeeDtoApiResponse.getData();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        try
        {
            ApiResponse<EmployeeDto> employeeDtoApiResponse = restClient.get()
                    .uri("/employees/{id}",id)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        System.out.println(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoApiResponse.getData();
        }

        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try
        {
            ApiResponse<EmployeeDto> employeeDtoApiResponse = restClient.post()
                    .uri("/employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->
                    {
                        System.out.println(res.getBody().readAllBytes());
                    })
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
                    });
            return employeeDtoApiResponse.getData();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
