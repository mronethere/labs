
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab1Controller = function ($http) {
                var controller = this;

                var labData = {
                    projectName: "graphics",
                    labId: 1,
                    params: [],
                    isSuccess: true
                };

                var canvas = document.getElementById("field");
                var ctx = canvas.getContext("2d");
                canvas.width = document.getElementById("lab2g").offsetWidth * 0.9;
                canvas.height = 400;

                this.submit = function() {
                    labData.params = [];
                    labData.params.push(
                        controller.x1.toString(),
                        controller.y1.toString(),
                        controller.x2.toString(),
                        controller.y2.toString()
                    );
                    $http
                        .post('/lab', labData)
                        .then(function(response) {
                            if (response.data['isSuccess'] && response.data['params'].length % 2 == 0) {
                                var points = response.data.params;
                                for (var i = 0; i < points.length; i+=2) {
                                    drawPoint(points[i], points[i+1]);
                                }
                            } else {
                                console.dir(response);
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
            return ['$http', Lab1Controller];
        }
    );
})(define);