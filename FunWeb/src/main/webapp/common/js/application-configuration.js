"use strict";

define(['angularAMD', 'angular-route', 'ui-bootstrap', 'angular-sanitize', 'blockUI', ], function (angularAMD) {
    var app = angular.module("mainModule", ['ngRoute', 'blockUI', 'ngSanitize', 'ui.bootstrap']);
   
    app.filter("leadingZeroes", function () {
        return function (data) {
            var pad = "000" + data;
            pad = pad.substr(pad.length - 3);
            return pad;
        }
    });
   

    app.config(function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.withCredentials = true;
    });

    app.config(function (blockUIConfigProvider) {

        // Change the default overlay message
        blockUIConfigProvider.message("executing...");
        // Change the default delay to 100ms before the blocking is visible
        blockUIConfigProvider.delay(1);
        // Disable automatically blocking of the user interface
        blockUIConfigProvider.autoBlock(false);

    });

    app.config(['$routeProvider', function ($routeProvider) {
   
        $routeProvider

           .when("/", angularAMD.route({
                         
                templateUrl: function (rp) {  return 'common/app/components/view/account/login.html';  },               
                controllerUrl: "common/app/components/controller/account/loginController.js"            

            }))

            .when("/:section/:tree", angularAMD.route({

                templateUrl: function (rp) { return 'common/app/components/view/' + rp.section + '/' + rp.tree + '.html'; },

                resolve: {

                    load: ['$q', '$rootScope', '$location', function ($q, $rootScope, $location) {

                        var path = $location.path();
                        var parsePath = path.split("/");
                        var parentPath = parsePath[1];
                        var controllerName = parsePath[2];

                        var loadController = "common/app/components/controller/" + parentPath + "/" + controllerName + "Controller";                 

                        var deferred = $q.defer();
                        require([loadController], function () {
                            $rootScope.$apply(function () {
                                deferred.resolve();
                            });
                        });
                        return deferred.promise;
                    }]
                }

            }))

            .when("/:section/:tree/:id", angularAMD.route({

                templateUrl: function (rp) { return 'views/' + rp.section + '/' + rp.tree + '.html'; },

                resolve: {

                    load: ['$q', '$rootScope', '$location', function ($q, $rootScope, $location) {

                        var path = $location.path();
                        var parsePath = path.split("/");
                        var parentPath = parsePath[1];
                        var controllerName = parsePath[2];

                        var loadController = "common/app/components/controller/" + parentPath + "/" + controllerName + "Controller";
                                             
                        var deferred = $q.defer();
                        require([loadController], function () {
                            $rootScope.$apply(function () {
                                deferred.resolve();
                            });
                        });
                        return deferred.promise;
                    }]
                }

            }))


            .otherwise({ redirectTo: '/error.html' }) 

    }]);


    var indexController = function ($scope, $rootScope, $http, $location, blockUI) {
             
    };

    indexController.$inject = ['$scope', '$rootScope', '$http', '$location', 'blockUI'];
    app.controller("indexController", indexController);
  
    // Bootstrap Angular when DOM is ready
    angularAMD.bootstrap(app);

  
    return app;
});


