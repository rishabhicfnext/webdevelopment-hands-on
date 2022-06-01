function getWeather() {
    const servletUrl = $('#weather').data('weather-path') + '.bangalore.json';
	$.ajax({
        url: servletUrl,
        type: 'GET',
        success: function (response) {
            try {
                console.log('response'+response);
                $('#city').html(response.name);
                $('#city-weather').html(response.weather[0].main + '-' + response.weather[0].description);
                $('#city-windspeed').html(response.wind.speed);
            } catch(err){
                console.log("Error occurred while fetching weather info::"+ response);
            }
        },
        error: function (xhr) {
            console.log("Failure while fetching weather info"+xhr.responseText);
        }
    });
}