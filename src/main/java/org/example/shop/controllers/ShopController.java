package org.example.shop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.shop.model.Cart;
import org.example.shop.model.Product;
import org.example.shop.services.CartService;
import org.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ProductService productService;
    @Autowired
    private CartService cartService;

    private final static Logger LOG = LogManager.getLogger(CartController.class);
    protected static final String MESSAGE = "message";


    @GetMapping(value = {"/"})
    public String root(Model model) {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homepage(Model viewModel) {
        List<Product> products = productService.getProducts();
        loadCartItems(viewModel);
        viewModel.addAttribute("products", products.subList(0,8));
        return "index";
    }

    @GetMapping(value= {"/shop.html"})
    public String shop(Model viewModel) {
        List<Product> products = productService.getProducts();
        loadCartItems(viewModel);
        viewModel.addAttribute("products", products.subList(0, 15));
        return "shop";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(@PathVariable String name, Model viewModel) {
        loadCartItems(viewModel);
        return name;
    }


    @GetMapping(value = {"/single-product.html"})
    public String detailsPage(Model viewModel,
                              @RequestParam(name = "productId") Integer productId,
                              RedirectAttributes atts) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            viewModel.addAttribute("product", product);
        } else {
            String message = String.format("Product with ID '%s' could not be found", productId);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.warn(message);
            return "redirect:/error-page";
        }
        loadCartItems(viewModel);

        return "single-product";
    }


    /**
     * Loads the cart items from the cart object and stores the corresponding attributes in the viewModel viewModel.
     * @param viewModel {@link Model}
     */

    void loadCartItems(Model viewModel) {
        Cart cart = cartService.getCart();
        viewModel.addAttribute("cartItems", cart.getItems());
        viewModel.addAttribute("numOfCartItems", cart.getNumberOfItems());
        viewModel.addAttribute("grandTotal", cart.getGrandTotal());
    }
}
