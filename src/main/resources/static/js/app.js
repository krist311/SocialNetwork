'use strict';
var Network = angular.module('myNetwork',['ngRoute','ngResource',"xeditable","ui.bootstrap"]);

Network.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

Network.controller('EditableRowCtrl', function($scope, $filter, $http, $routeParams, $location,$rootScope) {
    $scope.userLogin = $routeParams.login;
    $http.get('getuser/' + $scope.userLogin).success(function (data) {
        $scope.user = data;
        $scope.currentId = $scope.user.id;
        if (data.login === $rootScope.userLogin) {
            $scope.myElementClass = '';
            $scope.foreignElementClass = 'hidden';
        } else {
            $scope.myElementClass = 'hidden';
            $scope.foreignElementClass = '';
        }
        $http.get('gettasks/' + $scope.currentId).success(function (data) {
            $scope.tasks = data;
        }).error(function () {
            alert('Unable to fetch tasks');
        });
        $http.get('getfollowers/' + $scope.currentId).success(function (data) {
            $scope.followers = data;
        }).error(function () {
            alert('Unable to fetch followers');
        });
        $http.get('getfollowing/' + $scope.currentId).success(function (data) {
            $scope.following = data;
        }).error(function () {
            alert('Unable to fetch followers');
        });
    }).error(function () {
        $location.path("/home/" + $rootScope.userLogin);
    });

    $scope.opened = {};
    $scope.data = {};

    $scope.open = function ($event, elementOpened) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened[elementOpened] = !$scope.opened[elementOpened];
    };

    $scope.giveUp = function (index) {
        $scope.tasks.splice(index, 1);
    };

    $scope.done = function (index) {
        $scope.tasks.splice(index, 1);
        // completedTasks++; or smth like that is needed here
    };

    $scope.check = function (data) {
        if (data == '') {
            return "This field is required";
        }
    };

    $scope.addTask = function () {
        //by default:
        $scope.inserted = {
            id: $scope.tasks.length + 1,
            name: '',
            date: '',
            description: '',
            tags: '',
            progress : '',
            goals: ''
        };
        $scope.tasks.push($scope.inserted);
    };
    $scope.saveTask = function (data, id) {
//$scope.user not updated yet
        angular.extend(data, {id: id});
        angular.extend(data, {user_id: $scope.user.id});
        $http.post('/savetask', data).success(function (data) {

        }).error(function () {
            alert("AJAX ERROR");
        });
    };

    function confirmPassword() {
        //   window.onload = function confirmPassword() {
        var pass1 = document.getElementById("pass1").value;
        var pass2 = document.getElementById("pass2").value;
        var alrt = document.getElementById("alert");
        var ok = true;
        if (pass1 != pass2) {
            alrt.innerHTML = '<div class="alert alert-danger alert-dismissible" id="alert"> <a class="panel-close close" data-dismiss="alert">×</a> <i class="fa fa-ban"></i> Passwords do not match. </div>';
            document.getElementById("pass1").style.borderColor = "#E34234";
            document.getElementById("pass2").style.borderColor = "#E34234";
            ok = false;
        }
        else if (pass1 != "" && pass2 != "") {
            alrt.innerHTML = '<div class="alert alert-success alert-dismissible" id="alert"> <a class="panel-close close" data-dismiss="alert">×</a> <i class="fa fa-check"></i> Success </div>';
            document.getElementById("pass1").style.borderColor = "#ccc";
            document.getElementById("pass2").style.borderColor = "#ccc";
        }
        return ok;
        //   }
    };
});