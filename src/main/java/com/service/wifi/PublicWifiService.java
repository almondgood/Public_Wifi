package com.service.wifi;

import com.config.WifiConfig;
import com.dao.wifi.WifiDao;
import com.domain.Wifi;

import java.util.*;

public class PublicWifiService implements WifiService {
    private final int LIST_TOTAL_COUNT = 20; // 요구사항 개수

    public Map<Double, Queue<Wifi>> getNearWifiMap(double myLat, double myLnt) {
        WifiConfig wifiConfig = new WifiConfig();
        WifiDao wifiDao = wifiConfig.wifiDao();

        List<Wifi> wifiList = wifiDao.selectWifiList();

        Map<Double, Queue<Wifi>> nearWifiMap = new TreeMap<>(Collections.reverseOrder());


        int size = 0;
        double mostFarDistance = 0;
        for (Wifi wifi : wifiList) {

            double distance = calcDistance(myLat, myLnt,
                    wifi.getLat(), wifi.getLnt());


            // 요구 조건 개수 이하
            if (size < LIST_TOTAL_COUNT) {
                addWifiQueue(nearWifiMap, wifi, distance);
                size++;

                // 가장 먼 와이파이 거리
                if (size == LIST_TOTAL_COUNT) {
                    mostFarDistance = getMostFarDistance(nearWifiMap);
                }

            } else {
                // 요구 조건 개수 초과
                Queue<Wifi> queue = nearWifiMap.get(mostFarDistance);

                // 가장 멀리 있는 와이파이 빼고 가까운 와이파이 넣기
                if (distance < mostFarDistance) {
                    addWifiQueue(nearWifiMap, wifi, distance);
                    queue.poll();
                }

                // 가장 먼 큐가 비어 있으면 삭제, 갱신(메모리 누수 방지)
                if (queue.isEmpty()) {
                    nearWifiMap.remove(mostFarDistance);
                    mostFarDistance = getMostFarDistance(nearWifiMap);
                }
            }
        }


        return nearWifiMap;
    }

    private void addWifiQueue(Map<Double, Queue<Wifi>> nearWifiMap, Wifi wifi, double distance) {
        if (nearWifiMap.containsKey(distance)) {
            nearWifiMap.get(distance).add(wifi);
        } else {
            Queue<Wifi> queue = new PriorityQueue<>(Wifi::compareTo);
            queue.add(wifi);

            nearWifiMap.put(distance, queue);
        }
    }

    private double getMostFarDistance(Map<Double, Queue<Wifi>> nearWifiMap) {
        return nearWifiMap.keySet()
                .stream()
                .findFirst()
                .get();
    }



    // 거리 계산 api
    private double calcDistance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;


        dist = dist * 1.609344;


        return (dist);
    }


    // This function converts decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    // 거리 계산 api
}

