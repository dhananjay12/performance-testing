package com.mynotes.spring.samplerestapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mynotes.spring.samplerestapi.exceptions.EntityNotFoundException;
import com.mynotes.spring.samplerestapi.model.Employee;
import com.mynotes.spring.samplerestapi.repo.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepo empRepo;

	@GetMapping()
	public Iterable<Employee> getDrivers() {
		return empRepo.findAll();
	}

	@GetMapping("/{empId}")
	public Employee getDriver(@PathVariable long empId) {
		return empRepo.findById(empId).get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createDriver(@Valid @RequestBody Employee employeeRequest) {
		return empRepo.save(employeeRequest);
	}

	@DeleteMapping("/{empId}")
	public void deleteDriver(@PathVariable long empId) throws EntityNotFoundException {
		findEmployee(empId);
		empRepo.deleteById(empId);
	}

	@DeleteMapping
	public void deleteAll() {
		empRepo.deleteAll();
		;
	}

	private Employee findEmployee(Long empId) throws EntityNotFoundException {
		return empRepo.findById(empId)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + empId));
	}

}
