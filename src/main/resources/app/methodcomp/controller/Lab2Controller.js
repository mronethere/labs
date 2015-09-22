
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab2Controller = function ($http) {
                var controller = this;
                controller.E = 0.0001;
                controller.swap = false;
                controller.func = 'Math.log(x) - 1 / (x * x)';
                controller.A = 0.0001;
                controller.B = 8;
                this.submit = function() {
                    var labData = {
                        projectName: "methodcomp",
                        labId: 2,
                        params: [
                            controller.E.toString(),
                            controller.swap.toString(),
                            controller.func.toString(),
                            controller.A.toString(),
                            controller.B.toString()
                        ],
                        isSuccess: true
                    };
                    $http
                        .post('/lab', labData)
                        .then(function (response) {
                            console.dir(response);
                            if (response.data['isSuccess']) {
                                controller.rez = response.data['params'][0];
                            }
                        }, function (response) {
                            console.log('failed');
                            console.dir(response)
                        });
                }
            };
            return ['$http', Lab2Controller];
        }
    );
})(define);