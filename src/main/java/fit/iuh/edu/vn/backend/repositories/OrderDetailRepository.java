package fit.iuh.edu.vn.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.Order;
import fit.iuh.edu.vn.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order> {
}