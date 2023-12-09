package ssf.iss.day13class.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ssf.iss.day13class.model.Employee;
import ssf.iss.day13class.repo.EmployeeRepo;

@Controller
@RequestMapping ("/employees")
public class EmployeeController {
    @Autowired // @Autowired is used for automatic dependency injection.
    // example if have multiple controllers, will always access the same instance of EmployeeRepo instad of instantiating in all controllers created
    EmployeeRepo empRepo;

    @GetMapping("/list")
    public String employeeList(Model model) { //model is like a hashmap that helps communicate with thymeleaf -  used in html
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees); // "employees" in employeelist html <tr data-th-each="emp: ${employees}">
        return "employeelist";
    }

    @GetMapping ("/addnew")
    public String employeeAdd(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp); // "employee" in employeeadd html <form method ="POST" th:action="@{/employees/saveEmployee}" th:object="${employee}">
        return "employeeadd";
    }

    @PostMapping ("/saveEmployee")
    //sequence of parameters input is important 
    public String saveEmployee (@Valid @ModelAttribute("employee") Employee employeeForm, 
            BindingResult result, Model model) throws FileNotFoundException{
        if (result.hasErrors()) {
            return "employeeadd";
        }
        Boolean returnResult = empRepo.save(employeeForm); //saves to employees/list
        // return "redirect:/employees/list";
        model.addAttribute("savedEmployee", employeeForm);
        return "success";
    }

    @GetMapping ("/employeedelete/{emailPV}/delete") //terms in {} is a variable. example 
    // http://localhost:8080/employees/employeedelete/pap@pap.com/delete
    // frontend relation: employeelist.html 
    // <a th:href="@{/employees/employeedelete/{emailVar}(emailVar=${emp.email})}" class = "btn btn-danger">Delete</a>
    public String deleteEmployee (@PathVariable("emailPV") String email) {
        Employee emp = empRepo.findbyEmail(email);
        Boolean Result = empRepo.delete(emp);

        return "redirect:/employees/list";
    }

    @GetMapping("/employeeupdate/{emailPV}") 
    public String updateEmployee(@PathVariable("emailPV") String email, Model model) {
        Employee emp = empRepo.findbyEmail(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";  
    }

    @PostMapping("/updEmployee")
    public String updateEmployeeRecord(@ModelAttribute("employee") Employee emp, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "employeeupdate";
        }
        
        empRepo.updateEmployee(emp);
        return "redirect:/employees/list";
    }
    
}
