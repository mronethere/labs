(function (define, angular) {

    'use strict';

    define([
            './LabService',
            '../methodcomp/MethodCompModule',
            '../graphics/GraphicsModule'
        ], function ($labs, MethodCompModule, GraphicsModule) {
            var appName = 'labs';
            angular
                .module(appName, ['ui.router', 'ui.bootstrap', MethodCompModule, GraphicsModule])
                .factory('$labs', $labs)
                .controller('MenuController', ['$state', function ($state) {
                    this.state = $state;
                    var menu = {
                        methodcomp: 6,
                        graphics: 6
                    };
                    for (var prop in menu) {
                        this[prop] = [];
                        for (var i = 1; i <= menu[prop]; i++) {
                            this[prop].push('#/' + prop + '/' + i);
                        }
                    }
                    this.navClick = function (state) {
                        if (sessionStorage.getItem(state) == null) {
                            sessionStorage.setItem(state, 0);
                        }
                        return this[state][sessionStorage.getItem(state)];
                    }
                }])
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