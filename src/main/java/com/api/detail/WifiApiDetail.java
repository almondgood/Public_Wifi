package com.api.detail;

public class WifiApiDetail implements ApiDetail {

    private static final WifiApiDetail instance = new WifiApiDetail();

    @Override
    public String url() {
        return "http://openapi.seoul.go.kr:8088";
    }

    @Override
    public String authKey() {
        return "476442754b6a77743634424c534a79";
    }

    @Override
    public String fileType() {
        return apiFileType();
    }

    @Override
    public String title() {
        return "TbPublicWifiInfo";
    }

    private String apiFileType() {
        return ApiFileType.json.toString();
    }


    private WifiApiDetail() {
    }

    public static WifiApiDetail getInstance() {
        return instance;
    }

}
