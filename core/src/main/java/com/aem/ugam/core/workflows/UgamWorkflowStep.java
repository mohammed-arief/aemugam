package com.aem.ugam.core.workflows;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;

@Component(
        service = WorkflowProcess.class,
        property = {
                "process.label" + " = Ugam Workflow Step",
                Constants.SERVICE_VENDOR + "=AEM Ugam",
                Constants.SERVICE_DESCRIPTION + " = Custom ugam workflow step."
        }
)
public class UgamWorkflowStep implements WorkflowProcess {
    private static final Logger LOG = LoggerFactory.getLogger(UgamWorkflowStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) {
        LOG.info("\n ====================================Custom Workflow Step========================================");
        try {
            WorkflowData workflowData = workItem.getWorkflowData();
            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                Session session = workflowSession.adaptTo(Session.class);
                String path = workflowData.getPayload().toString() + "/jcr:content";
                Node node = (Node) session.getItem(path);
                String brand = processArguments.get("BRAND","");
                boolean multinational =processArguments.get("MULTINATIONAL",false);
                LOG.info("\n BRAND : {} , MULTINATIONAL : {} ",brand,multinational);
                String[] countries = processArguments.get("COUNTRIES",new String[]{});
                for (String country : countries) {
                    LOG.info("\n Countries {} ",country);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR {} ",e.getMessage());
        }
    }
}
