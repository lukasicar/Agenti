<html lang="en" data-ng-app="routerApp">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chat</title>
<!-- CSS (load bootstrap) -->
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<!-- AngularJS ============================================================= -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-resource.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.8/angular-ui-router.min.js"></script>


<!-- AngularJS App Code ==================================================== -->
<script src="app.js"></script>
<script src="registerController.js"></script>
<script src="loginService.js"></script>
<script src="loginController.js"></script>
<script src="chatPageController.js"></script>
<script src="chatPageService.js"></script>

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.min.js"></script>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>


</head>
<body class="bg">

	
    <div>
		<div data-ui-view></div>
	</div>
    <br>

    
    
</body>
</html>