(function (define, angular) {

    "use strict";

    define([], function () {
            var appName = "labs";
            angular.module(appName, ["ui.bootstrap"]);
            return appName;
        }
    );
})(window.define, window.angular);