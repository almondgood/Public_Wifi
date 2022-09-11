var my_location = document.getElementById("get-my-location");

my_location.addEventListener("click", getMyLocation);

function getMyLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {
            var latitude = pos.coords.latitude;
            var longitude = pos.coords.longitude;

            document.getElementById('latitude').value = latitude;
            document.getElementById('longitude').value = longitude;
        });
    } else {
        alert("위치 정보를 받아올 수 없습니다.");
    }
}
