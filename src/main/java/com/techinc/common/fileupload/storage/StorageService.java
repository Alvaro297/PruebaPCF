package com.techinc.common.fileupload.storage;

import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file, String pathName);

	void deleteAll();

	String cambiarLugarPruebaKotlin(String typeFile, String lenguaje);

	String cambiarNombre(Parameters parametros);

	void exit();

	void eliminarFichero(Parameters parameters);

	void  creacionZip(Parameters parameters) throws Exception;
}
