angular.module('starter.controllers', [])

.controller('weatherCtrl', function ($scope, $http, $cordovaGeolocation,$q ,getWeatherByCityService) {

  $scope.cities = [
    {name: "Medellin", "active":true},
    {name: "Bello", "active":true},
    {name: "Cali", "active":true},

  ];

  $scope.cities = $scope.cities.sort(function(a, b) {

  	var cityA = a.name.toLowerCase();
  	var cityB = b.name.toLowerCase();

  	if(cityA > cityB) return 1;
  	if(cityA < cityB) return -1;
  	return 0;
  });

  $scope.city = '';
  $scope.data = '';
  $scope.description = '';
  $scope.temperature = 0;
  $scope.humidity = 0;

  $scope.latitude = 0;
  $scope.longitude = 0;

  var posOptions = {timeout: 10000, enableHighAccuracy: false};
   $cordovaGeolocation
   .getCurrentPosition(posOptions)

   .then(function (position) {
      var lat  = position.coords.latitude;
      var long = position.coords.longitude;
      console.log(lat + '   ' + long);
      query = 'lat='+lat+'&lon='+long;
      getWeatherByCityService.getWeather(query).then(function successCallback(response) {
        $scope.data = response.data;
        console.log($scope.data);
        updateUI();
      }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
      });
   }, function(err) {
      console.log(err);
   });


  $scope.searchByCity = function(city) {
    query = 'q='+city;

    getWeatherByCityService.getWeather(query).then(function successCallback(response) {
      $scope.data = response.data;
      console.log($scope.data);
      updateUI();

    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });

  };

   var kelvinToCelsius = function (fahr){
        temperature = fahr-273;
        return  temperature;
    };

    var updateUI = function () {
      $scope.city = $scope.data.name;
      $scope.temperature = kelvinToCelsius($scope.data.main.temp);
      $scope.humidity = $scope.data.main.humidity;
      $scope.description = $scope.data.weather[0].description;
    };


    $scope.callbackMethod = function (query, isInitializing) {
    if(isInitializing) {
        // depends on the configuration of the `items-method-value-key` (items) and the `item-value-key` (name) and `item-view-value-key` (name)
        return { items: [ { name: "test" } ] };
    } else {
        //return $http.get(endpoint);
    }
};

});
