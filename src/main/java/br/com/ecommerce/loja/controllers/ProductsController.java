package br.com.ecommerce.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.ecommerce.loja.daos.ProductDAO;
import br.com.ecommerce.loja.enums.ProductType;
import br.com.ecommerce.loja.infra.FileSaver;
import br.com.ecommerce.loja.models.Product;


@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private FileSaver fileSaver;

	/*
	 * Retorno das views
	 */
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("/products/form");
		modelAndView.addObject("types", ProductType.values());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile attachmentPath ,@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		String webPath = fileSaver.write("uploaded-images",attachmentPath);
		product.setAttachment(webPath);
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso",
				"Produto cadastrado com sucesso");
				return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}

}
