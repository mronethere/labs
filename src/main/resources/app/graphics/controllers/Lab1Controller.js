
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab1Controller = function ($labs) {
                var controller = this;
                var canvas = document.getElementById("field");
                var ctx = canvas.getContext("2d");
                canvas.width = document.getElementById("lab2g").offsetWidth * 0.9;
                canvas.height = 400;

                this.submit = function() {
                    $labs.process('graphics', 1, [
                        controller.x1, controller.y1,
                        controller.x2, controller.y2
                    ]).then(function (data) {
                        if (data['isSuccess'] && data['params'].length % 2 == 0) {
                            var points = data.params;
                            for (var i = 0; i < points.length; i+=2) {
                                drawPoint(points[i], points[i+1]);
                            }
                        } else {
                            console.dir(data);
                        }
                    });
                };

                this.reset = function() {
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                };

                function drawPoint(x, y) {
                    ctx.fillRect(x, y, 1, 1);
                }
            };
            return ['$labs', Lab1Controller];
        }
    );
})(define);