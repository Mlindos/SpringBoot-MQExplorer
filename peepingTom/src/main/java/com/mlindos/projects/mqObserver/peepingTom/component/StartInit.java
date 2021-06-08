package com.mlindos.projects.mqObserver.peepingTom.component;

import com.ibm.mq.MQException;
import com.ibm.mq.constants.CMQC;
import com.mlindos.projects.mqObserver.peepingTom.activities.ParamsPresent;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

@Component
public class StartInit {

    /**
     * Extract the command-line parameters and initialize the MQ HashTable.
     * @param args
     * @throws IllegalArgumentException
     */


    private static final SimpleDateFormat LOGGER_TIMESTAMP = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    private Hashtable<String,String> params;
    private Hashtable<String,Object> mqht;
    private String qMgrName;


    public StartInit(){
        super();
        params = new Hashtable<String, String>();
        mqht = new Hashtable<String,Object>();
    }

    public Hashtable<String,Object> init (String[] args) throws IllegalArgumentException
    {
        Logger.log("Args size : " + args.length);
        int port;// = 1414;
        if (args.length > 0 && (args.length % 2) == 0)
        {
            for (int i = 0; i < args.length; i += 2)
            {
                Logger.log(" args[" + i + "] = " + args[i] + "  args[i + 1] = " + args[i + 1]);
                params.put(args[i], args[i + 1]);
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }

        if (ParamsPresent.allParamsPresent(params))
        {
            qMgrName = (String) params.get("-m");

            try
            {
                port = Integer.parseInt((String) params.get("-p"));
            }
            catch (NumberFormatException e)
            {
                port = 1414;
            }

            mqht.put(CMQC.CHANNEL_PROPERTY, params.get("-c"));
            mqht.put(CMQC.HOST_NAME_PROPERTY, params.get("-h"));
            mqht.put(CMQC.PORT_PROPERTY, new Integer(port));
            mqht.put(CMQC.USER_ID_PROPERTY, params.get("-u"));
            mqht.put(CMQC.PASSWORD_PROPERTY, params.get("-x"));

            // I don't want to see MQ exceptions at the console.
            MQException.log = null;
        }
        else
        {
            throw new IllegalArgumentException();
        }

        return mqht;
    }

}
