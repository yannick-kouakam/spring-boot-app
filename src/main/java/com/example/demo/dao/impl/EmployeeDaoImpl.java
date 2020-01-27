package com.example.demo.dao.impl;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private EntityManager entityManager;

    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> getEmployees() {
        Session session = getSession();
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = getSession();
        session.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
     Session session = getSession();
     session.delete(employee);
    }

    @Override
    public Employee findEmployee(Long employeeId) {
        Session session = getSession();
        return session.find(Employee.class, employeeId);
    }

    private Session getSession(){
        return entityManager.unwrap(Session.class);
    }
}
