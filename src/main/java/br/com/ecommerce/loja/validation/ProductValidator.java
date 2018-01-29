package br.com.ecommerce.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.ecommerce.loja.models.Product;


public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> classz) {
		// TODO Auto-generated method stub
		return Product.class.equals(classz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required.product.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
	}
	
}
