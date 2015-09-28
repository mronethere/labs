
(function (define) {

    'use strict';

    define([],
        function () {
            var Lab3Controller = function ($labs) {
                var lab = this;
                lab.matrix = [[]];

                this.submit = function() {
                    var arr = [];
                    arr.push(lab.size);
                    for (var i in lab.matrix) {
                        for (var j in lab.matrix[i]) {
                            arr.push(lab.matrix[i][j]);
                        }
                    }
                    $labs
                        .process('methodcomp', 3, arr)
                        .then(function (data) {
                            if (data['isSuccess']) {
                                var arr = data['params'];
                                var i = 0;
                                if (arr[i] = 'gaussian') {
                                    var rez = "";
                                    while (arr[++i] != 'cramer' && i < 100) {
                                        rez += arr[i] + ", ";
                                    }
                                    lab.result1 = rez;
                                    rez = "";
                                    while (arr[++i] != 'inverse' && i < 100) {
                                        rez += arr[i] + ", ";
                                    }
                                    lab.result2 = rez;
                                    rez = "";
                                    while (++i < arr.length) {
                                        rez += arr[i] + ", ";
                                    }
                                    lab.result3 = rez;
                                }


                            }
                            console.dir(data);
                        });
                };
                
                this.changeSize = function (size) {
                    lab.matrix =[];
                    for (var i = 0; i < size; i++) {
                        lab.matrix.push([]);
                        for (var j = 0; j < size + 1; j++) {
                            lab.matrix[i].push(0);
                        }
                    }
                }
            };
            return ['$labs', Lab3Controller];
        }
    );
})(define);