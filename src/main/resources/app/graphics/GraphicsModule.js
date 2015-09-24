(function (define, angular) {

    'use strict';

    define([
            './controllers/Lab1Controller'
        ],
        function (Lab1Controller) {

            var moduleName = 'Graphics';

            angular
                .module(moduleName, [])
                .controller('Lab1Controller', Lab1Controller)
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