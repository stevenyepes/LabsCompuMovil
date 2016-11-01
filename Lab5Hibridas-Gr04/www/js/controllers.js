angular.module('starter.controllers', [])

.controller('weatherCtrl', function ($scope, $http, $cordovaGeolocation, getWeatherByCityService) {

  $scope.city = '';
  $scope.data = '';
  console.log($scope.city);
  $scope.temperature = 0;
  $scope.humidity = 0;

  var posOptions = {timeout: 10000, enableHighAccuracy: false};
   $cordovaGeolocation
   .getCurrentPosition(posOptions)

   .then(function (position) {
      var lat  = position.coords.latitude;
      var long = position.coords.longitude;
      console.log(lat + '   ' + long);
   }, function(err) {
      console.log(err);
   });


  $scope.search = function(city) {

    getWeatherByCityService.getWeather(city).then(function successCallback(response) {
      $scope.data = response.data;
      console.log($scope.data);
      $scope.temperature = kelvinToCelsius($scope.data.main.temp);
      $scope.humidity = $scope.data.main.humidity;
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });

  };




   var kelvinToCelsius = function (fahr){
        temperature = fahr-273;
        return  temperature;
    };


});
