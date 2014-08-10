var pestApp = angular.module('pestApp',[]);

pestApp.controller('PestController', function($scope, $http){
	
	$scope.githubAddress="";
	$scope.username=null;
	$scope.password=null;
	$scope.versions=[];
	$scope.selectedVersions=[];
	$scope.requestVersionsReceipt = [];
	$scope.requestDiffFunctionsReceipt = [];
	$scope.report = "";
	
	$scope.requestVersions = function() {
		var data = {receiptId:0, 
					gitRepositoryLocation:$scope.githubAddress,
					username:$scope.username,
					password:$scope.password};
		$http.post('pestapp/preprocessor/requestversions', JSON.stringify(data)).success(function(data){	
			$scope.requestVersionsReceipt = data;
		}).error(function(data,status,headers,config){
			alert(JSON.stringify(status));
		});
	};
	
	$scope.retrieveVersions = function() {
		var data = $scope.requestVersionsReceipt;		
		$http.post('pestapp/preprocessor/retrieveversions', JSON.stringify(data)).success(function(data){	
			$scope.versions = data;		
			console.log($scope.versions);
		}).error(function(data,status,headers,config){
			alert(JSON.stringify(status));
		});
	};
	
	$scope.requestDiffFunctions = function() {
		
		if ($scope.selectedVersions.length != 2) {
			alert('Requires to select two versions');
			return;
		}
		
		var data = {receiptId:0, 
					gitRepositoryLocation:$scope.githubAddress,
					username:$scope.username,
					password:$scope.password,
					version1:$scope.selectedVersions[0].revisionName,
					version2:$scope.selectedVersions[1].revisionName};
		$http.post('pestapp/preprocessor/requestdifffunctions', JSON.stringify(data)).success(function(data){	
			$scope.requestDiffFunctionsReceipt = data;
		}).error(function(data,status,headers,config){
			alert(JSON.stringify(status));
		});
		
	};
	
	$scope.retrieveDiffFunctions = function() {
		var data = $scope.requestDiffFunctionsReceipt;
		$http.post('pestapp/preprocessor/retrievedifffunctions', JSON.stringify(data)).success(function(data){	
			$scope.report = JSON.stringify(data);
		}).error(function(data,status,headers,config){
			alert(JSON.stringify(status));
		});
	};
		
});
