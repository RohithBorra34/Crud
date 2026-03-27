package com.demo.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {

    private int id;

    @NotBlank(message = "This field should not be null")
    @Size(min = 4, max = 20)

    private String name;

    private String dept;

    @NotNull(message = "Password should not be null")
    @Positive
    private String password;
}
