<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href = "common/css/bootstrap.min.css">
<script src= "common/js/angular.min.js"></script>
<script src= "common/js/bootstrap.min.js"></script>
<script src= "common/js/jquery-2.1.4.min.js"></script>
<body>
<div data-ng-app="">
  <p>Name : <input type="text" data-ng-model="name"></p>
  <h1>Hello {{name}}</h1>
</div>
<button class="btn btn-success" data-ng-click="editUser('new')"><span class="glyphicon glyphicon-user"></span>  Create New User
</button>
</body>
<!-- <body data-ng-app="myApp" data-ng-controller="userCtrl">

<div class="container">

<h3>Users</h3>

<input class="ng-valid ng-dirty" type="text" data-ng-model="test">
<h2 class="ng-binding" data-ng-show="test" style=""></h2>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Edit</th>
      <th>First Name</th>
      <th>Last Name</th>
    </tr>
  </thead>
  <tbody>
    <tr data-ng-repeat="user in users">
      <td>
        <button class="btn" data-ng-click="editUser(user.id)">
          <span class="glyphicon glyphicon-pencil"></span>  Edit
        </button>
      </td>
      <td>{{ user.fName }}</td>
      <td>{{ user.lName }}</td>
    </tr>
  </tbody>
</table>

<hr>
<button class="btn btn-success" data-ng-click="editUser('new')">
<span class="glyphicon glyphicon-user"></span>  Create New User
</button>
<hr>

<h3 data-ng-show="edit">Create New User:</h3>
<h3 data-ng-hide="edit">Edit User:</h3>

<form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">First Name:</label>
    <div class="col-sm-10">
    <input type="text" data-ng-model="fName" data-ng-disabled="!edit" placeholder="First Name">
    </div>
  </div> 
  <div class="form-group">
    <label class="col-sm-2 control-label">Last Name:</label>
    <div class="col-sm-10">
    <input type="text" data-ng-model="lName" data-ng-disabled="!edit" placeholder="Last Name">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Password:</label>
    <div class="col-sm-10">
    <input type="password" data-ng-model="passw1" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Repeat:</label>
    <div class="col-sm-10">
    <input type="password" data-ng-model="passw2" placeholder="Repeat Password">
    </div>
  </div>
</form>

<hr>
<button class="btn btn-success" data-ng-disabled="error || incomplete">
<span class="glyphicon glyphicon-save"></span>  Save Changes
</button>

</div>

<script src= "common/js/main.js"></script>

</body> -->
</html>