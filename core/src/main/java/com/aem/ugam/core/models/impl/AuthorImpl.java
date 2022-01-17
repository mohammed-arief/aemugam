package com.aem.ugam.core.models.impl;

import com.aem.ugam.core.models.Author;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = Author.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class AuthorImpl implements Author {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);

    @SlingObject
    ResourceResolver resourceResolver;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @RequestAttribute(name = "rAttribute")
    private String reqAttribute;

    @ScriptVariable
    Page currentPage; //To display title of current page

    @ResourcePath(path = "/content/aemugam/us/en/my-first-page")@Via("resource")
    Resource resource;

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @Inject
    @Via("resource")
    @Required
    @Default(values = "AEM")
    String fname;

    @ValueMapValue
    @Default(values = "UGAM")
    String lname;

    @Inject
    boolean professor;

    @ValueMapValue
    private List<String> books;

    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public boolean getIsProfessor() {
        return professor;
    }

    @Override
    public String getCurrentPageTitle() {
        return currentPage.getTitle();
    }

    @Override
    public String getReqAttribute() {
        return reqAttribute;
    }

    @Override
    public String getMyFirstPageName() {
        return resource.getName();
    }

    @Override
    public String getLastModifiedBy(){
        return modifiedBy;
    }

    @PostConstruct
    protected void init() {
        LOG.info("\n Inside init {} : {} ", currentPage.getTitle(), resource.getPath());
    }

    @Override
    public List<String> getBooks() {
        if(books!=null){
            return new ArrayList<String>(books);
        }else{
            return Collections.emptyList();
        }
    }
}
