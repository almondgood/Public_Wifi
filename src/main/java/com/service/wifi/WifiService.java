package com.service.wifi;

import com.domain.Wifi;

import java.util.Map;
import java.util.Queue;

public interface WifiService {
    Map<Double, Queue<Wifi>> getNearWifiMap(double myLat, double myLnt);
}
