var app = angular.module("barrierManager", []);
app.controller('DSController', function ($scope, $http) {
    $http({
        method: "GET",
        url: "/global/get-all"
    }).then(function (response) {
        $scope.settings = response.data;
    });

    $scope.saveSettings = function () {
        $http({
            method: "POST",
            url: "/global/save",
            data: $scope.detect
        }).then(function (response) {
            $scope.settings = response.data;
        });
    };
});

app.controller('CameraController', function ($scope, $http) {
    $http({
        method: "GET",
        url: "/camera/get/all"
    }).then(function (response) {
        $scope.cameras = response.data;
    });

    $scope.saveCamera = function () {
        $http({
            method: "POST",
            url: "/camera/save",
            data: $scope.camera
        }).then(function (response) {
            $scope.cameras = response.data;
            $scope.camera = {};
        });
    };

    $scope.getCamera = function ($cam) {
        $http({
            method: "GET",
            url: "/camera/get/" + $cam.id
        }).then(function (response) {
            $scope.camera = response.data;
        });
    };



    $scope.deleteCamera = function () {
        $http({
            method: "GET",
            url: "/camera/delete/" + id
        }).then(function (response) {
            $scope.cameras = response.data;
        });
    }
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

app.controller('DetectionController', function ($scope, $http) {
    $scope.getAllCars = function () {
        $http({
            method: "GET",
            url: "/recognition/get-detected"
        }).then(function (response) {
            $scope.cars = response.data;
        });
    };



    $scope.startStream = function () {
        $http({
            method: "GET",
            url: "/recognition/start-streaming"
        }).then(function (response) {
            $scope.message = response.data;
        });
    };

    $scope.startDetect = function () {
        $http({
            method: "GET",
            url: "/recognition/detect-car"
        }).then(function (response) {
            $scope.cars = response.data;
        });
    };
});

app.controller('DriversController', function ($scope, $http) {
    // function getRequest(id) {
    //     //     return $http.get('/show-departure')
    //     //         .success(function (response) {
    //     //             $scope.departure = response.data;
    //     //         })
    //     //         .error(function (err) {});
    //     // }
    //     //
    //     // var promise = $timeout();
    //     //
    //     // promise = promise.then(function() {
    //     //     getRequest();
    //     //     return $timeout(5000);
    //     // });

    // setTimeout(function() {
    //     return $http.get('/show-departure')
    //         .success(function(response) {
    //             $scope.departure = response.data;
    //         })
    //         .error(function(err) {
    //         })
    // },10000);


    setTimeout(function() {
        $http({
            method: "GET",
            url: "/show-departure"
        }).then(function (response) {
            $scope.departure = response.data;
        })
    }, 5000);
});
