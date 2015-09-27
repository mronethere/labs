(function (define, angular) {

    'use strict';

    define([
            './controllers/Lab2Controller',
            './controllers/Lab3Controller'
        ],
        function (Lab2Controller, Lab3Controller) {

            var moduleName = 'MethodComp';

            angular
                .module(moduleName, [])
                .controller('Lab2Controller', Lab2Controller)
                .controller('Lab3Controller', Lab3Controller)
                .config(function ($stateProvider) {
                    $stateProvider
                        .state('methodcomp', {
                            url: '/methodcomp/:labid',
                            templateUrl: function ($stateParams){
                                return '/app/methodcomp/views/lab' + $stateParams.labid + '.view.html'
                            }
                        })
                });
            return moduleName;
        }
    );
})(define, angular);