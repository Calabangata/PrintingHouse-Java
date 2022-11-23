package com.company;


import java.math.BigDecimal;

public class Employee {
    private String name;
    private BigDecimal BaseSalary;
    private EmployeePosition Position;

    public Employee(String name,  EmployeePosition position) {
        this.name = name;
        Position = position;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBaseSalary() {
        return BaseSalary;
    }

    public EmployeePosition getPosition() {
        return Position;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        BaseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", BaseSalary=" + BaseSalary +
                ", Position=" + Position +
                '}'+'\n';
    }
}
