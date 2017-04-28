/**
 * 
 */

var services = angular.module('chat.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('chatPageService', ['$http', function($http){
	
	this.getLoggedUsers = function(){
		return $http.get("/Chat/rest/login/getLoggedUsers");
	}
	
}]);