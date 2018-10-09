package com.mynotes.spring.samplerestapi.repo;

import org.springframework.data.repository.CrudRepository;

import com.mynotes.spring.samplerestapi.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
