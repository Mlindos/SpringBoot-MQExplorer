package com.mlindos.projects.mqObserver.peepingTom.activities;

import java.util.Hashtable;

public class ParamsPresent {

    /**
     * Make sure the required parameters are present.
     * @return true/false
     */
    public static boolean allParamsPresent(Hashtable params)
    {
        boolean b = params.containsKey("-h") && params.containsKey("-p") &&
                    params.containsKey("-c") && params.containsKey("-m") &&
                    params.containsKey("-u") && params.containsKey("-x");
        if (b)
            {
                try
                {
                    Integer.parseInt((String) params.get("-p"));
                }
                catch (NumberFormatException e)
                {
                    b = false;
                }
            }

        return b;
    }
}
