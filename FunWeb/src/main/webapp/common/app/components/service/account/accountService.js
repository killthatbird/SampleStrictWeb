"use strict";

define(['application-configuration', 'ajaxService'], function (app) {

    app.register.service('accountService', ['ajaxService', function (ajaxService) {

        this.login = function (user, successFunction, errorFunction) {
            ajaxService.AjaxPostWithNoAuthenication(user, "login/loginAjax.do", successFunction, errorFunction);
        };

    }]);
});