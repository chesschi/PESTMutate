
<%@ page contentType="text/html" pageEncoding="UTF-8"
%><%@taglib prefix="pest" tagdir="/WEB-INF/tags" %>
<pest:master title="hello">
	<jsp:attribute name="header">
		<link rel="stylesheet" href="styles/home.css" type="text/css" />
		<script src="scripts/home.js"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
		<div ng-app='pestApp' ng-controller="PestController" ng-cloak>
			<div class="control-panel" >
				<input type="text" ng-model="githubAddress" placeholder="git repository"/>
				<div class="control-buttons">
					<input type="button" ng-click="requestVersions()" value="Request Version Lists"/>
					<input type="button" ng-click="retrieveVersions()" value="Retrieve Version Lists"/>					
				</div>
			</div>			
			<div class="version-panel">
				<select class="selectBox" size="10" ng-model="selectedVersions" ng-options="(version.commitTimestamp+'&nbsp;'+version.commitShortDescription) for version in versions.commitObjects" multiple></select>
				<div class="control-buttons">
					<input type="button" ng-click="requestDiffFunctions()" value="Submit"/>
					<input type="button" ng-click="retrieveDiffFunctions()" value="Retrieve"/>
				</div>
			</div>
			<div class="report-panel">
				<textarea class="report" ng-model="report" readonly></textarea>
			</div>
		</div>
	</jsp:attribute>
	<jsp:attribute name="footer"></jsp:attribute>
</pest:master>

