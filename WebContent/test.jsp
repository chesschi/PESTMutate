<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
   <title>Simple jsp page</title>
   		<link rel="stylesheet" href="styles/master.css" type="text/css" media="screen">		
   		<link rel="stylesheet" href="styles/home.css" type="text/css" />
   		<script src="scripts/angular.min.js"></script>
		<script src="scripts/home.js"></script>   		
  </head>
  <body>

  	<div ng-app='pestApp' ng-controller="PestController" ng-cloak>
		<input type="text" ng-model="githubAddress" placeholder="git repository"/>
		<input type="button" ng-click="submit()" value="Submit"/>
		<input type="button" ng-click="cancel()" value="Cancel"/>
		<hr/>
		<textarea class="report" ng-model="report" readonly></textarea>
	</div>

  </body>
</html>