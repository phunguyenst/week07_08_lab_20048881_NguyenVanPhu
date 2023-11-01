package fit.iuh.edu.vn.ids;

import fit.iuh.edu.vn.models.Order;
import fit.iuh.edu.vn.models.Product;

import java.io.Serializable;

public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
