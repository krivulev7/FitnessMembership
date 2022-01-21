package com.FitnessMembership.FitnessMembership.Entities.Employees;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @OneToOne(mappedBy = "role")
    private Employee employee;

    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;

    }

    public Long getId() {
        return Id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

