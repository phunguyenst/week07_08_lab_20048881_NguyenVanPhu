package fit.iuh.edu.vn.backend.ids;

import fit.iuh.edu.vn.backend.models.Order;
import fit.iuh.edu.vn.backend.models.Product;

import java.io.Serializable;

public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
