package fit.iuh.edu.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}