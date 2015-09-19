(function (define) {

    'use strict';

    define([],
        function () {
            var ContainerController = function ($state) {
                var stateName = $state.$current.name;
                var labCount = {
                    methods: 3,
                    graphics: 0
                }[stateName];

                this.labLinks = function () {
                    var links = [];
                    if (typeof labCount == 'number') {
                        for (var i = 0; i < labCount; i++) {
                            links.push('#' + stateName + '/' + i);
                        }
                    }
                    return links;
                }
            };
            return ['$state', ContainerController];
        }
    );
})(define);