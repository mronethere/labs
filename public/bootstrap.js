(function (define, require) {

    "use strict";

    require.config({
        paths: {
            "angular": "./bower_components/angular/angular",
            "ngBootstrap": "./bower_components/angular-bootstrap/ui-bootstrap-tpls"
        },
        shim: {
            "angular": { exports: "angular" },
            "ngBootstrap": { deps: ["angular"] }
        }
    });


    define(["angular", "ngBootstrap"], function (angular) {
        require(["main"], function (app) {
            angular.bootstrap(document.getElementsByTagName("body")[0], [app]);
        });
    });

})(window.define, window.require);