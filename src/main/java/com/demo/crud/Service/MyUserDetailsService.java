package com.demo.crud.Service;

import com.demo.crud.Entity.Employee;
import com.demo.crud.Repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpRepo empRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee emp = empRepo.findEmployeeByName(username).orElseThrow(() ->  new UsernameNotFoundException("User not found"));
        System.out.println("Password from DB: " + emp.getPassword());

            return User.builder()
                    .username(emp.getName())
                    .password(emp.getPassword())
                    .build();

    }
}
