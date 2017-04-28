/**
 * 
 */

var app = angular.module('login.controllers', []);
 
var firstLoginId = null;

app.controller('loginController', ['$scope','loginService', '$location','$http','$state',
  	function ($scope, loginService, $location,$http,$state) {
	
		$scope.submitLogin = function () {
			//alert($scope.user);
			/*loginService.logIn($scope.user).then(
				function (response) {
                    $scope.state = undefined;
                    if(response.data=='true'){
                    	//alert("prosao login");
                    	//$location.path('chatPage');
                    	$state.transitionTo('chatPage');
                    }else{
                    	alert("Ne postoji korisnik sa tim parametrima.");
                    }
				}
			)*/
			var p=new Object();
			p.user=$scope.user;
			p.type="login";
			socket.send(JSON.stringify(p));
		}
		
		$scope.submitRegistration = function () {  
			if($("#pass").val() != $("#passRepeat").val()){
				alert("Password does not match the confirm password");
				return;
			}
			
			loginService.save($scope.user).then(
				function (response) {
                    $location.path('login');
                },
                function (response) {
                    alert("Greska pri registraciji.");
                }
            ); 	
		};
		
		
		$scope.logOut = function() {
			loginService.logOut().then(
				function (response) {
					$location.path('login');
	            }		
			)
		}		
		
		$scope.opsa = function () {  
			alert("nestoo");/*
			loginService.arest().then(
				function (response) {
					alert("eoeo");
					$state.go('chatPage');
                }
                
            ); 	*/
		};
		//checkRights();
		/*
		function checkRights() {			
			loginService.checkRights().then(
				function (response) {
					if(response.data != null){
					    $state.transitionTo('chatPage');
				    }
				}
			);
		}*/
		
		
		socket = new WebSocket('ws://' + $location.host() + ":" + $location.port() + '/Chat/login');
		socket=socket;
		socket.onmessage = onMessage;

	    function onMessage(event) {
	    	//alert(event.data);
	    	if(event.data==="true"){
	        	socket.close();
	        	$state.transitionTo('chatPage');    	
	    	}else{
	    		alert("Ne postoji korisnik sa tim parametrima.");
	    	}
	    		
	    }
}]);