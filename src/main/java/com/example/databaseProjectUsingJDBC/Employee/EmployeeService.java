package com.example.databaseProjectUsingJDBC.Employee;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(Long id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (name, position, salary) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getPosition(), employee.getSalary());
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET name = ?, position = ?, salary = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getPosition(), employee.getSalary(), employee.getId());
    }

    public void deleteEmployeeById(Long id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
