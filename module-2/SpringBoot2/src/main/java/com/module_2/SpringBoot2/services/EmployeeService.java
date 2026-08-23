package com.module_2.SpringBoot2.services;

import com.module_2.SpringBoot2.dtos.EmployeeDto;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
    public Optional<EmployeeDto> getEmployeeById(Long employeeId)
    {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployees()
    {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto)
    {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }

    public EmployeeDto updateEntireEmployeeById(EmployeeDto employeeDto, Long employeeId)
    {
       EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);

       if(employeeEntity == null)
       {
           EmployeeEntity employeeEntity1 = modelMapper.map(employeeDto,EmployeeEntity.class);
           return modelMapper.map(employeeRepository.save(employeeEntity1),EmployeeDto.class);
       }
       employeeEntity.setName(employeeDto.getName());
       employeeEntity.setAge(employeeDto.getAge());
       employeeEntity.setIsActive(employeeDto.getIsActive());
       employeeEntity.setDateOfJoining(employeeDto.getDateOfJoining());
       return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);


    }
    //helper functions
    public boolean existsById(Long employeeId)
    {
        return employeeRepository.existsById(employeeId);
    }

    public Boolean deleteEmployeeById(Long employeeId)
    {
        if(!existsById(employeeId))
        {
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updateFewFields(Long employeeId, Map<String, Object> updates)
    {
        if(!existsById(employeeId))
        {
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->
        {
            Field field1 = ReflectionUtils.findField(EmployeeEntity.class,field);
            field1.setAccessible(true);
            ReflectionUtils.setField(field1,employeeEntity,value);

        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }
}
