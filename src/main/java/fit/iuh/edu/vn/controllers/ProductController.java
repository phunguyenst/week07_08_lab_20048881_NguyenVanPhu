package fit.iuh.edu.vn.controllers;

import fit.iuh.edu.vn.dto.CartItem;
import fit.iuh.edu.vn.models.Product;
import fit.iuh.edu.vn.repositories.ProductRepository;
import fit.iuh.edu.vn.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
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

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "home";
    }
    @GetMapping("/buy/{id}")
    public String buy(Model model, HttpSession session, @PathVariable("id") long id)
    {
        Object obj = session.getAttribute("cart");
        Map<Long, CartItem> cartItems = null;
        if(obj == null){
            cartItems = new HashMap<>();
        }
        else
            cartItems = (HashMap<Long, CartItem>) obj;

        Product selProduct = productRepository.findById(id).get();
        int quan = cartItems.get(selProduct.getProduct_id()) == null
                ? 1 : cartItems.get(selProduct.getProduct_id()).getQuantity() + 1;
        CartItem item = new CartItem(selProduct, quan);

        cartItems.put(selProduct.getProduct_id(),item);
        session.setAttribute("cart", cartItems);
        return "redirect:/";
    }
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model){
        Object obj = session.getAttribute("cart");
        if(obj == null){
            return "/";
        }
        HashMap<Long, CartItem> cart = (HashMap<Long, CartItem>)  obj;
        model.addAttribute("selProduct", cart);


        List<CartItem> lst = new ArrayList<>(cart.values());
        model.addAttribute("selProduct", lst);
        return "checkout";
    }

}