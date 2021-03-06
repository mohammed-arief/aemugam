package com.aem.ugam.core.models.impl;

import com.aem.ugam.core.helper.MultifieldHelper;
import com.aem.ugam.core.helper.NestedHelper;
import com.aem.ugam.core.models.Author;
import com.aem.ugam.core.models.AuthorBean;
import com.aem.ugam.core.models.AuthorBooks;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = AuthorBooks.class,
        resourceType = AuthorBooksImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson" , extensions = "json" , selector = "ugam" ,
        options = {
                    @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE" , value = "true"),
                    @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY" , value = "true")
})
@JsonRootName("author book details")

public class AuthorBooksImpl implements AuthorBooks {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);
    static final String RESOURCE_TYPE = "/apps/aemugam/components/content/author-books";

    @Inject
    Resource componentResource;

    @ScriptVariable
    PageManager pageManager;

    @ValueMapValue
    @Default(values = "AEM")
    private String authorName;

    @ValueMapValue
    private List<String> books;


    @Override
    @JsonProperty(value = "author-name")
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public List<String> getAuthorBooks() {
        if(books!=null){
            return new ArrayList<String>(books);
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map<String, String>> getBookDetailsWithMap() {
        List<Map<String, String>> bookDetailsMap=new ArrayList<>();
        try {
            Resource bookDetail=componentResource.getChild("bookdetailswithmap");
            if(bookDetail!=null){
                for (Resource book : bookDetail.getChildren()) {
                    Map<String,String> bookMap=new HashMap<>();
                    bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
                    bookMap.put("booksubject",book.getValueMap().get("booksubject",String.class));
                    bookMap.put("publishyear",book.getValueMap().get("publishyear",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
        }
        LOG.info("\n SIZE {} ",bookDetailsMap.size());
        return bookDetailsMap;
    }

    @Override
    @JsonIgnore
    public List<MultifieldHelper> getBookDetailsWithBean(){
        List<MultifieldHelper> bookDetailsBean=new ArrayList<>();
        try {
            Resource bookDetailBean=componentResource.getChild("bookdetailswithbean");
            if(bookDetailBean!=null){
                for (Resource bookBean : bookDetailBean.getChildren()) {
                    LOG.info("\n PATH Bean {} ",bookBean.getPath());
                    LOG.info("\n BEAN PRO {} ",bookBean.getValueMap().get("bookname",String.class));

                    bookDetailsBean.add(new MultifieldHelper(bookBean));
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details With Bean {} ",e.getMessage());
        }
        return bookDetailsBean;
    }


    @Override
    public List<MultifieldHelper> getBookDetailsWithNestedMultifield() {
        List<MultifieldHelper> bookDetailsNested=new ArrayList<>();
        try {
            Resource bookDetailNested=componentResource.getChild("bookdetailswithnestedmultifield");
            if(bookDetailNested!=null){
                for (Resource bookNested : bookDetailNested.getChildren()) {
                    MultifieldHelper multifieldHelper=new MultifieldHelper(bookNested);
                    if(bookNested.hasChildren()){
                        List<NestedHelper> bookNestedList=new ArrayList<>();
                        Resource nestedResource=bookNested.getChild("bookeditons");
                        for(Resource nested : nestedResource.getChildren()){
                            bookNestedList.add(new NestedHelper(nested));
                        }
                        multifieldHelper.setBookEditons(bookNestedList);
                    }
                    bookDetailsNested.add(multifieldHelper);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details With Nested Multifield {} ",e.getMessage());
        }
        LOG.info("\n SIZE Multifield {} ",bookDetailsNested.size());
        return bookDetailsNested;
    }

    @PostConstruct
    protected void init() {
        LOG.info("\n ========= PRINTING LOGS ==========");
    }

    @Override
    public Map<String,String> getBooksMap() {
        Map<String,String> mapBooks = new HashMap<String,String>();
        mapBooks.put("Key1","Value1");
        mapBooks.put("Key2","Value2");
        mapBooks.put("Key3","Value3");
        mapBooks.put("Key4","Value4");
        return mapBooks;
    }

    public Calendar getPublishDate() {
        return Calendar.getInstance();
    }

    @Override
    public AuthorBean getAuthorInfo() {
        LOG.info("\n ==============CALLING GET AUTHOR INFO===============");
        AuthorBean authorBean = new AuthorBean();
        authorBean.setFirstName("AEM");
        authorBean.setLastName("UGAM");
        return authorBean;
    }

    @Override
    public String[] getBookArr() {
        String[] bookArr = {"Java", "AEM", "UGAM"};
        return bookArr;
    }

    @Override
    public List<String> getBookList() {
        List<String> bookList = new ArrayList<String>();
        bookList.add("BATMAN");
        bookList.add("SUPERMAN");
        bookList.add("WW");
        return bookList;
    }

    @Override
    public Iterator<Page> getPageIterator() {
        Page home = pageManager.getPage("/content/aemugam/us/en");
        Iterator<Page> childs = home.listChildren();
        return childs;
    }
}
