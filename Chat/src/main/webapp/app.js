/**
 * 
 */
angular.module('routerApp', ['ui.router','login.controllers','chat.services','chat.controllers','login.services'
	]).config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/login');
    
    $stateProvider
    
    .state('login', {
    	url : '/login',
      	templateUrl : 'login.html',
      	controller : 'loginController'
     })
     
     .state('register', {
    	url : '/register',
      	templateUrl : 'register.html',
      	controller : 'loginController'
     })
     .state('chatPage', {
    	url : '/chat',
      	templateUrl : 'chatPage.html',
      	controller : 'chatPageController'
     })
});