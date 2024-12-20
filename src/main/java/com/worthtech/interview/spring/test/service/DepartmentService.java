package com.worthtech.interview.spring.test.service;

import com.worthtech.interview.spring.test.dto.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    // Save operation
    Department saveDepartment(Department department);

    // Read operation
    List<Department> fetchDepartmentList();

    // Update operation
    Department updateDepartment(Department department,
                                Long departmentId);

    // Delete operation
    void deleteDepartmentById(Long departmentId);

    Optional<Department> findById(long id);
}
