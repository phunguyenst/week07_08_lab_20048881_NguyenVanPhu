package fit.iuh.edu.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}