(function (define, angular) {

    'use strict';

    define([
            '../methodcomp/MethodCompModule',
            '../graphics/GraphicsModule'
        ], function (MethodCompModule, GraphicsModule) {
            var appName = 'labs';
            angular
                .module(appName, ['ui.router', 'ui.bootstrap', MethodCompModule, GraphicsModule])
                .controller('MenuController', ['$state', function ($state) {
                    this.state = $state;
                    var menu = {
                        methodcomp: 2,
                        graphics: 2
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