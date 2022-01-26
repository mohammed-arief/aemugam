package com.aem.ugam.core.models.impl;

import com.aem.ugam.core.models.ServiceDemo;
import com.aem.ugam.core.service.DemoService;
import com.aem.ugam.core.service.DemoServiceB;
import com.aem.ugam.core.service.MultiService;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = ServiceDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl.class);

    @OSGiService
    DemoService demoService;
    /*@Inject*/
    @OSGiService
    DemoServiceB demoServiceB;

    @OSGiService(filter = "(component.name=serviceA)")
    MultiService multiService;

    @OSGiService(filter = "(component.name=com.aem.ugam.core.service.impl.MultiServiceBImpl)")
    MultiService multiServiceB;


    @Override
    public Iterator<Page> getPagesList(){
        return demoService.getPages();
    }

    @Override
    public List<String> getPageTitleList() {
        return demoServiceB.getPages();
    }

    @Override
    public String getNameFromService() { return multiService.getName(); }

    @Override
    public String getNameFromServiceB() { return multiServiceB.getName(); }

    @Override
    public String getNameWithReference(){
        return demoServiceB.getNameWithReference(); }

    @PostConstruct
    protected  void init() {
        LOG.trace("\n ==============PRINTING LOGS from TRACE================");
        LOG.debug("\n ==============PRINTING LOGS from DEBUG================");
        LOG.info("\n ==============PRINTING LOGS from INFO================");
        LOG.warn("\n ==============PRINTING LOGS from WARN================");
        LOG.error("\n ==============PRINTING LOGS from ERROR================");
    }
}
