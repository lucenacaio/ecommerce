package br.com.ecommerce.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.ecommerce.loja.daos.ProductDAO;
import br.com.ecommerce.loja.enums.ProductType;
import br.com.ecommerce.loja.models.Product;
import br.com.ecommerce.loja.models.ShoppingCart;
import br.com.ecommerce.loja.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, ProductType productType) {
		ShoppingItem item = createItem(productId, productType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}
	
	private ShoppingItem createItem(Integer productId, ProductType productType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(productId, productType);
		return item;
	}
	
	
}
