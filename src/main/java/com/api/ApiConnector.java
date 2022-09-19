package com.api;

import java.io.IOException;

public interface ApiConnector {
    String loadApiData(int page) throws IOException;
}
