/**
 * 
 */
var services = angular.module('login.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('loginService', ['$http', function($http){
	
	this.logIn = function(user){
		return $http.post("/Chat/rest/login",user);
	}
	
	this.logOut = function(){
		return $http.get("/Chat/rest/login");
	}
	
	this.save = function(guest){
		return $http.put("/Chat/rest/login",guest);
	}
	
	this.checkRights = function(){
		return $http.get("/Chat/rest/login/checkRights");
	}
	
	this.arest=function(){
		return $http.get(/*baseUrl+*/"/Chat/rest/login/ll");
	}
}]);