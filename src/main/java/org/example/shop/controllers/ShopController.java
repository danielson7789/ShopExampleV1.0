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

    @GetMapping(value = {"/shop.html"})
    public String shop(Model viewModel, @RequestParam(name = "page", required = false) Integer page) {
        int from = (page == null ? 0 : page * productService.PAGE_SIZE);
        int to = (page == null) ? ProductService.PAGE_SIZE : (page * productService.PAGE_SIZE) + productService.PAGE_SIZE;
        List<Product> products = productService.getProducts();
        loadCartItems(viewModel);
        viewModel.addAttribute("products", productService.getProductsRange(from, to));
        viewModel.addAttribute("from", from);
        viewModel.addAttribute("to", to);
        viewModel.addAttribute("numberOfProducts", products.size());
        viewModel.addAttribute("page", (page==null) ? 1 : page);
        viewModel.addAttribute("numberOfPages", products.size()/productService.PAGE_SIZE + 1);
        return "shop";
    }


    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(@PathVariable String name, Model viewModel) {
        loadCartItems(viewModel);
        return name;
    }


    // Example URL: http://localhost:8080/single-page.html?id=1
    @GetMapping(value = {"/single-product.html"})
    public String detailsPage(Model viewModel,
                              @RequestParam(name = "productId") Integer productId,
                              RedirectAttributes atts) {
        // TODO: 1. get the product with {id} from the ProductService
        Product product = productService.getProductById(productId);

        if (product != null) {
            // TODO: 2. if it exists, add it to the viewModel as 'product'
            viewModel.addAttribute("product", product);
            LOG.info("Showing details of '{}'", product.getShortName());
        } else {
            // TODO: 3. if it doesn't, show an error message using 'atts.addFlashAttribute()'
            String message = String.format("Product with ID '%s' not found", productId);
            atts.addFlashAttribute(MESSAGE, message);
            LOG.warn(message);
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
