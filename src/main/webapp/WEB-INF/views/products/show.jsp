<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<customTags:page bodyClass="" title="">
	<ul class="clearfix">
		<li><a href="" rel="nofollow">Seu carrinho
				(${shoppingCart.quantity}) </a></li>
	</ul>
	<article id="${product.title}" itemscope
		itemtype="http://schema.org/Book">
		<header id="product-highlight" class="clearfix">
			<div id="product-overview" class="container">
				<img itemprop="image" width="280px" height="395px"
					src="${product.attachmentPath}" class="product-featured-image"
					alt="${product.title}">
				<h1 class="product-title" itemprop="name">${product.title}</h1>
				<p class="product-author">
					<span class="product-author-link"> ${product.title} </span>
				</p>

				<p itemprop="description" class="book-description">
					${product.description} Veja o <a href="${product.attachmentPath}"
						target="_blank">sum&#225;rio</a> completo do livro!
				</p>
			</div>
		</header>


		<section class="buy-options clearfix">
			<form:form servletRelativeAction="/shopping" cssClass="container">
				<input type="hidden" value="${product.id}" name="productId" />
				<ul id="variants" class="clearfix">
					<c:forEach items="${product.prices}" var="price">
						<li class="buy-option"><input type="radio" name="productType"
							class="variant-radio" id="${product.id}-${price.productType}"
							value="${price.productType}"
							${price.productType.name() == 'COMBO' ? 'checked' : ''}>
							<label class="variant-label"
							for="${product.id}-${price.productType}">
								${price.productType} </label>
							<p class="variant-price">${price.value}</p></li>
					</c:forEach>




				</ul>

				<input type="submit" class="submit-image icon-basket-alt"
					alt="Compre agora" title="Compre agora '${product.title}'!"
					value="Comprar" />

			</form:form>

		</section>

		<div class="container">

			<section class="author product-detail" itemprop="author" itemscope
				itemtype="http://schema.org/Person">
				<h2 class="section-title" itemprop="name">${product.title}</h2>
				<span itemprop="description">

					<p class="book-description">${product.description}</p>

				</span>
			</section>

			<section class="data product-detail">



				<p></p>
				<p>
					Encontrou um erro? <a href='/submissao-errata' target='_blank'>Submeta
						uma errata</a>
				</p>
			</section>
		</div>


	</article>

</customTags:page>