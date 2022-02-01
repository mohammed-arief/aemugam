package com.aem.ugam.core.service;

import org.json.JSONObject;

public interface SearchService {
    public JSONObject searchResult(String searchText, int startResult, int resultPerPage);
    public JSONObject searchResultSQL2(String searchPath);
}
