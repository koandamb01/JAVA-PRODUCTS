<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ page import = "java.io.*,java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style>
	form{ display: inline; }
	
	.container{
		padding-top: 5vh;
	}
</style>
<title>Products</title>
</head>
<body>
	<div class="container">
		<h1 class="mx-auto">${product.name}</h1>
		<div class="row">
			<div class="col-md-3">
				<a href="/categories/new" class="btn btn-sm btn-primary">Add a New Category</a>
			</div>
			
			<div class="col-md-6">
				<form action="/products/${product.id}/add" method="GET">
			    <div class="form-group">
			        <select class="form-control" name="category_id">
			        	<c:forEach var="catego" items="${categories}">
					        <option value="${catego.id}"> ${catego.getName()}</option>
					    </c:forEach>
			        </select>
			        
			    </div>
			    <input type="submit" value="Add" class="btn btn-sm btn-primary"/>
				</form>    
			</div>
			
			<div class="col-md-3">
				<a href="/products/new" class="btn btn-sm btn-success">Add a New Product</a>
			</div>
		</div>
		
		<br><br>
		<ul class="list-group">
		  <li class="list-group-item active">Categories</li>
		  <c:forEach var="catego" items="${product.getCategories()}">
		  	<li class="list-group-item">${ catego.getName() }</li>
	    </c:forEach>
		</ul>
	</div>
</body>
</html>
