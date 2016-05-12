<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Social network</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myNetwork" class="ng-cloak">
<table>
    <tr>
        <td style="vertical-align: top">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="#/home">Home</a></li>
                <li role="presentation"><a href="#/friends">Friends</a></li>
                <li role="presentation"><a href="#/messages">Messages</a></li>
                <li role="presentation"><a href="#/settings">Settings</a></li>
            </ul>
        </td>
        <td><ng-view></ng-view></td>
    </tr>
</table>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/controller/controllers.js' />"></script>
</body>
</html>