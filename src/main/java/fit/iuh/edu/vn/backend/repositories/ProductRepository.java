package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    long countByStatus(ProductStatus status);
}