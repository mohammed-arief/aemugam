package com.aem.ugam.core.models;

import com.aem.ugam.core.helper.MultifieldHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuthorBooks {

    String getAuthorName();

    List<String> getAuthorBooks();

    List<Map<String,String>> getBookDetailsWithMap();

    List<MultifieldHelper> getBookDetailsWithBean();

    List<MultifieldHelper> getBookDetailsWithNestedMultifield();

    public Map<String,String> getBooksMap();

    public Calendar getPublishDate();
}
