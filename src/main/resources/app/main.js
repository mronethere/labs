(function (define, angular) {

    "use strict";

    define([], function () {
            var appName = "labs";
            angular
                .module(appName, ["ui.router", "ui.bootstrap"])
                .config(function ($stateProvider, $urlRouterProvider, $stateParams) {
                    $urlRouterProvider.otherwise("/home");

                    $stateProvider
                        .state("home", {
                            url: "/home",
                            templateUrl: "/app/main.view.html"
                        })
                        .state("methodcomp", {
                            url: "/methodcomp",
                            templateUrl: "/app/methodcomp/methodcomp.view.html"
                        })
                        .state("methodcomp.lab", {
                            url: "/methodcomp/lab/:lab_id",
                            templateUrl: "/app/methodcomp/lab" + $stateParams.lab_id + ".view.html"
                        });
                });
            return appName;
        }
    );
})(window.define, window.angular);