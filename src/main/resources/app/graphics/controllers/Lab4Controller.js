(function (define) {

    'use strict';

    define([],
        function () {
            var Lab4Controller = function ($labs) {
                var controller = this;
                var width = document.getElementById('ctrl').offsetWidth * 0.9;
                var height = 400;
                var canvas = document.getElementById('field');
                var ctx = canvas.getContext('2d');
                canvas.width = width;
                canvas.height = height;
                var dx = width / 2;
                var dy = height / 2;
                init();

                this.submit = function() {
                    $labs.process('graphics', 4, [
                        controller.x1, controller.y1, controller.x2, controller.y2,
                        controller.xMin, controller.yMin, controller.xMax, controller.yMax
                    ]).then(function (data) {
                        if (data['isSuccess'] && data['params'].length % 2 == 0) {
                            var i;
                            var points = data.params;
                            var arr = [];
                            for (i = 0; i < points.length; i+=2) {
                                drawPoint(parseInt(points[i]), parseInt(points[i+1]), arr);
                            }
                            var rez = '';
                            for (i = arr.length - 1; i >= 0; i--) {
                                rez += '(' + arr[i].x + ',' + arr[i].y + ') ';
                            }
                            console.log(rez);
                        } else {
                            console.dir(data);
                        }
                    });
                };

                this.reset = function() {
                    ctx.clearRect(0, 0, width, height);
                    init();
                };

                function init() {
                    ctx.fillStyle='#FF0000';
                    ctx.fillRect(width / 2, 0, 1, height);
                    ctx.fillRect(0, height / 2, width, 1);
                    ctx.fillStyle='#000000';
                }

                function drawPoint(x, y, arr) {
                    arr.push({'x' : x, 'y': y});
                    ctx.fillRect(x + dx, dy - y, 1, 1);
                }
            };
            return ['$labs', Lab4Controller];
        }
    );
})(define);