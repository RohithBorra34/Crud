package com.demo.crud.Repository;

import com.demo.crud.Entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByName(String name);
    String findEmpByName(String username);
    List<Employee> findEmployeeByDept (String dept);

    @Query(value = "SELECT * FROM employee WHERE id > 2", nativeQuery = true)
    List<Employee> fetchEmployeesAboveId();
}
