(function (define, angular) {

    'use strict';

    define([
            './controllers/Lab2Controller'
        ],
        function (Lab2Controller) {

            var moduleName = 'MethodComp';

            angular
                .module(moduleName, [])
                .controller('Lab2Controller', Lab2Controller)
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