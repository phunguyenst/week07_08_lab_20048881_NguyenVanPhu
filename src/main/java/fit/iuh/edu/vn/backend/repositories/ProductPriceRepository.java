package fit.iuh.edu.vn.backend.repositories;

import fit.iuh.edu.vn.backend.ids.ProductPricePK;
import fit.iuh.edu.vn.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import fit.iuh.edu.vn.backend.models.ProductPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {
    @Query("SELECT pp FROM ProductPrice pp WHERE pp.product = :product AND pp.price_date_time = (SELECT MAX(ppInner.price_date_time) FROM ProductPrice ppInner WHERE ppInner.product = :product)")
    Optional<ProductPrice> findNewPrice(@Param("product") Product product);
}
