/**
 * 
 */
var app = angular.module('chat.controllers', []);
 
var firstLoginId = null;

app.controller('chatPageController', ['$scope','loginService', '$location', '$http','$state', 'chatPageService',
  	function ($scope, loginService, $location,$http,$state,chatPageService) {
	
		var name;
	
		//checkRights();
		
		function checkRights() {			
			loginService.checkRights().then(
				function (response) {
					//alert(response.data);
					if(response.data == ""){
						alert("Access denied!");
					    $state.transitionTo('login');
				    }else{
				    	name=response.data;
				    	loadActive();
				    }
				}
			);
		}
		
		$scope.logOut = function() {
			loginService.logOut().then(
				function (response) {
					
	            }		
			)
		}
		
		
		function loadActive() {			
			
			chatPageService.getLoggedUsers().then(
					function (response){
						//alert(response.data);
						var niz=[];
						for(x in response.data){
							//alert(response.data[x].username);
							//alert(name.username);
							//if(response.data[x].username!=name.username)
								//niz.push(response.data[x].username);
							
						}
						$scope.items=niz;
					}
				)
		}
		
		$scope.myFunc = function($event) {
			if($event.keyCode==13){
				//socket.send(JSON.stringify($scope.inputField+"//"+name.username));
				socket.send(JSON.stringify($scope.inputField));
				$scope.inputField="";			
			}

	    };
	    
	    //var socket = new WebSocket("ws://localhost:8080/Chat/echo");
	    socket=new WebSocket('ws://' + $location.host() + ":" + $location.port() + '/Chat/echo');
	    socket.onmessage = onMessage;
	    
	    //$rootScope.socket.onMessage=onMessage;
	    function onMessage(event) {
	        //var device = JSON.parse(event.data);
	    	device=event.data;
	    	//alert(device);	    		
	    	$scope.field=$scope.field+"\n"+device;
	    	$scope.$digest(); 
	    	//$scope.field=device;		    	
	        if (device.action === "add") {
	        	alert(device);
	            //printDeviceElement(device);
	        }
	        if (device.action === "remove") {
	        	alert("remove");
	            //document.getElementById(device.id).remove();
	            //device.parentNode.removeChild(device);
	        }
	        if (device.action === "toggle") {
	        	alert("toggle");/*
	            var node = document.getElementById(device.id);
	            var statusText = node.children[2];
	            if (device.status === "On") {
	                statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
	            } else if (device.status === "Off") {
	                statusText.innerHTML = "Status: " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
	            }*/
	        }
	    }

}]);


