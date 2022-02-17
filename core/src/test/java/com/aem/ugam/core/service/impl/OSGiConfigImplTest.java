package com.aem.ugam.core.service.impl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(AemContextExtension.class)
class OSGiConfigImplTest {

    AemContext aemContext = new AemContext();
    OSGiConfigImpl configTest;

    private String serviceName;

    @BeforeEach
    void setUp() {
        configTest = aemContext.registerService(new OSGiConfigImpl());
        OSGiConfigImpl.ServiceConfig config = mock(OSGiConfigImpl.ServiceConfig.class);
        when(config.serviceName()).thenReturn("Ugam Service");
        configTest.activate(config);
    }

    @Test
    void activate() {
    }

    @Test
    void getServiceName() {
        assertEquals("Ugam Service" , configTest.getServiceName());
    }

    @Test
    void getServiceCount() {
    }

    @Test
    void isLiveData() {
    }

    @Test
    void getCountries() {
    }

    @Test
    void getRunModes() {
    }
}