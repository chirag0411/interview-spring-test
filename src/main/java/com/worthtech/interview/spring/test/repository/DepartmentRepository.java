package com.worthtech.interview.spring.test.repository;

import com.worthtech.interview.spring.test.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}