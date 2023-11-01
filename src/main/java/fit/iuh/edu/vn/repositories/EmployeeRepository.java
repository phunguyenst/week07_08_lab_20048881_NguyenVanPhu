package fit.iuh.edu.vn.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {




}