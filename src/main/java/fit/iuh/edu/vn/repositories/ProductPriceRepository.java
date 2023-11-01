package fit.iuh.edu.vn.repositories;

import fit.iuh.edu.vn.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.models.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Product> {
}