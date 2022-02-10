package com.aem.ugam.core.models;

import com.aem.ugam.core.helper.MultifieldHelper;
import com.day.cq.wcm.api.Page;

import java.util.*;

public interface AuthorBooks {

    String getAuthorName();

    List<String> getAuthorBooks();

    List<Map<String,String>> getBookDetailsWithMap();

    List<MultifieldHelper> getBookDetailsWithBean();

    List<MultifieldHelper> getBookDetailsWithNestedMultifield();

    public Map<String,String> getBooksMap();

    public Calendar getPublishDate();

    public AuthorBean getAuthorInfo();

    public String[] getBookArr();

    public List<String> getBookList();

    public Iterator<Page> getPageIterator();
}
