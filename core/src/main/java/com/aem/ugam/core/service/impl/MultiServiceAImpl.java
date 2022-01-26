package com.aem.ugam.core.service.impl;

import com.aem.ugam.core.service.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MultiService.class,immediate = true,name = "serviceA")
@ServiceRanking(1000)
public class MultiServiceAImpl implements MultiService {

    @Override
    public String getName() {
        return "MultiServiceAImpl";
    }
}
