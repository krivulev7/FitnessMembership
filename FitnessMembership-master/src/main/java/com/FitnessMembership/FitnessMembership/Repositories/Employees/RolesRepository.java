package com.FitnessMembership.FitnessMembership.Repositories.Employees;

import com.FitnessMembership.FitnessMembership.Entities.Employees.Role;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);
}
