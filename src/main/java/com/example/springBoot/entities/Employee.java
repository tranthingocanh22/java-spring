package com.example.springBoot.entities;

import com.example.springBoot.entities.dtos.EmployeeDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "employees")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String fullName;
    @NotNull
    public String salary;

    public Employee(EmployeeDto employeeDto) {
        BeanUtils.copyProperties(employeeDto, this);
    }
}
