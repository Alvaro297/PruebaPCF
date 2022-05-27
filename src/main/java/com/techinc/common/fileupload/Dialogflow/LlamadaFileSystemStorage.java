package com.techinc.common.fileupload.Dialogflow;

import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import com.techinc.common.fileupload.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;


public class LlamadaFileSystemStorage {

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    public void requestPrueba(String request, Parameters parametros){

        switch (request.toLowerCase()){
            case "Cambiar de lugar":
                fileSystemStorageService.cambiarLugarPruebaKotlin(parametros.getClases(), parametros.getLenguajes());
                break;
            case "Nombre cambiado":
                fileSystemStorageService.cambiarNombre(parametros);
        }
    }
}
