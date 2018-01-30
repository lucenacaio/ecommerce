<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
</head>
<body>

	<form:form method="post" commandName="product"
		action="${spring:mvcUrl('PC#save').build()}" enctype="multipart/form-data">
		<div>
			<label for="title">Titulo</label>
			<form:input path="title" />
			<form:errors path="title" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea path="description" rows="10" cols="20" />
			<form:errors path="description" />
		</div>
		<c:forEach items="${types}" var="productType" varStatus="status">
			<div>
				<label for="price_${productType}">${productType}</label> <input
					type="text" name="prices[${status.index}].value"
					id="price_${productType}" /> <input type="hidden"
					name="prices[${status.index}].productType" value="${productType}" />
			</div>
		</c:forEach>
		<div>
			<label for="attachmentPath">Anexo</label>
			<input type="file" name="attachmentPath"/>
			<form:errors path="attachmentPath"/>
		</div>
		<div>
			<label for="active">Ativo</label> 
			<input type="checkbox"
				name="active" id="active">
			</textarea>
		</div>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
</body>
</html>