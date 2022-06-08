package com.techinc.common.fileupload.Dialogflow.DialogFlowRequest;

import com.techinc.common.fileupload.Dialogflow.response.FulfillmentMessages;
import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class QueryResult {
    @Getter
    @Setter
    private List<FulfillmentMessages> fulfillmentMessages;

    @Getter
    @Setter
    private String fulfillmentText;

    @Getter
    @Setter
    private Parameters parameters;
}
