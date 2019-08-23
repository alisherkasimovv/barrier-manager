var app = angular.module("barrierManager", []);
app.controller('DSController', function ($scope, $http) {
    $http({
        method: "GET",
        url: "detection/get-all"
    }).then(function (response) {
        $scope.settings = response.data;
    });

    $scope.saveSettings = function () {
        $http({
            method: "POST",
            url: "detection/save",
            data: $scope.detect
        }).then(function (response) {
            $scope.settings = response.data;
        });
    };
});

app.controller('UserController', function ($scope, $http) {
    $scope.users = [];

    $http({
        method: "GET",
        url: "get-all"
    }).then(function (response) {
        $scope.users = response.data;
    });
});
