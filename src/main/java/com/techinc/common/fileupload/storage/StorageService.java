package com.techinc.common.fileupload.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file, String pathName);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename, String pathName);

	Resource loadAsResource(String filename, String pathName, String pathName2,String pathName3, String pathName4);

	void deleteAll();

	void deleteAllbyName(String pathName, String filename);

	void updatePrueba1(String file, String pathName);

	String cambiarLugarPruebaKotlin(String typeFile, String lenguaje);

	void exit();
}
