package com.aem.ugam.core.servlets;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class UgamResourceTypesServletTest {

    AemContext aemContext = new AemContext();
    UgamResourceTypesServlet unitTest = new UgamResourceTypesServlet();

    @BeforeEach
    void setUp() {
        aemContext.build().resource("/content/ugam/test" , "jcr:title", "Ugam Page");
        aemContext.currentResource("/content/ugam/test");
    }

    @Test
    void doGet() throws ServletException, IOException {

        MockSlingHttpServletRequest request = aemContext.request();
        MockSlingHttpServletResponse response = aemContext.response();

        unitTest.doGet(request , response);

        assertEquals("Page Title = Ugam Page" , response.getOutputAsString());
    }

    @Test
    void doPost() throws IOException, ServletException {

        MockSlingHttpServletRequest request = aemContext.request();
        MockSlingHttpServletResponse response = aemContext.response();

        request.addRequestParameter("fname" , "AEM");
        request.addRequestParameter("lname" , "UGAM");

        unitTest.doPost(request , response);

        assertEquals("AEM" , request.getParameter("fname"));
    }
}