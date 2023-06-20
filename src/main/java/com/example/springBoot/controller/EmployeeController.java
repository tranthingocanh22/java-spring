package com.example.springBoot.controller;

import com.example.springBoot.entities.dtos.EmployeeDto;
import com.example.springBoot.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public String getList() {
        return "/index";
    }

    @GetMapping("list")
    public String getList(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<EmployeeDto> employeeDtos = employeeService.findAll(pageable).map(EmployeeDto::new);
        model.addAttribute("employeeDtos", employeeDtos);
        return "/list";
    }

    @GetMapping("create")
    public String createProcess(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);
        return "/create";
    }

    @PostMapping("create/employee")
    public String create(@Valid @ModelAttribute EmployeeDto employeeDto,
                         Model model) {
        employeeService.create(employeeDto);
        model.addAttribute("success", "Create success!");
        return "redirect:/list";
    }
}
