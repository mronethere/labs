
(function (define) {

    'use strict';

    define([],
        function () {
            var $labs = function ($http) {
                return {
                    process: function (name, num, args) {
                        var labData = {
                            projectName: name,
                            labId: num,
                            params: [],
                            isSuccess: true
                        };
                        for (var i in args) {
                            labData.params.push(args[i].toString());
                        }
                        return $http
                            .post('/lab', labData).then(function (response) {
                                if (response.data['isSuccess'] == false) {
                                    console.log('unsuccessful request: ' + response)
                                }
                                return response.data;
                            });
                    }
                };
            };
            return ['$http', $labs];
        }
    );
})(define);