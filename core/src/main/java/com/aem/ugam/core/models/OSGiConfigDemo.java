package com.aem.ugam.core.models;

import com.aem.ugam.core.service.OSGiFactoryConfig;

import java.util.List;

public interface OSGiConfigDemo {
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries() ;
    public String getRunModes();

    public int getServiceId();
    public String getServiceNameModule() ;
    public String getServiceURL() ;

    public List<OSGiFactoryConfig> getAllOSGiConfigs();
}
