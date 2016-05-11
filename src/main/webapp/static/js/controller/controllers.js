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
Network.controller('HomeCtrl', ['$scope', 'MyUser', '$http', '$routeParams', function($scope, MyUser, $http, $routeParams) {
    var self = this;
    $scope.id=$routeParams.id;
    $http.get('myuser/'+ $scope.id).success(function(data) {
        $scope.myuser = data;
    });
    self.user= new MyUser();

    self.users=[];

    self.fetchAllUsers = function(){
        self.users = MyUser.query();
    };

    self.createUser = function(){
        self.user.$save(function(){
            self.fetchAllUsers();
        });
    };

    self.updateUser = function(){
        self.user.$update(function(){
            self.fetchAllUsers();
        });
    };

    self.deleteUser = function(identity){
        var user = User.get({id:identity}, function() {
            user.$delete(function(){
                console.log('Deleting user with id ', identity);
                self.fetchAllUsers();
            });
        });
    };

    self.fetchAllUsers();

    self.submit = function() {
        if(self.user.id==null){
            console.log('Saving New User', self.user);
            self.createUser();
        }else{
            console.log('Upddating user with id ', self.user.id);
            self.updateUser();
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//If it is the one shown on screen, reset screen
            self.reset();
        }
        self.deleteUser(id);
    };


    self.reset = function(){
        self.user= new User();
        $scope.myForm.$setPristine(); //reset Form
    };

}]);
Network.controller('FriendsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('MessagesCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
Network.controller('SettingsCtrl',['$scope','$http', '$location', function($scope, $http, $location) {

}]);
