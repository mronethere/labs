(function (define, angular) {

    'use strict';

    define([
            '/app/main/ContainerController'
        ], function (ContainerController) {
            var appName = 'labs';
            angular
                .module(appName, ['ui.router', 'ui.bootstrap'])
                .controller("ContainerController", ContainerController)
                .config(function ($stateProvider, $urlRouterProvider) {
                    $urlRouterProvider.otherwise('/home');

                    $stateProvider
                        .state('home', {
                            url: '/home',
                            templateUrl: '/app/home.view.html'
                        })
                        .state('methodcomp', {
                            url: '/methodcomp',
                            templateUrl: '/app/methodcomp/views/methodcomp.view.html'
                        })
                        .state('methodcomp_labs', {
                            url: '/methodcomp/lab/:labid',
                            templateUrl: function ($stateParams, $state){
                                console.dir($state);
                                return '/app/methodcomp/views/lab' + $stateParams.labid + '.view.html'
                            }
                        })
                });
            return appName;
        }
    );
})(window.define, window.angular);