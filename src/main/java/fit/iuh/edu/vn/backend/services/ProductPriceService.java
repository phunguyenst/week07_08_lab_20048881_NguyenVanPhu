package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.models.Product;
import fit.iuh.edu.vn.backend.models.ProductPrice;
import fit.iuh.edu.vn.backend.repositories.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProductPriceService {
    @Autowired
    private ProductPriceRepository productPriceRepository;
    public Optional<ProductPrice> findLatestPrice(Product product) {
        return productPriceRepository.findNewPrice(product);
    }

}
