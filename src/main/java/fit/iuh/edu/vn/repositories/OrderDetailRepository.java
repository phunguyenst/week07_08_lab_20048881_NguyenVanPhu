package fit.iuh.edu.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.Order;
import fit.iuh.edu.vn.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order> {
}