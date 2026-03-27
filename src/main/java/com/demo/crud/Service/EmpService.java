package com.demo.crud.Service;

import com.demo.crud.Entity.Employee;
import com.demo.crud.Exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

interface EmpService {

    Employee saveEmployee(Employee employee);

    List<Employee> getEmployee(Employee employee);

    Employee fetchEmployeeById(int id) throws EmployeeNotFoundException;

    void deleteById(int id) throws EmployeeNotFoundException;

    Employee updateEmployeeById(int id, Employee employee);

    Optional<Employee> fetchEmployeeByName(String name);

    List<Employee> fetchEmployeeByDept (String dept);

    List<Employee> getIdMoreThanTwo();
}
