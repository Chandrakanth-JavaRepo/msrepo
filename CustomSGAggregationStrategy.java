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
public class CustomSGAggregationStrategy implements AggregationStrategy {
   @Override
     public MuleEvent aggregate(AggregationContext context) throws MuleException {
        StringBuilder responseBuilder = new StringBuilder();
        MuleEvent result = null;
        ArrayList marketRates = new ArrayList < > ();
        for (MuleEvent event: context.collectEventsWithoutExceptions()) {
           String response = (String) event.getMessage().getPayload();
           String[] spiltResponse = StringUtils.split(response, ",");
           /*MarketRate marketRate = new MarketRate();
           marketRate.setEvent(event);
           marketRate.setMarketName(spiltResponse[0]);
           marketRate.setMarketRate(new Integer(spiltResponse[1]));
           marketRates.add(marketRate);*/
           //System.out.println(marketRate);
        }
        Collections.sort(marketRates);
       // result = DefaultMuleEvent.copy(marketRates.get(0).getEvent());
        result.getMessage().setPayload(marketRates.get(0).toString());
        if (result != null) {
           return result;
        }
        throw new RuntimeException("no response obtained");
     }
}