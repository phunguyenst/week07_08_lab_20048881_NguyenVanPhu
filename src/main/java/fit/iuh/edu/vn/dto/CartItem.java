package fit.iuh.edu.vn.dto;

import fit.iuh.edu.vn.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {
    private Product product;
    private int quantity;
}
