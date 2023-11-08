package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pojo.ProductItem;
import com.example.demo.pojo.ProductType;
import com.example.demo.pojo.User;
import com.example.demo.service.ProductItemService;
import com.example.demo.service.ProductTypeService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class AdminPortalController {
	
    @Autowired
    UserValidator validator;
    
	@Autowired
	UserService userService;
	
	@Autowired
	ProductTypeService productService;
	
	@Autowired
	ProductItemService productItemService;
	
	@RequestMapping(value = "/admin")
	public String getAdminIndexPage(Model model) {
		return "adminHomeForm";
	}
	
	@GetMapping(value = "/admin/showUserForm")
	public String getIndexPage(Model model) {
	    model.addAttribute("user", new User());
	    validator.userType = "NEW";
		return "addUserForm";
	}
	
	@PostMapping(value = "/admin/{operation}/saveUser/{userId}")
	public String saveUser(@ModelAttribute User user, BindingResult result, @PathVariable("operation") String operation, @PathVariable("userId") int id) {
		
		user.setId(id);
		
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			return "addUserForm";
		}
		
		if (operation.equals("update")) {
			userService.updateUser(user);
		} else {
			userService.addNewUser(user);
			userService.saveRole(user);
		}
		
		return "saveSucess";
	}
	
	@GetMapping(value = "/admin/showAllUsers")
	public String showAllUsersPage(Model model) {
		Iterable<User> users = userService.getAllUsers();
	    model.addAttribute("users", users);
		return "showUsers";
	}
	
	@PostMapping("/admin/{id}/deleteUser")
    public String deleteUser(@PathVariable("id") int id) {
		User user = userService.findUserByID(id);
        userService.deleteUser(user);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "showUsers";
    }
	
	@PostMapping("/admin/{id}/updateUser")
    public String updateUser(Model model, @PathVariable("id") int id) {
		User user = userService.findUserByID(id);
	    model.addAttribute("user", user);
	    validator.userType = "OLD";
		return "updateUserForm";
    }
	
	@GetMapping(value = "/admin/createProductType")
	public String createProductType(Model model) {
	    model.addAttribute("productType", new ProductType());
		return "addProductType";
	}
	
	@PostMapping(value = "/admin/{operation}/saveProductType/{productTypeId}")
	public String saveProductType(@ModelAttribute ProductType productType, BindingResult result, @PathVariable("operation") String operation, @PathVariable("productTypeId") int id) {
		
		productType.setId(id);
		
		if (result.hasErrors()) {
			return "addUserForm";
		}
		
		if (operation.equals("update")) {
			productService.updateProductType(productType);
		} else {
			productService.addNewProductType(productType);
		}
		
		return "saveSucess";
	}
	
	@PostMapping("/admin/{id}/deleteProductType")
    public String deleteProductType(@PathVariable("id") int id) {
		ProductType productType = productService.findProductTypeByID(id);
		productService.deleteProductType(productType);
        return "showProductType";
    }
	
	@PostMapping("/admin/{id}/updateProductType")
    public String updateProductType(Model model, @PathVariable("id") int id) {
		ProductType productType = productService.findProductTypeByID(id);
	    model.addAttribute("productType", productType);
	    validator.userType = "OLD";
		return "updateProductType";
    }
	
	@GetMapping(value = "/admin/showAllProductType")
	public String showAllProductTypes(Model model) {
		Iterable<ProductType> productTypes = productService.getAllProductType();
	    model.addAttribute("productType", productTypes);
		return "showProductType";
	}
	
	@PostMapping("/admin/{id}/addProductItem")
    public String addProductItem(Model model, @PathVariable("id") int id) {
		ProductType productType = productService.findProductTypeByID(id);
		ProductItem item = new ProductItem();
		item.setProductType(productType);
	    model.addAttribute("productItem", item);
	    model.addAttribute("productType", productType);
	    validator.userType = "OLD";
		return "addProductItem";
    }
	
	@PostMapping(value = "/admin/{operation}/saveProductItem/{productTypeId}/{productItemId}")
	public String saveProductItem(@ModelAttribute ProductItem productItem, 
		BindingResult result, @PathVariable("operation") String operation, @PathVariable("productItemId") int id, @PathVariable("productTypeId") int productTypeId) {
		
		ProductType productType = productService.findProductTypeByID(productTypeId);
		
		productItem.setId(id);
		
		productItem.setProductType(productType);
		
		if (result.hasErrors()) {
			return "addUserForm";
		}
		
		if (operation.equals("update")) {
			productItemService.updateProductItem(productItem);
		} else {
			productItemService.addNewProductItem(productItem);
		}
		
		return "saveSucess";
	}
	
	@PostMapping(value = "/admin/{productTypeId}/showProductItem")
	public String showAllProductItems(Model model, @PathVariable("productTypeId") int id) {

		Iterable<ProductItem> productItems = productItemService.getAllProductItem(id);
	    model.addAttribute("productItem", productItems);
	    model.addAttribute("productTypeId", id);
		return "showProductItem";
	}
	
	@PostMapping("/admin/{id}/deleteProductItem")
    public String deleteProductItem(@PathVariable("id") int id) {
		ProductItem productItem = productItemService.findProductItemByID(id);
		productItemService.deleteProductItem(productItem);
        return "showProductType";
    }
	
	@PostMapping("/admin/{id}/updateProductItem/{productTypeId}")
    public String updateProductItem(Model model, @PathVariable("id") int id, @PathVariable("productTypeId") int productTypeId) {
		ProductItem item = productItemService.findProductItemByID(id);
	    model.addAttribute("productItem", item);
	    model.addAttribute("productTypeId", productTypeId);
	    validator.userType = "OLD";
		return "updateProductItem";
	}
}
