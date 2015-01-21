<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>    
<html>
<head>
	<title>Welcome Spring MVC</title>
	<script type="text/javascript">
		function welcome(){
			window.open("http://localhost:8080/webstore/welcome");
		}

		function products(){
			window.open("http://localhost:8080/webstore/products/all");
		}

		function products2(){
			window.open("http://localhost:8080/webstore/products");
		}

		function iphone(){
			window.open("http://localhost:8080/webstore/products/product?id=P1234");
		}

		function category1(){
			window.open("http://localhost:8080/webstore/products/tablet");
		}

		function filter(){
			window.open("http://localhost:8080/webstore/products/filter/byCriteria;brand=google,dell;category=tablet,laptop");
		}

		function filter2(){
			window.open("http://localhost:8080/webstore/products/tablet/price;low=200;high=400?manufacturer=Google");
		}

		function order(){
			window.open("http://localhost:8080/webstore/order/P1234/2");
		}

		function customers(){
			window.open("http://localhost:8080/webstore/customers");
		}

		function addNew(){
			window.open("http://localhost:8080/webstore/products/add");
		}

		function welcome2(){
			window.open("http://localhost:8080/webstore/home");
		}

		function viewImage(){
			window.open("http://localhost:8080/webstore/resource/images/Daniel.jpg");
		}

		function xmlView(){
			window.open("http://localhost:8080/webstore/products/product.xml?id=P1234");
		}

		function jsonView(){
			window.open("http://localhost:8080/webstore/products/product.json?id=P1234");
		}

		function exception(){
			window.open("http://localhost:8080/webstore/products/HeadPhones");
		}
		
		function errorPage(){
			window.open("http://localhost:8080/webstore/products/product?id=P1000");
		}

		function invalidOffer(){
			window.open("http://localhost:8080/webstore/products/specialOffer?promo=offer");
		}

		function validOffer(){
			window.open("http://localhost:8080/webstore/products/specialOffer?promo=OFF3R");
		}

		function create_cart(){
			window.open("http://localhost:8080/webstore/rest/cart/1234");
		}

		function add_cart(){
			window.open("http://localhost:8080/webstore/rest/cart/add/1234");
		}

	</script>
</head>
<body>
	<h1>Spring MVC!</h1>
	<p>Chapter 02</p>
	<button onclick="welcome()">Welcome</button>
	<button onclick="products()">Products</button>
	<button onclick="products2()">Products without url</button>
	<p>Chapter 03</p>
	<button onclick="iphone()">Product - iPhone</button>
	<button onclick="category1()">Products - Tablet</button>
	<button onclick="filter()">Products - Filter</button>
	<button onclick="filter2()">Products - Google</button>
	<button onclick="order()">Order</button>
	<button onclick="customers()">Customers</button>
	<p>Chapter 04</p>
	<button onclick="addNew()">Add New Product</button>
	<p>Chapter 05</p>
	<button onclick="welcome2()">Welcome with ModelAndView</button>
	<button onclick="viewImage()">View an Image </button>
	<button onclick="xmlView()">Product - iPhone with xml format</button>
	<button onclick="jsonView()">Product - iPhone with json format</button>
	<button onclick="exception()">Product exception </button>
	<button onclick="errorPage()">Product Not Found </button>
	<p>Chapter 06</p>
	<button onclick="invalidOffer()">Invalid Offer</button>
	<button onclick="validOffer()">Valid Offer</button>
	<p>Chapter 08</p>
	<button onclick="create_cart()">Create a new cart</button>
	<button onclick="add_cart()">Add a new cart</button>
	
</body>
</html>