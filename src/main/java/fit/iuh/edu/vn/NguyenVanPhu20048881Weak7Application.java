package fit.iuh.edu.vn;

import fit.iuh.edu.vn.enums.ProductStatus;
import fit.iuh.edu.vn.models.Product;
import fit.iuh.edu.vn.repositories.ProductRepository;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NguyenVanPhu20048881Weak7Application {

    public static void main(String[] args) {
        SpringApplication.run(NguyenVanPhu20048881Weak7Application.class, args);
    }

    @Autowired
    private ProductRepository productRepository;
@Bean
    CommandLineRunner createSampleProducts(){
        return args -> {
            Faker faker =new Faker();
//            Random rnd = new Random();
            Device devices = faker.device();
            for (int i = 0; i < 10; i++) {
                Product product =new Product(
                        devices.modelName(),
                        faker.lorem().paragraph(30),
//                        rnd.nextInt(10)%2==0?"Kg":"piece",
                        "piece",
                        devices.manufacturer(),
                        ProductStatus.ACTIVE
                );
                productRepository.save(product);
            }
        };
    }
}
