package com.demo.crud.Controller;

import com.demo.crud.Entity.Employee;
import com.demo.crud.Exception.EmployeeNotFoundException;
import com.demo.crud.Repository.EmpRepo;
import com.demo.crud.Service.ServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.demo.crud.security.JwtUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class EmpController {

    @Autowired
    private ServiceImpl service;

    @Autowired
    private EmpRepo empRepo;

    Logger log = (Logger) LoggerFactory.getLogger(EmpController.class);

    @GetMapping("/check")
    public String check() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return String.valueOf(auth);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(value = "/employees")
    public List<Employee> getEmployees(Employee employee){
        log.info("Employee list has been fetched");
        return service.getEmployee(employee);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException exception) {
        return new ResponseEntity<>("Exception Handler is Tested" +exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/employees")
    public Employee createEmployees(@Valid @RequestBody Employee employee){
        return service.saveEmployee(employee);
    }

    @GetMapping(value = "/employees/{id}")
    public Employee getEmployeesById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return service.fetchEmployeeById(id);

    }

//    @GetMapping("/employee/{id}")
//    public Employee getEmp(@PathVariable int id) throws EmployeeNotFoundException {
//        return empRepo.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
//    }


    @DeleteMapping(value = "/employees/{id}")
    public String deleteEmployees(@PathVariable("id") int id) throws EmployeeNotFoundException {
        service.deleteById(id);
        return "Employee deleted successfully";   //remove this and make return type void
    }

    @PutMapping(value = "/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        return service.updateEmployeeById(id, employee);
    }

    @GetMapping(value = "/employees/name/{name}")
    public Optional<Employee> findByName(@PathVariable("name") String name){
        return service.fetchEmployeeByName(name);
    }

    @GetMapping(value = "/employees/dept/{dept}")
    public List<Employee> findByDept(@PathVariable("dept") String dept){
        return service.fetchEmployeeByDept(dept);
    }

    @GetMapping(value = "/employees/id")
    public List<Employee> getIdGreaterThanTwo(){
        return service.getIdMoreThanTwo();
    }





    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody Employee request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(request.getName());
    }
}
