package com.api.parser;

import com.domain.Wifi;

import java.util.List;

public interface ApiParser {
    List<Wifi> parsing(String file);
    int getListTotalCount();
}
