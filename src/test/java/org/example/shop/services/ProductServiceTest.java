package org.example.shop.services;

import org.example.shop.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;

    public ProductServiceTest(){
        productService = new ProductService();
    }


    @Test
    void readProducts() {
        List<Product> products = productService.readProducts("src/main/resources/data/electronics.csv");
        assertNotNull(products);
        assertEquals(173, products.size());
    }

    @Test
    void getProductById_171() {
        Product xiaomi11 = productService.getProductById(171);
        assertNotNull(xiaomi11);
        assertTrue(xiaomi11.getName().startsWith("Xiaomi 11 Lite"));
    }

    @Test
    void getProductById_boundary(){
        int size = productService.getProducts().size();
        Product maxProduct = productService.getProducts().get(size-1);
        assertNotNull(maxProduct);
        assertTrue(maxProduct.getName().startsWith("Xifo LYF Earth"));
    }

    @Test
    void getProductById_non_existant(){
        Product nonExistant = productService.getProductById(99999);
        assertEquals(null, nonExistant);
    }

    @Test
    void readProducts_wrong_filename() {
        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.readProducts("xxx"));

        String expectedMessage = "xxx";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getProductRange_0_to_PAGE_SIZE() {

    }

    @Test
    void getProductRange_16_to_PAGE_SIZE_x_2() {
    }

    @Test
    void getProductRange_negative_from() {
    }

    @Test
    void getProductRange_to_greater_than_size() {
    }

    @Test
    void getProductsRange() {
    }
}
