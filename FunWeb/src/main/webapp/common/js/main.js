require.config({
    baseUrl: "",
    // alias libraries paths
    paths: {
        'application-configuration': 'common/js/application-configuration',       
        'angular': 'common/js/angular.min',
        'angular-route': 'common/js/angular-route.min',
        'angularAMD': 'common/js/angularAMD',
        'ui-bootstrap' : 'common/js/ui-bootstrap-tpls-0.11.0',
        'blockUI': 'common/js/angular-block-ui.min',
        'ngload': 'common/js/ngload',
        'angular-sanitize': 'common/js/angular-sanitize.min',
        
        	
        'ajaxService': 'common/app/shared/service/ajaxService',
        'alertService': 'common/app/shared/service/alertService',
        
        'accountService': 'common/app/components/service/account/accountService'       
        
        /*'customersController': 'Views/Shared/CustomersController',*/
    },
    // Add angular modules that does not support AMD out of the box, put it in a SHIM
    shim: {
        'angularAMD': ['angular'],
        'angular-route': ['angular'],
        'blockUI': ['angular'],
        'angular-sanitize': ['angular'],
        'ui-bootstrap': ['angular']
         
    },
    // kick start application
    deps: ['application-configuration']
});