'use strict';

Network.config(['$routeProvider', '$locationProvider', function($routeProvide, $locationProvider){
    $routeProvide
        .when('/home/:id',{
            templateUrl:'static/pages/home.html',
            controller:'HomeCtrl as ctrl'
        })
        .when('/friends',{
            templateUrl:'static/pages/friends.html',
            controller:'FriendsCtrl'
        })
        .when('/messages', {
            templateUrl:'static/pages/messages.html',
            controller:'MessagesCtrl'
        })
        .when('/settings', {
            templateUrl:'static/pages/settings.html',
            controller:'SettingsCtrl'
        })
        .otherwise({
           //redirectTo: '/'
        });
}]);
Network.controller('HomeCtrl', ['$scope', 'User', '$http', '$routeParams', function($scope, User, $http, $routeParams) {
    $scope.id=$routeParams.id;
    $http.get('user/'+ $scope.id).success(function(data) {
        $scope.user = data;
    });
}]);
Network.controller('FriendsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('MessagesCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('SettingsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
