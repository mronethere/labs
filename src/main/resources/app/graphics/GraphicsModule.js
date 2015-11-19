(function (define, angular) {

    'use strict';

    define([
            './controllers/Lab1Controller',
            './controllers/Lab3Controller',
            './controllers/Lab4Controller'
        ],
        function (Lab1Controller, Lab3Controller, Lab4Controller) {

            var moduleName = 'Graphics';

            angular
                .module(moduleName, [])
                .controller('Lab1Controller', Lab1Controller)
                .controller('Lab3Controller', Lab3Controller)
                .controller('Lab4Controller', Lab4Controller)
                .config(function ($stateProvider) {
                    $stateProvider
                        .state('graphics', {
                            url: '/graphics/:labid',
                            templateUrl: function ($stateParams){
                                return '/app/graphics/views/lab' + $stateParams.labid + '.view.html'
                            }
                        })
                });
            return moduleName;
        }
    );
})(define, angular);