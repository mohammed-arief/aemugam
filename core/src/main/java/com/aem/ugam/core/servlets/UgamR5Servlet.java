package com.aem.ugam.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.List;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(
        service= {Servlet.class},
        property={
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                SLING_SERVLET_METHODS+"="+HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes="+ "aemugam/components/structure/ugam-home",
                SLING_SERVLET_PATHS+"="+"/ugam/r5servlet",
                "sling.servlet.selectors=" + "ugam",
                "sling.servlet.selectors=" + "ds",
                SLING_SERVLET_EXTENSIONS+"="+"xml",
                "sling.servlet.extensions"+"="+"txt"
        })
public class UgamR5Servlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UgamR5Servlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        String reqSelectors="SELECTORS => ";
        String reqExtension=req.getRequestPathInfo().getExtension();
        try {
            String[] selectors=req.getRequestPathInfo().getSelectors();
            for(String selector : selectors){
                reqSelectors=reqSelectors +"  "+selector;
            }

        } catch (Exception e) {
            LOG.info("\n ERROR {} ", e.getMessage());
        }

        resp.setContentType("application/json");
        resp.getWriter().write(reqSelectors+ " # " +reqExtension);
    }

    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        try {
            LOG.info("\n ------------------------STARTED POST-------------------------");
            List<RequestParameter> requestParameterList=req.getRequestParameterList();
            for(RequestParameter requestParameter : requestParameterList){
                LOG.info("\n ==PARAMETERS===>  {} : {} ",requestParameter.getName(),requestParameter.getString());
            }
        }catch (Exception e){
            LOG.info("\n ERROR IN REQUEST {} ",e.getMessage());
        }
        resp.getWriter().write("======FORM SUBMITTED========");

    }
}
