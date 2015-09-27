
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab3Controller = function ($labs) {
                var controller = this;
                controller.matrix = [[]];

                this.submit = function() {
                    $labs.process('methodcomp', 3, [
                        controller.E, controller.swap,
                        controller.func, controller.A, controller.B
                    ]).then(function (data) {
                        if (data['isSuccess']) {
                            controller.rez = data['params'][0];
                        }
                    });
                };
                
                this.changeSize = function (size) {
                    controller.matrix =[];
                    for (var i = 0; i < size; i++) {
                        controller.matrix.push([]);
                        for (var j = 0; j < size + 1; j++) {
                            controller.matrix[i].push(0);
                        }
                    }
                }
            };
            return ['$labs', Lab3Controller];
        }
    );
})(define);