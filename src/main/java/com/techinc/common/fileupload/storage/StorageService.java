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

	void deleteAll();

	void deleteAllbyName(String filename);

}
