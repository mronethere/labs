
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab2Controller = function ($labs) {
                var controller = this;
                controller.E = 0.0001;
                controller.swap = false;
                controller.func = 'Math.log(x) - 1 / (x * x)';
                controller.A = 0.0001;
                controller.B = 8;
                this.submit = function() {
                    $labs.process('methodcomp', 2, [
                        controller.E, controller.swap,
                        controller.func, controller.A, controller.B
                    ]).then(function (data) {
                        if (data['isSuccess']) {
                            controller.rez = data['params'][0];
                        }
                    });
                }
            };
            return ['$labs', Lab2Controller];
        }
    );
})(define);