package br.com.ecommerce.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.ecommerce.loja.enums.ProductType;

@Embeddable
public class Price {

	@Column(scale = 2)
	private BigDecimal value;
	private ProductType productType;
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	
	
}
