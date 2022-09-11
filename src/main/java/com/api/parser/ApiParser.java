package com.api.parser;

import com.domain.Wifi;

public interface ApiParser {
    Wifi[] parsing(String file);
    boolean isPageEnd(int endIndex);
}
