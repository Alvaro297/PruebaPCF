package com.techinc.common.fileupload.Dialogflow.DialogFlowRequest;

import com.techinc.common.fileupload.Dialogflow.response.FulfillmentMessages;
import com.techinc.common.fileupload.Dialogflow.response.FulfillmentText;
import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Parameter;
import java.util.Optional;

public class QueryRequest {
    @Getter
    @Setter
    private FulfillmentMessages fulfillmentMessages;

    @Getter
    @Setter
    private FulfillmentText fulfillmentText;

    @Getter
    @Setter
    private Parameters parameters;
}
