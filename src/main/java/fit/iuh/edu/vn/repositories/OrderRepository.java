package fit.iuh.edu.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}