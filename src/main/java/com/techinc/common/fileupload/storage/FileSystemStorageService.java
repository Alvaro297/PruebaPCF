package com.techinc.common.fileupload.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.stream.Stream;



@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	private StorageProperties storageProperties;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String pathName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			if (pathName.isEmpty()) {
				throw new StorageException("Path Name is required.");
			}
			Path destinationFile = this.rootLocation.toAbsolutePath();

				
			String fileName = destinationFile.toString();
			Path _newPath = Paths.get( fileName + "/"+ pathName);
			destinationFile  = Files.createDirectories(_newPath);

			destinationFile = destinationFile.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
		
			if (destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public void updatePrueba1(String file, String pathName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			if (pathName.isEmpty()) {
				throw new StorageException("Path Name is required.");
			}
			Path destinationFile = this.rootLocation.toAbsolutePath();


			String fileName = destinationFile.toString();
			Path _newPath = Paths.get( fileName + "/"+ pathName);
			destinationFile  = Files.createDirectories(_newPath);

			destinationFile = destinationFile.resolve(
							Paths.get(file))
					.normalize().toAbsolutePath();

			if (destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}


	@Override
	public String cambiarLugarPruebaKotlin(String typeFile, String lenguaje) {
		Path destinationFile = this.rootLocation.toAbsolutePath();
		File origin;
		File destination;

		if (lenguaje.toLowerCase()=="kotlin"){
			switch (typeFile.toLowerCase()){
				case "interfaz":
					origin = new File(destinationFile + "/ElementosKotlin/Interfaz/Interfaz.kt" );
					destination = new File(destinationFile + "/Animal/src/main/kotlin");
					if (origin.exists()) {
						try {
							InputStream in = new FileInputStream(origin);
							OutputStream out = new FileOutputStream(destination);
							byte[] buf = new byte[1024];
							int len;
							while ((len = in.read(buf)) > 0) {
								out.write(buf, 0, len);
							}
							in.close();
							out.close();

						} catch (IOException ioe) {
							ioe.printStackTrace();

						}
					} else {
						return "Fichero inexistente";
					}
				case "clase"+"class":
					origin = new File(destinationFile + "/ElementosKotlin/Class/Class.kt" );
					destination = new File(destinationFile + "/Animal/src/main/kotlin");
					if (origin.exists()) {
						try {
							InputStream in = new FileInputStream(origin);
							OutputStream out = new FileOutputStream(destination);
							byte[] buf = new byte[1024];
							int len;
							while ((len = in.read(buf)) > 0) {
								out.write(buf, 0, len);
							}
							in.close();
							out.close();

						} catch (IOException ioe) {
							ioe.printStackTrace();

						}
					} else {
						return "Fichero inexistente";
					}
			}
		}else if (lenguaje.toLowerCase()=="java") {

		}


		return "true";
	}


		/*String fileName = destinationFile.toString();
		File origen = new File(fileName+"/"+pathName);
		File destino = new File(fileName+"/ElementosKotlin/"+file);

		try {
			InputStream in = new FileInputStream(origen);
			OutputStream out = new FileOutputStream(destino);

			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			in.close();
			out.close();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}*/


	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 6)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename, String pathName) {
		try {
			Path file = this.rootLocation.toAbsolutePath();
			
			
			String fileName = file.toString();
			
			Path _newPath = Paths.get( fileName + "/"+ pathName + "/" + filename);
			Resource resource = new UrlResource(_newPath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public Resource loadAsResource(String filename, String pathName, String pathName2, String pathName3, String pathName4) {
		try {
			Path file = this.rootLocation.toAbsolutePath();


			String fileName = file.toString();

			Path _newPath = Paths.get( fileName + "/"+ pathName + "/" +pathName2+"/"+pathName3+"/"+pathName4+"/"+ filename);
			Resource resource = new UrlResource(_newPath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
		init();
	}

	@Override
	public void deleteAllbyName(String pathName, String filename) {
		
		Path file = this.rootLocation.toAbsolutePath();
		//File fileToDelete = FileUtils.getFile(file.toAbsolutePath());
		String fileName = file.toString();
		
		Path _newPath = Paths.get( fileName + "/"+ pathName + "/" + filename);
		
		
		Resource resource;
		try {
			resource = new UrlResource(_newPath.toUri());
			Files.delete(_newPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		//FileUtils.forceDelete(FileUtils.getFile(file.normalize().toString()));
	}

	@Override
	public void exit(){
		storageProperties.setLocation(storageProperties.getOriginalLocation());
	}
	

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}
