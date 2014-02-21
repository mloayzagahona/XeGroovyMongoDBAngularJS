'use strict';
(function() {
    var as = angular.module('myApp.controllers', []);
    as.controller('CurrencyCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $location, apiUrl) {
            var loadCurr = function() {
                console.log('call load()...');
                $http.get(apiUrl + '/currency/listCurr')
                    .success(function(data, status, headers, config) {
                        $scope.currencies = data;
                        angular.copy($scope.currencies, $scope.copy);
                    });
            }
            loadCurr();
        }
    ]);
    as.controller('ExchangeCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $location, apiUrl) {
            var loadExc = function() {
                console.log('call load()...');
                $http.get(apiUrl + '/currency/listExc')
                    .success(function(data, status, headers, config) {
                        $scope.exchanges = data;
                        angular.copy($scope.exchanges, $scope.copy);
                    });
            }
            loadExc();
            $scope.addExchange = function() {
                console.log('call addExchange');
                $location.path("/newExchange");
            }
            $scope.editExchange = function(index) {
                console.log('call editExchange');
                $location.path('/editExchange/' + $scope.exchanges[index].objid);
            }
        }
    ]);
    as.controller('EditExchangeCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {
            var objid = $routeParams.id;
            var load = function() {
                console.log('call load()...');
                $http.get(apiUrl + '/currency/exchange', {
                    params: {
                        id: objid
                    }
                }).success(function(data, status, headers, config) {
                    $scope.exchange = data;
                    angular.copy($scope.exchange, $scope.copy);
                });
            }
            load();
            $scope.exchange = {};
            $scope.updateExchange = function() {
                console.log('call updateExchange');
                $http.post(apiUrl + '/currency/saveExchange', {
                    params: $scope.exchange
                })
                    .success(function(data, status, headers, config) {
                        console.log("going to Exchange page");
                        $location.path('/exchanges');
                    }).error(function(data, status, headers, config) {
                        console.log(status);
                    });
            }
            $scope.cancelExchange = function() {
                console.log('call cancelExchange');
                $location.path('/exchanges');
            }

            $scope.submitted = false;
            $scope.signupForm = function() {
                if ($scope.myform.$valid) {
                    console.log("posting updated data....");
                    $scope.updateExchange();
                } else {
                    $scope.submitted = false;
                }
            }

        }
    ]);

    as.controller('NewExchangeCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {
            var loadCurr = function() {
                $scope.currencies = [];
                console.log('call loadCurr()...');
                $http.get(apiUrl + '/currency/listCurr')
                    .success(function(data, status, headers, config) {
                        for (var idx in data) {
                            $scope.currencies.push({
                                id: data[idx].id,
                                name: data[idx].name
                            });
                        };
                        angular.copy($scope.currencies, $scope.copy);
                    });
            }
            loadCurr();
            $scope.exchange = {};
            $scope.addExchange = function() {
                console.log('call addExchange');
                $scope.message = null;
                $http.post(apiUrl + '/currency/addExchange', {
                    params: $scope.exchange,
                    headers: {
                        "content-type": "application/json"
                    }
                }).success(function(data, status, headers, config) {
                    console.log("going to Exchange page");
                    $location.path('/exchanges');
                }).error(function(data, status, headers, config) {
                    console.log(data);
                    console.log(status);
                    $scope.message = data;
                });
            }
            $scope.cancelExchange = function() {
                console.log('call cancelExchange');
                $location.path('/exchanges');
            }

            $scope.submitted = false;
            $scope.isNotReadyToSubmit = true;

            $scope.signupForm = function() {
                if ($scope.myform.$valid) {
                    console.log("posting data....");
                    $scope.addExchange();
                } else {
                    $scope.submitted = false;
                }
            }
            $scope.validateCombination = function(fromto) {
                if ($scope.exchange.currFrom === $scope.exchange.currTo) {
                    alert("Please don't select similar currencies");
                    $('select[name=' + fromto + ']').val('');
                    $scope.isNotReadyToSubmit = true;
                } else {
                    $scope.isNotReadyToSubmit = false;
                }
            }

        }
    ]);

    as.controller('ConverterCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {
            $scope.amount = 1;
            $scope.message = null;

            $scope.loadRate = function() {
                $scope.message = null;
                console.log('call loadRate()...');
                $http.post(apiUrl + '/currency/conversion', {
                    params: {
                        from: $scope.exchange.currFrom,
                        to: $scope.exchange.currTo,
                        amount: $scope.amount
                    },
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                }).success(function(data, status, headers, config) {
                    $scope.message = data;
                    angular.copy($scope.currencies, $scope.copy);
                }).error(function(data, status, headers, config) {
                    console.log(data);
                    console.log(status);
                    $scope.message = data;
                });
            }

            var loadCurr = function() {
                $scope.currencies = [];
                console.log('call loadCurr()...');
                $http.get(apiUrl + '/currency/listCurr')
                    .success(function(data, status, headers, config) {
                        for (var idx in data) {
                            $scope.currencies.push({
                                id: data[idx].id,
                                name: data[idx].name
                            });
                        };
                        angular.copy($scope.currencies, $scope.copy);
                    });
            }

            loadCurr();

            $scope.exchange = {};
            $scope.submitted = false;
            $scope.isNotReadyToSubmit = true;

            $scope.convertForm = function() {
                if ($scope.myform.$valid) {
                    console.log("posting data....");
                    $scope.loadRate();
                } else {
                    $scope.submitted = false;
                }
            }

            $scope.validateCombination = function(fromto) {
                if ($scope.exchange.currFrom === $scope.exchange.currTo) {
                    alert('Please dont select similar currencies');
                    $('select[name=' + fromto + ']').val('');
                    $scope.isNotReadyToSubmit = true;
                } else {
                    $scope.isNotReadyToSubmit = false;
                }
            }
        }
    ]);

    as.controller('HistoricCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', 'apiUrl',
        function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {
            var loadHis = function() {
                console.log('call loadHis()...');
                $http.get(apiUrl + '/currency/listHis')
                    .success(function(data, status, headers, config) {
                        $scope.historics = data;
                        angular.copy($scope.historics, $scope.copy);
                    });
            }
            loadHis();

            $scope.message = '';

            $scope.myCallback = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                $('td:eq(2)', nRow).bind('click', function() {
                    $scope.$apply(function() {
                        $scope.someClickHandler(aData);
                    });
                });
                return nRow;
            };

            $scope.someClickHandler = function(info) {
                $scope.message = 'clicked: ' + info.price;
            };

            $scope.columnDefs = [{
                "mDataProp": "currTo",
                "aTargets": [0]
            }, {
                "mDataProp": "currFrom",
                "aTargets": [1]
            }, {
                "mDataProp": "rateFrom",
                "aTargets": [2]
            }, {
                "mDataProp": "rateTo",
                "aTargets": [3]
            }, {
                "mDataProp": "createdAt",
                "aTargets": [4]
            }, {
                "mDataProp": "updatedAt",
                "aTargets": [5]
            }, {
                "mDataProp": "deletedAt",
                "aTargets": [6]
            }];

            $scope.overrideOptions = {
                "bStateSave": true,
                "iCookieDuration": 2419200,
                /* 1 month */
                "bJQueryUI": true,
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": true,
                "bInfo": true,
                "bDestroy": true
            };

        }
    ]);

    as.controller('MainCtrl', function($scope) {
        $scope.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];
    });
}());