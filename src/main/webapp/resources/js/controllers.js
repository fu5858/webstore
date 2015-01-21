var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function($scope, $http) {
	
	$scope.refreshCart = function(cartID){

		$http.get('/webstore/rest/cart/' + $scope.cartID)
		.success(function(data){
			$scope.cart = data;
			
		});
	};
	
	$scope.clearCart = function(){
		$http.delete('/webstore/rest/cart/' + $scope.cartID)
		.success($scope.refreshCart($scope.cartID));
	};
	
	$scope.initCartID = function(cartID){
		
		$scope.cartID = cartID;
		$scope.refreshCart($scope.cartID);
	};
	
	$scope.addToCart = function(productID){
		
		$http.put('/webstore/rest/cart/add/' + productID)
		.success(function(data){
			
			$scope.refreshCart($http.get('/webstore/rest/cart/get/cartID'));
//			$scope.refreshCart($http.get('/webstore/rest/cart/get/' + $scope.cartID));
			alert("Product Successfully added to the Cart!");
		});
	};
	
	$scope.removeFromCart = function(productID){
		$http.put('/webstore/rest/cart/remove/' + productID)
		.success(function(data){
			$scope.refreshCart($http.get('/webstore/rest/cart/get/cartID'));
		});
	};
});
