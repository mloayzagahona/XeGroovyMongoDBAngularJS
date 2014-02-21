'use strict';

var as = angular.module('myApp', ['ngRoute', 'ui.bootstrap', 'myApp.controllers', 'myApp.directives']);
as.value('version', '0.1');
as.value('apiUrl', 'http://localhost:8080/xe');
as.config(function($routeProvider, $httpProvider) {
    //$httpProvider.defaults.useXDomain = true;
    //delete $httpProvider.defaults.headers.common['X-Requested-With'];
    //$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $routeProvider
        .when('/', {
            templateUrl: 'views/converter.html',
            controller: 'ConverterCtrl'
        })
        .when('/currencies', {
            templateUrl: 'views/currencylist.html',
            controller: 'CurrencyCtrl'
        })
        .when('/exchanges', {
            templateUrl: 'views/exchangeAdmin.html',
            controller: 'ExchangeCtrl'
        })
        .when('/editExchange/:id', {
            templateUrl: 'views/editExchange.html',
            controller: 'EditExchangeCtrl'
        })
        .when('/historics', {
            templateUrl: 'views/historic.html',
            controller: 'HistoricCtrl'
        })
        .when('/newExchange', {
            templateUrl: 'views/newExchange.html',
            controller: 'NewExchangeCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
    console.log('@X-Requested-With@' + $httpProvider.defaults.headers.common["X-Requested-With"]);
});