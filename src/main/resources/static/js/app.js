//create the module and name it mySpringApp

/*
 * AngularJS 1.2 and Routing The ngRoute module is no longer included in Angular after version 1.1.6. 
 * You will need to call the module and add it to the head of your document to use it.
 */

//also include ngRoute for all our routing needs
var mySpringApp = angular.module('mySpringApp', [ 'ngRoute' ]);

//configure our routes
mySpringApp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
    .when('/', {
	templateUrl : '/templates/home.html',
	controller : 'mainController'
    })

    // route for the contact page
    .when('/login', {
	templateUrl : '/templates/login.html',
	controller : 'loginController'
    })

    // route for the about page
    .when('/data', {
	templateUrl : '/templates/data.html',
	controller : 'dataController'
    }).otherwise('/');
});

//create the controller and inject Angular's $scope
mySpringApp.controller('mainController', function($rootScope, $scope, $http,
	$location) {

    $scope.message = 'Sample Single Page application development using Angular 1.x and Spring Boot';

    $scope.logout = function() {

	$location.path("/");

	$http.post('logout', {}).success(function() {
	    $rootScope.authenticated = false;
	    $location.path("/");
	}).error(function(data) {
	    $rootScope.authenticated = false;
	});
    }

    $scope.isLinkActive = function(url) {
	return url === $location.path();
    }

});

/*
 * Login Controller Logic
 */
mySpringApp.controller('loginController', function($rootScope, $scope, $http,
	$location) {

    /*
     * The authenticate() function is called when the controller is loaded to see
     * if the user is actually already authenticated (e.g. if he had refreshed
     * the browser in the middle of a session). We need the authenticate()
     * function to make a remote call because the actual authentication is done
     * by the server, and we don’t want to trust the browser to keep track of
     * it.
     */
    var authenticate = function(callback) {
	$http.get('user').success(function(data) {
	    if (data.name) {
		$rootScope.authenticated = true;
	    } else {
		$rootScope.authenticated = false;
	    }
	    callback && callback();
	}).error(function() {
	    $rootScope.authenticated = false;
	    callback && callback();
	});
    }

    authenticate();

    $scope.credentials = {};

    $scope.login = function() {

	$http.post('login', $.param($scope.credentials), {
	    headers : {
		"content-type" : "application/x-www-form-urlencoded"
	    }
	}).success(function(data) {

	    authenticate(function() {
		if ($rootScope.authenticated) {
		    $location.path("/");
		    $scope.error = false;
		} else {
		    $location.path("/login");
		    $scope.error = true;
		}
	    });
	}).error(function(data) {
	    $location.path("/login");
	    $scope.error = true;
	    $rootScope.authenticated = false;
	})
    };

});

/*
 * Data controller where Search and CRUD operation are performed
 */
mySpringApp
.controller(
	'dataController',
	function($scope, $http) {

	    $scope.message = 'Data Page! We are maintaining the Person information!';

	    var urlBase = "";
	    $scope.selection = [];
	    $scope.statuses = [ 'Male', 'Female' ];
	    $http.defaults.headers.post["Content-Type"] = "application/json";

	    function listAllEmployees() {
		$http.post(urlBase + '/getData', {
		    empName : 'Vignesh',
		    empGender : 'Male'
		}).success(
			function(data, status, headers) {
			    $scope.dataList = data;
			});
	    }

	    listAllEmployees();

	    $scope.refresh = function() {
		listAllEmployees();
	    }
	    
	    // add a new task
	    $scope.save = function() {

		if ($scope.empName == undefined
			|| $scope.empDesc == undefined
			|| $scope.empGender == undefined
			|| $scope.empName == "" || $scope.empDesc == ""
			|| $scope.empGender == "") {

		    alert('Insufficient Data! Please provide values for all Employee fields');
		} else {

		    $http.post(urlBase + '/save', {
			empName : $scope.empName,
			empDesc : $scope.empDesc,
			empGender : $scope.empGender
		    }).success(
			    function(data, status, headers) {
				alert('Employee details added');
				listAllEmployees();
			    });
		}
	    };

	    // add a new task
	    $scope.clear = function() {
		$scope.empName = "";
		$scope.empDesc = "";
		$scope.empGender = "";
	    };

	});