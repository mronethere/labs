(function (define, angular) {

    'use strict';

    define([
            '/app/main/ContainerController',
            '/app/methodcomp/MethodCompModule'
        ], function (ContainerController, MethodCompModule) {
            var appName = 'labs';
            angular
                .module(appName, ['ui.router', 'ui.bootstrap', MethodCompModule])
                .controller("ContainerController", ContainerController)
                .config(function ($stateProvider, $urlRouterProvider) {
                    $urlRouterProvider.otherwise('/home');

                    $stateProvider
                        .state('home', {
                            url: '/home',
                            templateUrl: '/app/home.view.html'
                        });
                });
            return appName;
        }
    );
})(define, angular);