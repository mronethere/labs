(function (define, angular) {

    'use strict';

    define([],
        function () {

            var moduleName = 'Graphics';

            angular
                .module(moduleName, [])
                .config(function ($stateProvider) {

                    $stateProvider
                        .state('graphics', {
                            url: '/graphics',
                            templateUrl: '/app/graphics/views/graphics.view.html'
                        })
                        .state('graphics_labs', {
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