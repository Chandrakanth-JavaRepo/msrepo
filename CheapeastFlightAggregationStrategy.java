package com.sap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.lang.StringUtils;
import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;
public class CheapeastFlightAggregationStrategy implements AggregationStrategy {

    @Override
    public MuleEvent aggregate(AggregationContext context) throws MuleException {
        MuleEvent result = null;
        long value = Long.MAX_VALUE;
        ArrayList al = new ArrayList();
        for (MuleEvent event : context.collectEventsWithoutExceptions()) {
             al = (ArrayList) event.getMessage().getPayload();
             
            /*if (flight.getCost() < value) {
                result = DefaultMuleEvent.copy(event);
                value = flight.getCost();
            }*/
        }

        if (result != null)  {
            return result;
        }

        throw new  RuntimeException("no flights obtained");
    }
}
