package com.api;

import java.io.IOException;

public interface ApiConnector {
    /**
     * @param startIndex
     * @param endIndex
     * @return 데이터를 String 형태로 반환합니다.
     * 응답을 받지 못했거나 내용이 없을 경우 null을 반환합니다.
     * @throws IOException
     */
    String loadApiData(int startIndex, int endIndex) throws IOException;
    int increaseIndex(int index);
}
