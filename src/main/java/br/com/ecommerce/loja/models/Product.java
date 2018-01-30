package br.com.ecommerce.loja.models;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.ecommerce.loja.enums.ProductType;

@Entity
@Table
public class Product {
	
	/*
	 * Properties
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String title;
	@Lob
	@NotBlank
	private String description;
	@ElementCollection
	private List<Price> prices = new ArrayList<Price>();
	private boolean active;
	@Column(name="date_create")
	@DateTimeFormat
	private Calendar release; 
	private String attachmentPath;
	
	public Product() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Calendar getRelease() {
		return release;
	}

	public void setRelease(Calendar release) {
		this.release = release;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachment(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	
	public BigDecimal priceFor(ProductType productType) {
		return prices
				.stream()
				.filter(price -> price.getProductType().equals(productType))
				.findFirst().get().getValue();
	}
	
}
