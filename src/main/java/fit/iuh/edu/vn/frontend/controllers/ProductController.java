package fit.iuh.edu.vn.frontend.controllers;

import fit.iuh.edu.vn.backend.enums.ProductStatus;
import fit.iuh.edu.vn.backend.models.ProductImage;
import fit.iuh.edu.vn.backend.models.ProductPrice;
import fit.iuh.edu.vn.backend.services.ProductPriceService;
import fit.iuh.edu.vn.frontend.dto.CartItem;
import fit.iuh.edu.vn.backend.models.Product;
import fit.iuh.edu.vn.backend.repositories.ProductRepository;
import fit.iuh.edu.vn.backend.services.ProductService;
import fit.iuh.edu.vn.frontend.dto.ProductDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fit.iuh.edu.vn.backend.enums.ProductStatus;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductPriceService productPriceService;
    @GetMapping("/products")
    public String showProductListPaging(HttpSession session,Model model,
        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize= size.orElse(10);
        Page<Product> productPage = productService.findPaginated(currentPage - 1, pageSize , "name", "asc");
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if(totalPages >0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user/product-paging";
    }
    @GetMapping("/list")
    public String ShowProductList(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "admin/products/listing";
    }

   @GetMapping("/buy/{id}")
    public String buy(Model model, HttpSession session, @PathVariable("id") long id) {
        Object obj = session.getAttribute("items");
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            // Handle the case where the product is not found
            return "redirect:/products";
        }

        HashMap<Long, CartItem> cart;

        if (obj == null) {
            cart = new HashMap<>();
        } else {
            cart = (HashMap<Long, CartItem>) obj;
        }

        if (cart.containsKey(product.getProduct_id())) {
            // If the product is already in the cart, increase the quantity
            CartItem item = cart.get(product.getProduct_id());
            item.setQuantity(item.getQuantity() + 1);
        } else {
            // If the product is not in the cart, add it with quantity 1
            CartItem item = new CartItem(product, 1);
            cart.put(product.getProduct_id(), item);
        }

        session.setAttribute("items", cart);
        session.setAttribute("itemOnCart", cart.size());
        return "redirect:/products";
    }


    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model){
        Object obj = session.getAttribute("items");
        if(obj == null){
            return "redirect:/";
        }
        HashMap<Long, CartItem> cart = (HashMap<Long, CartItem>)  obj;
        List<ProductDTO> selectProduct =  cart.values().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            ProductImage firstImage = product.getProductImageList().stream().findFirst().orElse(null);
            String imagePath = (firstImage != null) ? firstImage.getPath() : "";
            Double price = product.getProductPrices().stream().findFirst().map(ProductPrice::getPrice).orElse(0.0);

            return new ProductDTO(
                    product.getProduct_id(),
                    product.getName(),
                    product.getUnit(),
                    product.getManufacturer(),
                    product.getStatus(),
                    product.getDescription(),
                    imagePath,
                    price
            );
        }).collect(Collectors.toList());

        model.addAttribute("selProduct", selectProduct);


        List<CartItem> lst = new ArrayList<>(cart.values());
        model.addAttribute("selProduct", lst);
        return "user/checkout";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.orElse(null));
        return "admin/products/update-product";
    }

    @PostMapping("/update")
    public String updateCandidate(@ModelAttribute Product product) {

        if (product.getManufacturer() == null) {
            product.setManufacturer("Default Manufacturer");
        }

        productRepository.save(product);
        return "redirect:/list";
    }


    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.orElse(null));
        return "admin/products/delete-product";
    }

    @PostMapping("/delete")
    public String deleteCandidate(@ModelAttribute Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(product.getProduct_id());

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setStatus(ProductStatus.TERMINATED);
            productRepository.save(existingProduct);
        }

        return "redirect:/list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/products/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model) {

        if (product.getManufacturer() == null || product.getName().isEmpty()) {
            model.addAttribute("error", "Manufacturer and Name are required."); // Add error message to be displayed in the form
            return "admin/products/add-product";
        }

        productRepository.save(product);
        return "redirect:/list";
    }

    @GetMapping("/statistics")
    public String showProductStatistics(Model model) {
        long countActive = productService.getCountByStatus(ProductStatus.ACTIVE);
        long countInactive = productService.getCountByStatus(ProductStatus.IN_ACTIVE);
        long countTerminated = productService.getCountByStatus(ProductStatus.TERMINATED);


        model.addAttribute("countActive", countActive);
        model.addAttribute("countInactive", countInactive);
        model.addAttribute("countTerminated", countTerminated);


        Map<String, Long> statusCountMap = new HashMap<>();
        statusCountMap.put("Active", countActive);
        statusCountMap.put("Inactive", countInactive);
        statusCountMap.put("Terminated", countTerminated);
        model.addAttribute("statusCountMap", statusCountMap);

        return "admin/statistics/statisticProductCount";
    }


    @GetMapping("/statisticPrice")
    public String showProductPriceStatistics(Model model,
                                             @RequestParam(name = "startDate", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                             @RequestParam(name = "endDate", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {


        Product product = productService.findById(1L).orElse(null);

        if (product != null) {
            Optional<ProductPrice> latestPrice = productPriceService.findLatestPrice(product);


            if (latestPrice.isPresent()) {
                double latestPriceValue = latestPrice.get().getPrice();

                model.addAttribute("latestPrice", latestPriceValue);
            }
        }

        // Your other logic here...

        return "admin/statistics/product_PriceStatistics";
    }




}