(function (define, angular) {

    'use strict';

    define([],
        function () {

            var moduleName = 'MethodComp';

            angular
                .module(moduleName, [])
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