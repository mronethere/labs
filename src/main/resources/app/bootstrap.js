(function (define, require) {

    "use strict";

    require.config({
        paths: {
            "angular": "/bower_components/angular/angular",
            "uiRouter": "/bower_components/angular-ui-router/release/angular-ui-router",
            "ngBootstrap": "/bower_components/angular-bootstrap/ui-bootstrap-tpls"
        },
        shim: {
            "angular": { exports: "angular" },
            "uiRouter": { deps: ["angular"] },
            "ngBootstrap": { deps: ["angular"] }
        }
    });


    define(["angular", "ngBootstrap", "uiRouter"], function (angular) {
        require(["main/app"], function (app) {
            angular.bootstrap(document.getElementsByTagName("body")[0], [app]);
        });
    });

})(define, require);