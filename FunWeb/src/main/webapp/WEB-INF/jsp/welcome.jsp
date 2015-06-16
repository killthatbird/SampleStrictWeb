<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href = "common/css/bootstrap.min.css">
<script src= "common/js/angular.min.js"></script>
<script src= "common/js/bootstrap.min.js"></script>
<script src= "common/js/jquery-2.1.4.min.js"></script>
<title>Base Project</title>
</head>
<body>
<div data-ng-app="">
  <p>Name : <input type="text" data-ng-model="name"></p>
  <h1>Hello {{name}}</h1>
</div>
<button class="btn btn-success" data-ng-click="editUser('new')"><span class="glyphicon glyphicon-user"></span>  Create New User
</button>
<script src= "common/js/main.js"></script>
</body>
</html>