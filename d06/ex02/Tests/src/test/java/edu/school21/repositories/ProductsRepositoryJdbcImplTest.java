package edu.school21.repositories;

import com.sun.xml.internal.ws.util.xml.CDATA;
import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "TIJ", 10),
            new Product(2L, "Eco cup", 30),
            new Product(3L, "Popsocket", 40),
            new Product(4L, "T-shirt", 130),
            new Product(5L, "Hoodie", 200),
            new Product(6L, "Backpack", 350)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(
            4L, "T-shirt", 130);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(
            4L, "T-shirt-updated", 135);


    ProductsRepository pr;

    @BeforeEach
    void init() throws SQLException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DataSource dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        pr = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAll() {
        List<Product> list = pr.findAll();
        for (int i = 0; i < list.size(); ++i) {
            assertEquals(EXPECTED_FIND_ALL_PRODUCTS.get(i), list.get(i));
        }
    }

    @Test
    void findById() {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, pr.findById(4L).orElse(null));
    }

    @Test
    void update() {
        Optional<Product> productOptional = pr.findById(4L);
        Product product = productOptional.orElse(null);
        assertNotNull(product);
        product.setName("T-shirt-updated");
        product.setPrice(135);
        pr.update(product);
        productOptional = pr.findById(4L);
        assertEquals(EXPECTED_UPDATED_PRODUCT, productOptional.orElse(null));
    }

    @Test
    void save() {
        final Long EXPECTED_LAST_ID = EXPECTED_FIND_ALL_PRODUCTS.get(EXPECTED_FIND_ALL_PRODUCTS.size() - 1).getId();
        Product product = new Product(null, "Test", 2142);
        pr.save(product);
        assertEquals(EXPECTED_LAST_ID + 1, product.getId());
        Optional<Product> productOptional = pr.findById(product.getId());
        assertEquals(product, productOptional.orElse(null));
    }

    @Test
    void delete() {
        Optional<Product> productOptional = pr.findById(4L);
        assertNotNull(productOptional.orElse(null));
        pr.delete(4L);
        productOptional = pr.findById(4L);
        assertNull(productOptional.orElse(null));
    }
}