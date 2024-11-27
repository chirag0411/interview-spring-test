package com.worthtech.interview.spring.test.controller;

import com.worthtech.interview.spring.test.dto.Department;
import com.worthtech.interview.spring.test.exception.DepartmentNotFoundException;
import com.worthtech.interview.spring.test.exception.InvalidRequestException;
import com.worthtech.interview.spring.test.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Tag(name = "post", description = "Save operation of departments APIs")
    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        if (department == null) {
            throw new InvalidRequestException("Invalid product request");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.saveDepartment(department));
    }

    @Tag(name = "get", description = "Get operation of department based on ID APIs")
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
        Optional<Department> departmentData = departmentService.findById(id);

        if (departmentData.isPresent()) {
            return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
        } else {
            throw new DepartmentNotFoundException("Data Not available.");
        }
    }

    @Tag(name = "get", description = "Read operation of departments APIs")
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> fetchDepartmentList() {
        return ResponseEntity.ok(departmentService.fetchDepartmentList());
    }

    @Tag(name = "put", description = "Update operation of departments APIs")
    @Operation(summary = "Update an department",
            description = "Update an existing department. The response is updated departments object with department object.")
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
        return departmentService.updateDepartment(department, departmentId);
    }

    @Tag(name = "delete", description = "Delete operation of departments APIs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class))}),
            @ApiResponse(responseCode = "404", description = "Department not found",
                    content = @Content)})
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Deleted Successfully";
    }
}