var city;
var lat;
var lon;

window.addEventListener('load', function(){
    navigator.geolocation.getCurrentPosition(success);

    function success(pos){

        let crd=pos.coords;
        console.log(crd);
        getWeatherbyLoc(crd);
    }
})

// Function to update the digital clock
document.addEventListener("DOMContentLoaded", function() {
    // Function to update the digital clock
    function updateDigitalClock() {
        var now = new Date();
        var hours = now.getHours().toString().padStart(2, '0');
        var minutes = now.getMinutes().toString().padStart(2, '0');
        var seconds = now.getSeconds().toString().padStart(2, '0');
        var ampm = hours >= 12 ? 'P.M' : 'A.M';
        
        // Convert to 12-hour format
	    hours = hours % 12;
	    hours = hours ? hours : 12; // Handle midnight (0 hours)
	    
	    
        var timeString = hours + ':' + minutes + ':' + seconds + " ";

        // Update the content of the digital clock element
        var digitalClock = document.getElementById('digital-clock');
        if (digitalClock) {
            digitalClock.innerHTML = `<h1> <span> ${timeString} <span> <span id="ampm"> ${ampm} <\span> <\h1>`;
        } else {
            console.error('Element with ID "digital-clock" not found.');
        }
    }

    // Call the update function initially and set an interval to update every second
    updateDigitalClock();
    setInterval(updateDigitalClock, 1000);
});

async function getWeatherbyLoc(crd){
    try{
        lat=crd.latitude;
        lon=crd.longitude;
        let res= await fetch(`http://localhost:8080/weather/${lat}/${lon}`)

        let data= await res.json();
        console.log(data);
        appendData(data)
    }catch(err){
        console.log('err:', err);
    }
}

async function getWeather(){
    try{
        city = document.querySelector("#city").value
        
        if(city == undefined || city == ''){
			alert("Please add a city to search.!");
			return;
		}

        let res= await fetch(`http://localhost:8080/weather/${city}`)

        let data= await res.json();
        console.log(data);
        appendData(data)
    }catch(err){
        console.log('err:', err);
    }
}

function appendData(data){

    weather_container.innerHTML=null;
	
	let headCon = document.createElement('div');
	headCon.setAttribute('class', 'weather_details');

    let name=document.createElement('p');
    name.setAttribute('id','name');
    name.setAttribute('title', 'Location');
    name.innerHTML=` <i class="fa-solid fa-location-crosshairs"></i> ${data.name}`;
    headCon.append(name);
    
    let weatherType = document.createElement('p');
    weatherType.setAttribute('id', 'weatherType');
    weatherType.innerHTML = `${data.weather[0].description}`
    headCon.append(weatherType);

	let tempDiv = document.createElement('div');
    tempDiv.setAttribute('class','weather_details');
    
    let temp=document.createElement('p');
    temp.setAttribute('id','temp');
    temp.setAttribute('title', 'Temperature');
    temp.innerHTML=` <i class="fa-solid fa-temperature-three-quarters" style="color: #0d1421;"></i>  ${data.main.temp} °C`
	tempDiv.append(temp)
	
    let temp_feel=document.createElement('p');
    temp_feel.setAttribute('id','feel')
	temp_feel.setAttribute('title', 'Feels like');
    temp_feel.innerText=`Feels like ${data.main.feels_like}°C`
    tempDiv.append(temp_feel);

	let maxMinDiv = document.createElement('div');
    maxMinDiv.setAttribute('class','weather_details');    
    let temp_max=document.createElement('p');
    temp_max.setAttribute('id','temp_max');
    temp_max.setAttribute('title', 'Maximum Temperature')
    temp_max.innerHTML=`<i class="fa-solid fa-temperature-arrow-up" style="color: #db0626;"></i> ${data.main.temp_max}°C`
	maxMinDiv.append(temp_max);

	let temp_min=document.createElement('p');
    temp_min.setAttribute('id','temp_min');
	temp_min.setAttribute('title', 'Minimum Temperature');
    temp_min.innerHTML=`<i class="fa-solid fa-temperature-arrow-down" style="color: #0659ea;"></i> ${data.main.temp_min}°C`
	maxMinDiv.append(temp_min);

	let windHumidDiv = document.createElement('div');
    windHumidDiv.setAttribute('class','weather_details');
    let wind=document.createElement('p');
	wind.setAttribute('title', 'Wind Speed');
    wind.innerHTML=` <i class="fa-solid fa-wind" style="color: #042c71;"></i> ${data.wind.speed} m/s`;
	windHumidDiv.append(wind);

    let humidity=document.createElement('p');
	humidity.setAttribute('title', 'Humidity'); 
    humidity.innerHTML=`<i class="fa-solid fa-droplet" style="color: #4c85e6;"></i> ${data.main.humidity}%`
    windHumidDiv.append(humidity);
    
    let cloudsPressDiv = document.createElement('div');
    cloudsPressDiv.setAttribute('class','weather_details');
    let clouds=document.createElement('p');
	clouds.setAttribute('title', 'Clouds');
    clouds.innerHTML=`<i class="fa-solid fa-cloud" style="color: #4783eb;"></i> ${data.clouds.all}%`
	cloudsPressDiv.append(clouds);

    let pressure=document.createElement('p');
	pressure.setAttribute('title', 'Pressure');
    pressure.innerText=`Pressure:- ${data.main.pressure}hPa`
    cloudsPressDiv.append(pressure);
    
    let sunDiv = document.createElement('div');
    sunDiv.setAttribute('class','weather_details');
    let sunrise=document.createElement('p');
	sunrise.setAttribute('title', 'Sunrise');
    let sunriseTime = convertUnixTimeToDateTime(data.sys.sunrise);
    sunrise.innerHTML=`<i class="fa-solid fa-sun" style="color: #ece509;"></i> ${sunriseTime}`
    sunDiv.append(sunrise);
    
    let sunset=document.createElement('p');
	sunset.setAttribute('title', 'Sunset');
    let sunsetTime = convertUnixTimeToDateTime(data.sys.sunset);
    sunset.innerHTML=`<i class="fa-solid fa-cloud-sun" style="color: #bc5b01;"></i> ${sunsetTime}`
    sunDiv.append(sunset);

	weather_container.append(headCon, tempDiv, maxMinDiv, windHumidDiv, cloudsPressDiv, sunDiv)
}


function convertUnixTimeToDateTime(unixTime) {
    var date = new Date(unixTime * 1000);

    var options = {
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
        hour12: true, // Use 12-hour format
        timeZoneName: 'short'
    };

    var formattedDate = date.toLocaleString('en-US', options);

    return formattedDate;
}


function openHourlyModal() {
    var modal = document.getElementById('hourlyModal');
    modal.style.display = 'block';

    // Fetch and display hourly forecast data
    getHourlyForecast();
}

function closeHourlyModal() {
    var modal = document.getElementById('hourlyModal');
    modal.style.display = 'none';
}

async function getHourlyForecast() {
    try {
		let url = '';
        // Fetch hourly forecast data from the server
        if(city==undefined || city == ''){
			url = `http://localhost:8080/forecast/${lat}/${lon}`
		}else{
			url = `http://localhost:8080/forecast/${city}`;
		}
        let res = await fetch(url);
        let data = await res.json();

        // Display the fetched data in the modal
        console.log(data);
        displayHourlyForecast(data);
    } catch (err) {
        console.log('Error fetching hourly forecast:', err);
    }
}

function displayHourlyForecast(data) {
    // Clear existing content in the container
    var hourlyContainer = document.getElementById('hourlyForecastContainer');
    hourlyContainer.innerHTML = '';
    
    let forecastHeading = document.createElement('h2');
    forecastHeading.setAttribute('id', 'forecastHeading');
    forecastHeading.innerHTML = `Hourly Forecast for ${data.city.name}  ${data.city.country}:`;
    hourlyContainer.append(forecastHeading);

    // Iterate through the hourly forecast data and append it to the container
    data.list.forEach(item => {

		let time = document.createElement('h3');
		time.setAttribute('id', 'hourlyHeading');
		time.innerHTML = `<i class="fa-regular fa-clock"></i> ${item.dt_txt}`;
		
        // Create elements for each forecast entry
        let forecastEntry = document.createElement('div');
        forecastEntry.setAttribute('id', 'forecastContainer');
        
		let temp = document.createElement('div');
		temp.setAttribute('class', 'forecastDetails');
		temp.setAttribute('title', 'Temperature');
		temp.innerHTML = `<i class="fa-solid fa-temperature-three-quarters" style="color: #0d1421;"></i> ${item.main.temp}`;
		forecastEntry.append(temp);
		
		let humidity = document.createElement('div');
		humidity.setAttribute('class', 'forecastDetails');
		humidity.setAttribute('title', 'Humidity');
		humidity.innerHTML = `<i class="fa-solid fa-droplet" style="color: #4c85e6;"></i> ${item.main.humidity}`;
		forecastEntry.append(humidity);
		
		let wind = document.createElement('div');
		wind.setAttribute('class', 'forecastDetails');
		wind.setAttribute('title', 'Wind');
		wind.innerHTML = `<i class="fa-solid fa-wind" style="color: #042c71;"></i> ${item.wind.speed}`;
		forecastEntry.append(wind);
		
		let clouds = document.createElement('div');
		clouds.setAttribute('class', 'forecastDetails');
		clouds.setAttribute('title', 'Clouds');
		clouds.innerHTML = `<i class="fa-solid fa-cloud" style="color: #4783eb;"></i> ${item.clouds.all}`;
		forecastEntry.append(clouds);
		
		let desc = document.createElement('div');
		desc.setAttribute('class', 'forecastDetails');
		desc.innerHTML = item.weather[0].description;
		forecastEntry.append(desc);

        // Append the entry to the container
        hourlyContainer.append(time ,forecastEntry);
    });
}