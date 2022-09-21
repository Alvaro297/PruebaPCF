package com.techinc.common.fileupload.storage;

import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	String cambiarLugarPruebaKotlin(String typeFile, String lenguaje);

	String cambiarNombre(List<String> any, String lenguajes);

	void  creacionZip(List<String> any, String gmail, String lenguaje,String salida) throws Exception;

	void eliminarFichero(List<String> any, String lenguajes);
}
