window.onload = function () {
    var map = new Map();
    var rectangle = null;

    // 카카오 지도 설정
    document.getElementById("searchPlaces").onsubmit = function () {
        map.searchPlaces();
        return false;
    }

    kakao.maps.event.addListener(map.getMap(), 'click', function (mouseEvent) {
        document.getElementById("lat").innerText = mouseEvent.latLng.getLat();
        document.getElementById("lng").innerText = mouseEvent.latLng.getLng();

        if (rectangle != null) {
            rectangle.setMap(null);
        }

        rectangle = new kakao.maps.Rectangle({
            bounds: new kakao.maps.LatLngBounds(
                new kakao.maps.LatLng(sw.getY(), sw.getX()),
                new kakao.maps.LatLng(ne.getY(), ne.getX())
            ),
            strokeWeight: 4,
            strokeColor: '#FF3DE5',
            strokeOpacity: 1,
            strokeStyle: 'shortdashdot',
            fillColor: '#FF8AEF',
            fillOpacity: 0.8
        });

        rectangle.setMap(map.getMap());
    });

    document.getElementById("map").onmousedown = function (e) {
        if (e.button == 2 && rectangle != null) {
            rectangle.setMap(null);
        }
    }
    // 카카오 지도 설정 끝

}