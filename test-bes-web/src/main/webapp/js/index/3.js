app.controller("indexController",function($scope,$tools){
	$scope.name="你猜猜";
}).controller("routController",function($scope,$location){
	 $scope.$on("$viewContentLoaded",function(){
            console.log($location);
        });
    $scope.$on("$routeChangeStart",function(event,next,current){
            //event.preventDefault(); //cancel url change
            console.log(current,next);
        });
}).config(function($routeProvider, $locationProvider){
	 $routeProvider
           .when('/customer', {
            templateUrl: 'modules/customer.html',
            controller: 'customerController'
          })
});
app.controller("customerController",function($scope,$tools){
	$scope.customerList=[];
	$scope.search=function(){
		$tools.http("GET","http://localhost/custdomain/cust/v1/customer?number=1&size=10",null,null,function(data){
			$scope.customerList = data.data;
			console.log("222222===",JSON.stringify($scope.customerList));
		},null);
	};
	$scope.addressAdd={};
	$scope.add=function(){
		$tools.http("POST","http://localhost/app/v1/mdaddress",$scope.addressAdd,null,function(data){
			$scope.search();
			$scope.addressAdd={};
		},null);
	};
});