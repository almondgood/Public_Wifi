package com.api;

import com.api.detail.ApiDetail;
import com.config.WifiConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class WifiApiConnector implements ApiConnector {

    private static final WifiApiConnector instance = new WifiApiConnector();
    private final ApiDetail apiDetail;

    @Override
    public String loadApiData(int page) throws IOException {

        return getFile(response(url(page)));
    }

    private String getFile(Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();

            if (body != null) {
                return body.string();
            }
        }
        return null;
    }

    private Response response(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get().build();

        return client.newCall(request)
                .execute();
    }

    private String url(int page) {
        return apiDetail.url() + '/' +
                apiDetail.authKey() + '/' +
                apiDetail.fileType() + '/' +
                apiDetail.title() + '/' +
                startIndex(page) + '/' +
                endIndex(page) + '/';
    }

    private int startIndex(int page) {
        return 1 + (1000 * (page - 1));
    }

    private int endIndex(int page) {
        return 1000 + (1000 * (page - 1));
    }


    private WifiApiConnector() {
        apiDetail = WifiConfig.wifiApiDetail();
    }

    public static WifiApiConnector getInstance() {
        return instance;
    }
}