package com.mlindos.projects.mqObserver.peepingTom.component;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.CMQCFC;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Hashtable;

@Component
public class InquireQueueManager {

    /**
     * Handle connecting to the queue manager, issuing PCF command then
     * looping through PCF response messages and disconnecting from
     * the queue manager.
     */
    public void doPCF(String qMgrName, Hashtable mqht)
        {
            MQQueueManager qMgr = null;
            PCFMessageAgent agent = null;
            PCFMessage request = null;
            PCFMessage[] responses = null;

            System.out.println("qManager : " + qMgrName + " - mqht : " + mqht.toString() );

            try {
                    qMgr = new MQQueueManager(qMgrName, mqht);
                    Logger.log("successfully connected to "+ qMgrName);

                    agent = new PCFMessageAgent(qMgr);
                    Logger.log("successfully created agent");

                    // https://www.ibm.com/support/knowledgecenter/en/SSFKSJ_9.1.0/com.ibm.mq.ref.adm.doc/q087800_.htm
                    request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q);

                    /**
                    * You can explicitly set a queue name like "TEST.Q1" or
                    * use a wild card like "TEST.*"
                    */
                        request.addParameter(CMQC.MQCA_Q_NAME, "*");

                    // Add parameter to request only local queues
                        request.addParameter(CMQC.MQIA_Q_TYPE, CMQC.MQQT_LOCAL);

                    // Add parameter to request only queue name and current depth
                        request.addParameter(CMQCFC.MQIACF_Q_ATTRS, new int [] {CMQC.MQCA_Q_NAME, CMQC.MQIA_CURRENT_Q_DEPTH });

                    // Add filter to only return responses with a queue depth greater than 0 (zero)  i.e. non-zero queue depth
                        request.addFilterParameter(CMQC.MQIA_CURRENT_Q_DEPTH, CMQCFC.MQCFOP_GREATER, 0 );

                        responses = agent.send(request);

                        Logger.log("responses.length="+responses.length);

                        for (int i = 0; i < responses.length; i++)
                            {
                                if ( ((responses[i]).getCompCode() == CMQC.MQCC_OK) && ((responses[i]).getParameterValue(CMQC.MQCA_Q_NAME) != null) )
                                    {
                                        String name = responses[i].getStringParameterValue(CMQC.MQCA_Q_NAME);
                                        if (name != null)
                                            name = name.trim();
                                            int depth = responses[i].getIntParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH);
                                            Logger.log("Name="+ name + " : depth=" + depth);
                                    }
                            }
                } catch (MQException e) { Logger.log("CC = " +e.completionCode + " : RC = " + e.reasonCode + " Message: " + e.getMessage()); }
                  catch (IOException e) { Logger.log("IOException:" +e.getLocalizedMessage()); }
                  catch (MQDataException e) { Logger.log("MQDataException:" +e.getLocalizedMessage()); }
                  finally {
                              try {
                                   if (agent != null) {
                                       agent.disconnect();
                                       Logger.log("disconnected from agent");
                                     }
                                 } catch (MQDataException e) { Logger.log("CC = " +e.completionCode + " : RC = " + e.reasonCode + "Message: " + e.getMessage()); }
                              try {
                                   if (qMgr != null) {
                                       qMgr.disconnect();
                                       Logger.log("disconnected from "+ qMgrName);
                                   }
                                  } catch (MQException e) { Logger.log("CC = " +e.completionCode + " : RC = " + e.reasonCode + "Message: " + e.getMessage()); }
                        }
        }
}
