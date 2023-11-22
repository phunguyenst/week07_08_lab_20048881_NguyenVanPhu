package fit.iuh.edu.vn.frontend.dto;

import fit.iuh.edu.vn.backend.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String unit;
    private String manufacturer;
    private ProductStatus status;
    private String description;
    private String imagePath; // Assuming you want to display only the first image
    private Double price;

    public ProductDTO(String name, String unit, String manufacturer, ProductStatus status, String description, String imagePath, Double price) {
        this.name = name;
        this.unit = unit;
        this.manufacturer = manufacturer;
        this.status = status;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }
}
