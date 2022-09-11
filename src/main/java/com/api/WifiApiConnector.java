package com.api;

import com.api.detail.ApiDetail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

@RequiredArgsConstructor
public class WifiApiConnector implements ApiConnector {

    @NonNull
    private ApiDetail apiDetail;

    @Override
    public String loadApiData(int startIndex, int endIndex) throws IOException {

        Response response = getResponse(getUrl(startIndex, endIndex));

        return getFile(response);
    }

    public int increaseIndex(int index) {
        return index + 1000;
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

    private Response getResponse(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder().url(url).get();
        Request request = builder.build();

        return client.newCall(request).execute();
    }

    private String getUrl(int startIndex, int endIndex) {
        StringBuilder urlBuilder = new StringBuilder()
                .append(apiDetail.url()).append('/')
                .append(apiDetail.authKey()).append('/')
                .append(apiDetail.fileType()).append('/')
                .append(apiDetail.title()).append('/')
                .append(startIndex).append('/')
                .append(endIndex).append('/');

        return urlBuilder.toString();
    }
}