package com.aem.ugam.core.service.impl;

import com.aem.ugam.core.models.ServiceDemo;
import com.aem.ugam.core.service.DemoService;
import com.aem.ugam.core.service.DemoServiceB;
import com.aem.ugam.core.service.MultiService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = DemoServiceB.class, immediate = true)
public class DemoServiceBImpl implements DemoServiceB {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);

    @Reference
    DemoService demoService;

    @Reference(target = "(component.name=com.aem.ugam.core.service.impl.MultiServiceBImpl)")
    MultiService multiService;

    @Override
    public String getNameWithReference(){
        return "Response coming from  "+multiService.getName(); }

    @Override
    public List<String> getPages() {
        List<String> titleList = new ArrayList<String>();

        try {
            Iterator<Page> pages = demoService.getPages();
            while(pages.hasNext()) {
                titleList.add(pages.next().getTitle());
            }
            return titleList;
        } catch (Exception e) {
            LOG.info("\n Exception {} ", e.getMessage());
        }
        return null;
    }
}
