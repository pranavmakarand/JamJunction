package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.ShoppingCart;
import com.example.demo.pojo.ProductItem;
import com.example.demo.service.ProductItemService;
import com.example.demo.service.ProductTypeService;

@Controller
public class ApplicationController {
	
	@Autowired
	ProductTypeService productService;
	
	@Autowired
	ProductItemService productItemService;
	
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", productService.getAllProductType());
        model.addAttribute("products", productItemService.getAllProductItem(0));
        return "shop";
    }
    
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", productService.getAllProductType());
        model.addAttribute("products", productItemService.getAllProductItem(id));
        return "shop";
    }
    
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        ShoppingCart.add(productItemService.findProductItemByID(id));
        return "redirect:/shop";
    }
    
    @GetMapping("/cart")
    public String cartGet(Model model) {
        int count = 1;
        
        model.addAttribute("cartSize", ShoppingCart.getProductItems().size());
        model.addAttribute("cartProducts", ShoppingCart.getProductItems());
        model.addAttribute("totalAmount", ShoppingCart.getMyTotal());
        model.addAttribute("count", count);
        return "cart";
    }
    
    @GetMapping("cart/removeItem/{id}")
    public String cartItemRemove(@PathVariable int id){
    	ShoppingCart.deleteProductItem(id);
        return "redirect:/cart";
    }
    
    @GetMapping("/checkout")
    public String checkout(Model model){
        return "checkout";
    }
    
    @PostMapping("/placeOrder")
    public String OrderPlaced(Model model) {
    	
    	for (ProductItem item : ShoppingCart.getProductItems()) {
    		ProductItem productItem = productItemService.findProductItemByID(item.getId());
    		productItem.setCount(productItem.getCount() - item.getCount());
    	}
    	
        model.addAttribute("orderNumber", new Random().nextInt());
    	
        model.addAttribute("deliveryDate", LocalDateTime.now().plusDays(10));

        return "placeOrder";
    }
}