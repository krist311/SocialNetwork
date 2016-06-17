'use strict';
var Network = angular.module('myNetwork',['ngRoute','ngResource',"xeditable","ui.bootstrap"]);

Network.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

Network.controller('EditableRowCtrl', function($scope, $filter, $http, $routeParams, $location,$rootScope) {
    $scope.userLogin=$routeParams.login;
    $http.get('getuser/'+ $scope.userLogin).success(function(data) {
        $scope.user = data;
        if(data.login === $rootScope.userLogin){
        $scope.myElementClass='';
        $scope.foreignElementClass='hidden';
        } else {
            $scope.myElementClass='hidden';
            $scope.foreignElementClass='';
        }

    }).error(function(){
        $location.path("/home/"+$rootScope.userLogin);
    });
    $scope.tasks = [
        {id: 1, name: 'awesadfasdfr1', date: new Date(2017, 4, 15), description: 'dfd', tags: 'admin'},
        {id: 2, name: 'awesdfas', date: undefined, description: 3, tags: 'vip'},
        {id: 3, name: 'awesasdf3', date: 2, description: null}
    ];

    $scope.opened = {};
    $scope.data = {};

    $scope.open = function($event, elementOpened) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened[elementOpened] = !$scope.opened[elementOpened];
    };

    $scope.giveUp = function(index) {
        $scope.tasks.splice(index, 1);
    };

    $scope.done = function(index) {
        $scope.tasks.splice(index, 1);
        // completedTasks++; or smth like that is needed here
    };

    $scope.check = function(data) {
        if (data == '') {
            return "This field is required";
        }
    };

    $scope.addTask = function() {
        //by default:
        $scope.inserted = {
            id: $scope.tasks.length+1,
            name: '',
            date: '',
            description: '',
            tags: ''
        };
        $scope.tasks.push($scope.inserted);
    };
    $scope.saveTask = function(data, id) {
//$scope.user not updated yet
        angular.extend(data, {id: id});
        $http.post('/savetask', data).success(function(data) {

        }).error(function(){
           alert("AJAX ERROR");
        });
    };


});

// $(document).ready( function() {
//     $.fn.editable.defaults.mode = 'popover';
//
// $('.tags').editable({
//     placement: 'right',
//     select2: {
//         tags: ['cake', 'cookies'],
//         tokenSeparators: [",", " "]
//     },
//     display: function(value) {
//         $.each(value,function(i){
//             // value[i] needs to have its HTML stripped, as every time it's read, it contains
//             // the HTML markup. If we don't strip it first, markup will recursively be added
//             // every time we open the edit widget and submit new values.
//             value[i] = "<span class='label'>" + $('<p>' + value[i] + '</p>').text() + "</span>";
//         });
//         $(this).html(value.join(" "));
//     }
// });
//
// $('.tags').on('shown', function() {
//     var editable = $(this).data('editable');
//     var value;
//     value = editable.value;
//     $.each(value,function(i){
//         value[i] = $('<p>' + value[i] + '</p>').text()
//     });
// });
//
// $('[id^="tags-edit-"]').click(function(e) {
//     e.stopPropagation();
//     e.preventDefault();
//     $('#' + $(this).data('editable') ).editable('toggle');
// });
//
// });

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
}