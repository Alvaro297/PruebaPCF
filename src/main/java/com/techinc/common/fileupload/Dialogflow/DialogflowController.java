package com.techinc.common.fileupload.Dialogflow;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.techinc.common.fileupload.Dialogflow.DialogFlowRequest.DialogFlowRequest;
import com.techinc.common.fileupload.Dialogflow.response.DialogFlowResponse;
import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import com.techinc.common.fileupload.FileUploadController;
import com.techinc.common.fileupload.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/dialogflow")
public class DialogflowController {

   private LlamadaFileSystemStorage llamadaFileSystemStorage;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity dialogflow(@RequestBody DialogFlowRequest dialogFlowRequest) throws ClassNotFoundException {
       DialogFlowResponse dialogFlowResponse=new DialogFlowResponse();
       String request=dialogFlowRequest.getQueryRequest().getFulfillmentMessages().getText().getText().get(0);
       Parameters parametros=dialogFlowRequest.getQueryRequest().getParameters();
       llamadaFileSystemStorage.requestPrueba(request, parametros);
       return ResponseEntity.ok(dialogFlowResponse);
    }


}
