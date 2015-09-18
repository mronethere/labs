(function (define, angular) {

    'use strict';

    define([
            './ContainerController',
            '../methodcomp/MethodCompModule',
            '../graphics/GraphicsModule'
        ], function (ContainerController, MethodCompModule, GraphicsModule) {
            var appName = 'labs';
            angular
                .module(appName, ['ui.router', 'ui.bootstrap', MethodCompModule, GraphicsModule])
                .controller('ContainerController', ContainerController)
                .config(function ($stateProvider, $urlRouterProvider) {
                    $urlRouterProvider.otherwise('/home');

                    $stateProvider
                        .state('home', {
                            url: '/home',
                            templateUrl: '/app/main/home.view.html'
                        });
                });
            return appName;
        }
    );
})(define, angular);