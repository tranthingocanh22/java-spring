package com.example.springBoot.services;

import com.example.springBoot.entities.Employee;
import com.example.springBoot.entities.dtos.EmployeeDto;
import com.example.springBoot.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee create(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto);
        BeanUtils.copyProperties(employeeDto, employee);
        return employeeRepository.save(employee);
    }

}
