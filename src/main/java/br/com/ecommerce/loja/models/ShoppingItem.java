package br.com.ecommerce.loja.models;

import java.math.BigDecimal;
import br.com.ecommerce.loja.enums.ProductType;

public class ShoppingItem {
	private Product product;
	private ProductType productType;
	private Integer productId;
	

	public ShoppingItem(Product product, ProductType productType) {
		this.product = product;
		this.productType = productType;
		this.productId = product.getId();
	}
	
	public ShoppingItem(Integer id, ProductType productType) {
		this.productType = productType;
		this.productId = id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	
	public BigDecimal getPrice(){
		/** Navegue também até a classe Product, para descobrir como é a implementação do método priceFor **/
		return product.priceFor(productType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productType == null) ? 0 : productType.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (productType != other.productType)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	public BigDecimal getTotal(Integer quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}
}
