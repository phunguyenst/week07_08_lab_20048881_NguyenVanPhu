package fit.iuh.edu.vn.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}