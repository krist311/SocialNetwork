'use strict';
var Network = angular.module('myNetwork',['ngRoute','ngResource',"xeditable","ui.bootstrap"]);

Network.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});

Network.controller('EditableRowCtrl', function($scope, $filter, $http) {

    $scope.tasks = [
        {id: 1, name: 'awesadfasdfr1', date: new Date(2017, 4, 15), description: 'dfd', tags: 'admin'},
        {id: 2, name: 'awesdfas', date: undefined, description: 3, tags: 'vip'},
        {id: 3, name: 'awesasdf3', date: 2, description: null}
    ];

    $scope.opened = {};

    $scope.open = function($event, elementOpened) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened[elementOpened] = !$scope.opened[elementOpened];
    };

    $scope.saveTask = function(data, id) {
        //THERE MUST BE THE MESSAGE TO DB
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
            // name: '',
            // date: '',
            // description: 'One good deed for the benefit of all life on the Earth',
            // tags: 'kindness'
        };
        $scope.tasks.push($scope.inserted);
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