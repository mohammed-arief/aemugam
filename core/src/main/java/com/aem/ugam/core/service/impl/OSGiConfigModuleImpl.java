package com.aem.ugam.core.service.impl;

import com.aem.ugam.core.config.UgamOSGiConfig;
import com.aem.ugam.core.service.OSGiConfigModule;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OSGiConfigModule.class , immediate = true)
@Designate(ocd = UgamOSGiConfig.class)
public class OSGiConfigModuleImpl implements OSGiConfigModule {

    private int serviceId;
    private String serviceName;
    private String serviceURL;

    @Activate
    protected void activate(UgamOSGiConfig ugamOSGiConfig){
        serviceId=ugamOSGiConfig.serviceID();
        serviceName=ugamOSGiConfig.serviceName();
        serviceURL=ugamOSGiConfig.serviceURL();
    }
    @Override
    public int getServiceId() {
        return serviceId;
    }
    @Override
    public String getServiceName() {
        return serviceName;
    }
    @Override
    public String getServiceURL() {
        return serviceURL;
    }
}
