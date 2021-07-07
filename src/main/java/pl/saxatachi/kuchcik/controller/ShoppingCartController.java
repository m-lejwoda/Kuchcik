package pl.saxatachi.kuchcik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.saxatachi.kuchcik.controller.dto.ItemCartDTO;
import pl.saxatachi.kuchcik.email.EmailSenderImpl;
import pl.saxatachi.kuchcik.exception.NotEnoughProductsInStockException;
import pl.saxatachi.kuchcik.model.*;
import pl.saxatachi.kuchcik.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Controller
@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final AddressService addressService;
    private final EmailSenderImpl emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, OrderService orderService,UserService userService,EmailSenderImpl emailSender,TemplateEngine templateEngine,AddressService addressService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.addressService = addressService;
    }
    @GetMapping("/")
    public String home() {
        return "Hello Kuchcik2!";
    }
    @GetMapping("/shoppingCart")
    public List<ItemCartDTO> shoppingCart() throws IOException {

//        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
//        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
//        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        List<ItemCartDTO> itemsincart = new ArrayList<>();
        for(Map.Entry<Product, Integer> entry : shoppingCartService.getProductsInCart().entrySet()){
            ItemCartDTO item = new ItemCartDTO();
            item.setName(entry.getKey().getName());
            item.setPrice(entry.getKey().getPrice());
            item.setQuantity(entry.getValue());
            itemsincart.add(item);
        }
        return itemsincart;
    }

//    @GetMapping("/shoppingCart/addProduct/{productId}")
//    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) throws IOException {
//        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
//        return shoppingCart();
//    }
    @GetMapping("/allorders")
    public List<Order> allOrders(){
        return orderService.allorders();
    }
    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId){
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return "Produkt został dodany";
    }
    @GetMapping("shoppingCart/removeProduct/{productId}")
        public String removeProductFromCart(@PathVariable("productId") Long productId){
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return "Produkt został usuniety";
    }
    @GetMapping("shoppingCart/removeWholeProduct/{productId}")
    public String removeWholeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeWholeProduct);
        return "Produkt został usuniety";
    }
    @PostMapping("/shoppingCart/addOrder")
    public Order addCartToOrder(@RequestBody Address address){
        List<OrderItem> itemsincart = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        Order order = new Order();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        orderService.addOrder(order);
        for(Map.Entry<Product, Integer> entry : shoppingCartService.getProductsInCart().entrySet()){
            OrderItem item = new OrderItem();
            Product product = productService.getById(entry.getKey().getId());
            item.setProduct(product);
            item.setQuantity(entry.getValue());
            item.setOrder_item_id(order.getId());
            itemsincart.add(item);
            orderService.addOrderItem(item);
        }
        order.setTotal(shoppingCartService.getTotal());
        if(userService.getUser(username).getAddress() != null){
            System.out.println("user service");
            System.out.println(userService.getUser(username).getAddress() );
            addresses.add(userService.getUser(username).getAddress());
        }else{
            addressService.saveAddress(address);
            addresses.add(address);
        }
//        addresses.add(userService.getUser(username).getAddress());
        order.setUserId(userService.getUser(userService.getUser(username).getEmail()).getId());
        userService.getUser(userService.getUser(username).getEmail());
        order.setAddress(addresses);
        order.setOrderitems(itemsincart);
        orderService.addOrder(order);
        Context context = new Context();
        context.setVariable("order",order);
        context.setVariable("price","zł");
        context.setVariable("header", "Forum kulinarne Kuchcik");
        context.setVariable("title", "Twoje zamówienie");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(userService.getUser(username).getEmail(), "Zamowienie", body);
        return order;
    }
    @GetMapping("/shoppingCart/checkUser")
    public User checkUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        System.out.println(userService.getUser(username).getAddress());

        return userService.getUser(username);
    }
    @PostMapping("/addaddress")
    public Address addAddresstoUser(Address address){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        userService.getUser(username).setAddress(address);
        return address;
    }
//    @GetMapping("/shoppingCart/removeProduct/{productId}")
//    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) throws IOException {
//        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
//        return shoppingCart();
//    }

//    @GetMapping("/shoppingCart/checkout")
//    public ModelAndView checkout() throws IOException {
//        try {
//            shoppingCartService.checkout();
//        } catch (NotEnoughProductsInStockException e) {
//            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
//        }
//        return shoppingCart();
//    }
}
