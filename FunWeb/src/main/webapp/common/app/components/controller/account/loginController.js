
"use strict";

define(['application-configuration', 'accountService', 'alertService'], function (app) {

    app.register.controller('loginController', ['$scope', '$rootScope', '$location', 'accountService', 'alertService',
        function ($scope, $rootScope, $location, accountService, alertService) {

            $rootScope.closeAlert = alertService.closeAlert;
            $rootScope.alerts = [];

            $scope.initializeController = function () {
            	$scope.appForm = {};
                $scope.appForm.userName = "";               
                $scope.appForm.password = "";

               

            }

            $scope.processForm = function () {
            	$rootScope.closeAlert = alertService.closeAlert;
            	$rootScope.IsloggedIn = false;
                var user = $scope.appForm;
                accountService.login(user, $scope.loginCompleted, $scope.loginError);
            }

            $scope.loginCompleted = function (response) {
                if(! response){
                	
                }
            	
            	var validationErrors = response.allErrors;
                if(validationErrors !=null && validationErrors.length>0){
                	for(var i = 0;i<validationErrors.length;i++){
                		 alertService.RenderWarningMessage(validationErrors[i].codes[0]);
                	}
                }else{
                	$rootScope.account = response;
                	$location.path('/home/home');
                }
                
            }

            $scope.loginError = function (response) {

                alertService.RenderErrorMessage(response.ReturnMessage);
        
                $scope.clearValidationErrors();
                alertService.SetValidationErrors($scope, response.ValidationErrors);              

            }

            $scope.clearValidationErrors = function () {
              
                $scope.UserNameInputError = false;               
                $scope.PasswordInputError = false;               

            }

            $scope.createLoginCredentials = function () {

                var user = new Object();
               
                user.UserName = $scope.UserName;              
                user.Password = $scope.Password;
             
                return user;

            }

        }]);
});
