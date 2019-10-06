package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class ControllerDemo {

    private EmployeeService employeeService;

    @Autowired
    public ControllerDemo(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("list-employees")
    public String listEmployees(Model model){

        List<Employee> list = employeeService.findAll();

        model.addAttribute("employees", list);

        return "employees/list-employees";
    }

    @GetMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("Date", new java.util.Date());
        return "helloworld";
    }

    @GetMapping("/showFormForAdd")
    public String addEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model){

        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list-employees";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int id){

        employeeService.deleteById(id);

        return "redirect:/employees/list-employees";
    }
}
