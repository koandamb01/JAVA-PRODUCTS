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
<title>Categories</title>
</head>
<body>
	<div class="container">
		<h1 class="mx-auto">${category.name}</h1>
		<div class="row">
			<div class="col-md-3">
				<a href="/categories/new" class="btn btn-sm btn-primary">Add a New Category</a>
			</div>
			
			<div class="col-md-6">
				<form action="/categories/${category.id}/add" method="GET">
			    <div class="form-group">
			        <select class="form-control" name="product_id">
			        	<c:forEach var="prod" items="${products}">
					        <option value="${prod.id}"> ${prod.getName()}</option>
					    </c:forEach>
			        </select>
			        
			    </div>
			    <input type="submit" value="Add" class="btn btn-sm btn-primary"/>
				</form>    
			</div>
			
			<div class="col-md-3">
				<a href="/products/new" class="btn btn-sm btn-success">Add a New Prodcut</a>
			</div>
		</div>
		
		<br><br>
		<table class="table table-striped">
		    <thead class="thead-dark">
		        <tr>
		            <th>Name</th>
		            <th>Description</th>
		            <th>Price</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${category.getProducts()}" var="prod">
		        <tr>
		            <td>${prod.getName()}</td>
		            <td>${prod.getDescription()}</td>
		            <td>${prod.getPrice()}</td>
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
</body>
</html>
