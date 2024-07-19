<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<%@include file="base-head.jsp"%>
	<title>CRUD Manager - inserir Vendedor</title>
</head>

<body>
	<%@include file="nav-menu.jsp"%>
	
	<div id="container" class="container-fluid">
		<h3 class="page-header">Adicionar Vendedor</h3>
		
	<div class="row">
		<form action="${pageContext.request.contextPath}/seller/insert/${action}" method="POST">
			<div class="form-group col-md-6">
					<label for="content">Nome</label>
						<input type="text" class="form-control" id="seller_name" name="seller_name" 
							   autofocus="autofocus" placeholder="Nome do vendedor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o nome do vendedor.')"
							   oninput="setCustomValidity('')">
			</div>
			
			<div class="form-group col-md-6">
					<label for="content">Email</label>
						<input type="email" class="form-control" id="seller_email" name="seller_email" 
							   autofocus="autofocus" placeholder="Email do vendedor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o Email do vendedor.')"
							   oninput="setCustomValidity('')">
			</div>
			
			<div class="form-group col-md-6">
					<label for="content">Telefone</label>
						<input type="number" class="form-control" id="seller_fone" name="seller_fone" 
							   autofocus="autofocus" placeholder="fone do vendedor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o telefone do vendedor.')"
							   oninput="setCustomValidity('')">
			</div>
			
			<div class="form-group col-md-6">
						<label for="seller_empresar">Empresa</label>
						<select id="seller_empresa" class="form-control selectpicker" name="seller_empresa" 
							    required oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
							    oninput="setCustomValidity('')">
						  <option value="" disabled ${not empty vendedor ? "" : "selected"}>Selecione uma empresa</option>
						  <c:forEach var="empresa" items="${empresas}">
						  	<option value="${empresa.getId()}">
						  		${empresa.getName()}
						  	</option>	
						  </c:forEach>
						</select>
			</div>
		</div>
			<hr />
			
			<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/sellers" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${not empty post ? "Alterar Vendedor" : "Criar Vendedor"}</button>
					</div>
			</div>
		</form>
		
	</div>
</body>
</html>