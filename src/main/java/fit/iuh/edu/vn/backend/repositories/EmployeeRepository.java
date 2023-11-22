package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {




}