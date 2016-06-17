'use strict';

Network.config(['$routeProvider', '$httpProvider','$locationProvider', function($routeProvide, $httpProvider, $locationProvider){
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $locationProvider.html5Mode(true);
    $routeProvide
        .when('/home/:login',{
            templateUrl:'pages/user-profile.html',
            controller:'EditableRowCtrl as ctrl'
        })
        .when('/friends',{
            templateUrl:'pages/friends.html',
            controller:'FriendsCtrl'
        })
        .when('/messages', {
            templateUrl:'pages/messages.html',
            controller:'MessagesCtrl'
        })
        .when('/settings', {
            templateUrl:'pages/settings.html',
            controller:'SettingsCtrl'
        })
        .when('/exit',{
            templateUrl:'pages/sign-in-sign-up.html',
            controller:'LoginController as ctrl'
        });

    //.otherwise({
           //redirectTo: '/'
       // });
}]);
Network.controller('HomeCtrl', ['$scope', 'User', '$http', '$routeParams', function($scope, User, $http, $routeParams) {
    $scope.id=$routeParams.id;
    $http.get('getuser/'+ $scope.id).success(function(data) {
        $scope.user = data;
    });
}]);
Network.controller('FriendsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('MessagesCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('SettingsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('LoginController', function($rootScope, $http, $location, $window) {

    var self = this;
    self.logout = function() {
        $http.get('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $window.location.href = '/';
        });
    };

    self.register = function(){
        $http.post('/registeruser', self.credentials).success(function() {
            alert("Thank you for your registration")
        }).error(function(){
            alert("Login is already in use");
        });
    }
    var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
                $rootScope.userLogin = response.data.name;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }

    authenticate();
    self.credentials = {};
    self.login = function() {
        authenticate(self.credentials, function() {
            if ($rootScope.authenticated) {
                self.error = false;
                $window.location.href = '/home/' + $rootScope.userLogin;
            } else {
                $location.path("/exit");
                self.error = true;
            }
        });
    };
});

