package com.aem.ugam.core.models.impl;

import com.aem.ugam.core.models.XmlExporter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@Exporter(name = "ugamxml", extensions = "xml", selector = "ugam")
@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = XmlExporter.class,
        resourceType = XmlExporterImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@XmlRootElement(name = "ugam-exporter")
public class XmlExporterImpl implements XmlExporter {
    static final String RESOURCE_TYPE="aemugam/components/content/xmlexporter";

    @ValueMapValue
    String xmlTitle;

    @ValueMapValue
    String xmlDescription;

    @ValueMapValue
    Calendar xmlDate;

    @ValueMapValue
    private List<String> books;

    @Override
    @XmlElement(name = "author-title")
    public String getTitle() {
        return xmlTitle;
    }

    @Override
    @XmlElement(name = "author-description")
    public String getDescription() {
        return xmlDescription;
    }

    @Override
    @XmlElementWrapper(name = "books")
    @XmlElement(name="book")
    public List<String> getBooks() {
        if (books != null) {
            return new ArrayList<String>(books);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    @XmlElement(name = "publish-date")
    public Calendar getDate() {
        return xmlDate;
    }
    @XmlElement(name = "author-name")
    public String getAuthorName(){
        return "AEM UGAM";
    }
}
