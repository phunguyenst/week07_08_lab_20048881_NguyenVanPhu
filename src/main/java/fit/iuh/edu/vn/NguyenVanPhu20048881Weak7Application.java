package fit.iuh.edu.vn;

import fit.iuh.edu.vn.backend.enums.ProductStatus;
import fit.iuh.edu.vn.backend.models.Product;
import fit.iuh.edu.vn.backend.models.ProductImage;
import fit.iuh.edu.vn.backend.models.ProductPrice;
import fit.iuh.edu.vn.backend.repositories.ProductImageRepository;
import fit.iuh.edu.vn.backend.repositories.ProductPriceRepository;
import fit.iuh.edu.vn.backend.repositories.ProductRepository;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootApplication

public class NguyenVanPhu20048881Weak7Application {

    public static void main(String[] args) {
        SpringApplication.run(NguyenVanPhu20048881Weak7Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ProductImageRepository productImageRepository;
@Bean
    CommandLineRunner createSampleProducts(){
        return args -> {
            Faker faker =new Faker();
            Random rnd = new Random();
            Device devices = faker.device();
            for (int i = 0; i < 30; i++) {
                Product product =new Product(
                        devices.modelName(),
                        faker.lorem().paragraph(30),
//                        rnd.nextInt(10)%2==0?"Kg":"piece",
                        "piece",
                        devices.manufacturer(),
                        ProductStatus.ACTIVE
                );
                ProductImage img = createProductImage("assets/img-sample" + (i % 4 + 1) + ".png", "sample image");

                img.setProduct(product);





                ProductPrice price = new ProductPrice(LocalDateTime.now(), rnd.nextInt(1500), "Note #" + i);
                price.setProduct(product);

                productRepository.save(product);
                productImageRepository.save(img);

                productPriceRepository.save(price);
            }
        };
    }
    private ProductImage createProductImage(String path, String alternative) {
        ProductImage img = new ProductImage();
        img.setPath(path);
        img.setAlternative(alternative);
        return img;
    }
}
