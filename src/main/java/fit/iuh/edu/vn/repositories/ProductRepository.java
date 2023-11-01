package fit.iuh.edu.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}