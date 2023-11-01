package fit.iuh.edu.vn.controllers;

import fit.iuh.edu.vn.models.Product;
import fit.iuh.edu.vn.repositories.ProductRepository;
import fit.iuh.edu.vn.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public String showProductListPaging(Model model,
        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize= size.orElse(10);
        Page<Product> productPage = productService.findAll(currentPage - 1, pageSize , "product_id", "asc");
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if(totalPages >0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "products/product-paging";
    }
    @GetMapping("/list")
    public String ShowProductList(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products/products";
    }
}