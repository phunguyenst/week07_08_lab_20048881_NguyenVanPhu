package fit.iuh.edu.vn.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}