package com.techinc.common.fileupload.Dialogflow;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.techinc.common.fileupload.Dialogflow.DialogFlowRequest.DialogFlowRequest;
import com.techinc.common.fileupload.Dialogflow.response.DialogFlowResponse;
import com.techinc.common.fileupload.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/dialogflow")
public class DialogflowController {

    @Autowired
    FileUploadController fileUploadController;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity dialogflow(@RequestBody DialogFlowRequest dialogFlowRequest){
       DialogFlowResponse dialogFlowResponse=new DialogFlowResponse();
       String request=dialogFlowRequest.getQueryRequest().getFulfillmentMessages().getText().getText().get(0);
       return ResponseEntity.ok(dialogFlowResponse);
    }


}
