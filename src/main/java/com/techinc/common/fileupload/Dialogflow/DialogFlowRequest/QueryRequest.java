package com.techinc.common.fileupload.Dialogflow.DialogFlowRequest;

import com.techinc.common.fileupload.Dialogflow.response.FulfillmentMessages;
import com.techinc.common.fileupload.Dialogflow.response.FulfillmentText;
import lombok.Getter;
import lombok.Setter;

public class QueryRequest {
    @Getter
    @Setter
    private FulfillmentMessages fulfillmentMessages;

    @Getter
    @Setter
    private FulfillmentText fulfillmentText;
}
