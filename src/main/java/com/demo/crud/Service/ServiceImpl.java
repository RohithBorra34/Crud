package com.demo.crud.Service;

import com.demo.crud.Entity.Employee;
import com.demo.crud.Exception.EmployeeNotFoundException;
import com.demo.crud.Exception.ResponseException;
import com.demo.crud.Repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements EmpService{

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResponseException responseException;

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee emp = Employee.builder()
                .name(employee.getName())
                .password(passwordEncoder.encode(employee.getPassword()))
                .dept(employee.getDept())
                .build();

        return empRepo.save(emp);
    }

    @Override
    public List<Employee> getEmployee(Employee employee) {
        return empRepo.findAll();
    }

    @Override
    public Employee fetchEmployeeById(int id) throws EmployeeNotFoundException {
       // return empRepo.findById(id).get();
        Optional<Employee> employee = empRepo.findById(id);

        if(!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee is not present");
        }else{
            return employee.get();
        }
    }

    @Override
    public void deleteById(int id) throws EmployeeNotFoundException {

        Employee employee = empRepo.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id)
                );

        try {
            empRepo.delete(employee);

        } catch (Exception e) {

            throw new EmployeeNotFoundException(
                    "Error while deleting employee with id: " + id,
                    e
            );
        }
    }

    @Override
    public Employee updateEmployeeById(int id, Employee employee) {
        Optional<Employee> existingEmployee = empRepo.findById(id);

        if(existingEmployee.isPresent()){
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setDept(employee.getDept());

            return empRepo.save(updatedEmployee);
        }else {
            return null;
        }

    }

    @Override
    public Optional<Employee> fetchEmployeeByName(String name) {
        return empRepo.findEmployeeByName(name);
    }

    @Override
    public List<Employee> fetchEmployeeByDept(String dept) {
        return empRepo.findEmployeeByDept(dept);
    }

    @Override
    public List<Employee> getIdMoreThanTwo() {
        return empRepo.fetchEmployeesAboveId();
    }
}
