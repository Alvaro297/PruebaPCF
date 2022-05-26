package com.techinc.common.fileupload.Dialogflow.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonSerialize
public class DialogFlowResponse {
    @Getter
    @Setter
    private List<FulfillmentMessages> fulfillmentMessages= List.of(new FulfillmentMessages());
}
