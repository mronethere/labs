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