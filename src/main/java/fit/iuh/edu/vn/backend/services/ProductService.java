package fit.iuh.edu.vn.backend.services;

import fit.iuh.edu.vn.backend.enums.ProductStatus;
import fit.iuh.edu.vn.backend.models.Product;
import fit.iuh.edu.vn.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findPaginated(int pageNo, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return productRepository.findAll(pageable);
    }
    public long getCountByStatus(ProductStatus status) {
        return productRepository.countByStatus(status);
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }
}
