package fit.iuh.edu.vn.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}