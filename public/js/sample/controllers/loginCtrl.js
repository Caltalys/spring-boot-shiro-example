loginApp.controller('loginCtrl', function ($scope, $http, $window) {
    $scope.login = function(username, passowrd) {
        $http.post('/api/security/login', {username: username, password: passowrd}).success(function () {
            window.location.href = "/views/index.html";
        }).error(function (data) {
            $window.alert('Login Failed');
        });
    }
});
