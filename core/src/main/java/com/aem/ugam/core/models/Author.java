package com.aem.ugam.core.models;

import java.util.List;

public interface Author {
    String getFirstName();
    String getLastName();
    boolean getIsProfessor();
    String getCurrentPageTitle();
    String getReqAttribute();
    String getMyFirstPageName();
    String getLastModifiedBy();
    List<String> getBooks();
}
