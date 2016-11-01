angular.module('starter.services', [])

.service('getWeatherByCityService', function ($http) {

  var url = 'http://api.openweathermap.org/data/2.5/weather?q=';
  var appID = '6f0003d0842a175ea9003bfecf8121b7';

  this.getWeather  = function (city) {

    return $http({
      method: 'GET',
      url: url + city,
      params: {
        appid: appID
      },
    });
  };

});
