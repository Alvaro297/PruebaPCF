package com.techinc.common.fileupload.Dialogflow;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.techinc.common.fileupload.Dialogflow.DialogFlowRequest.DialogFlowRequest;
import com.techinc.common.fileupload.Dialogflow.response.DialogFlowResponse;
import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import com.techinc.common.fileupload.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dialogflow")
public class DialogflowController {

    @Autowired
    StorageService storageService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public void dialogflow(@RequestBody DialogFlowRequest dialogFlowRequest) throws Exception {
       String request=dialogFlowRequest.getQueryResult().getFulfillmentMessages().get(0).getText().getText().get(0);
       Parameters parametros=dialogFlowRequest.getQueryResult().getParameters();
       requestPrueba(request, parametros);
    }

    public void requestPrueba(String request, Parameters parametros) throws Exception {
        switch (request){
            case "Cambiar de lugar":
                storageService.cambiarLugarPruebaKotlin(parametros.getClases(), parametros.getLenguajes());
                break;
            case "Nombre cambiado":
                storageService.cambiarNombre(parametros.getAny(), parametros.getLenguajes());
                break;
            case "Eliminado el fichero":
                storageService.eliminarFichero(parametros.getAny(), parametros.getLenguajes());
                break;
            case "Su programa se ha entregado a su correo anteriormente solicitado":
                storageService.creacionZip(parametros.getAny(), parametros.getGmail(), parametros.getLenguajes());
                break;
        }
    }
}
