package com.module_2.SpringBoot2.services;

import com.module_2.SpringBoot2.advices.exceptions.ResourceNotFoundException;
import com.module_2.SpringBoot2.dtos.EmployeeDto;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final SalaryServiceImpl salaryService;
    private final String CACHE_NAME = "employees";

    @Cacheable(cacheNames = CACHE_NAME,key = "#employeeId")
    public EmployeeDto getEmployeeById(Long employeeId)
    {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDto.class))
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id : "+employeeId));
    }

    public List<EmployeeDto> getAllEmployees()
    {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @CachePut(cacheNames = CACHE_NAME,key = "#result.id")
    @Transactional
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto)
    {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        EmployeeEntity createNewEmployee = employeeRepository.save(employeeEntity);
        salaryService.createSalaryAccount(createNewEmployee);
        return modelMapper.map(createNewEmployee,EmployeeDto.class);
    }

    @CachePut(cacheNames = CACHE_NAME,key = "#employeeId")
    public EmployeeDto updateEntireEmployeeById(EmployeeDto employeeDto, Long employeeId)
    {
       EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(()->
               new ResourceNotFoundException("Employee not found with id : "+employeeId));

       if(employeeEntity == null)
       {
           EmployeeEntity employeeEntity1 = modelMapper.map(employeeDto,EmployeeEntity.class);
           return modelMapper.map(employeeRepository.save(employeeEntity1),EmployeeDto.class);
       }
       employeeEntity.setName(employeeDto.getName());
       employeeEntity.setAge(employeeDto.getAge());
       employeeEntity.setIsActive(employeeDto.getIsActive());
       employeeEntity.setDateOfJoining(employeeDto.getDateOfJoining());
       EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
       return modelMapper.map(employeeEntity1,EmployeeDto.class);


    }
    //helper functions
    public void existsById(Long employeeId)
    {
        if(!employeeRepository.existsById(employeeId))
        {
            throw new ResourceNotFoundException("Employee not found with id :"+employeeId);
        }

    }

    @CacheEvict(cacheNames = CACHE_NAME,key = "#employeeId")
    public Boolean deleteEmployeeById(Long employeeId)
    {
        existsById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    @CachePut(cacheNames = CACHE_NAME,key = "#employeeId")
    public EmployeeDto updateFewFields(Long employeeId, Map<String, Object> updates)
    {
         existsById(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->
        {
            Field field1 = ReflectionUtils.findField(EmployeeEntity.class,field);
            field1.setAccessible(true);
            ReflectionUtils.setField(field1,employeeEntity,value);

        });
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }
}
