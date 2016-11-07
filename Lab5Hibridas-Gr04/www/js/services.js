angular.module('starter.services', [])

.service('getWeatherByCityService', function ($http) {

  var url = 'http://api.openweathermap.org/data/2.5/weather?';
  var appID = '6f0003d0842a175ea9003bfecf8121b7';

  this.getWeather  = function (query) {

    return $http({
      method: 'GET',
      url: url + query,
      params: {
        appid: appID
      },
    });
  };

})
;
