package com.techinc.common.fileupload.storage;

import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import com.techinc.common.fileupload.funciones.Correo;
import com.techinc.common.fileupload.funciones.EliminaryCambiar;
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
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	private StorageProperties storageProperties;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Autowired
	private Correo correo;

	@Autowired
	private EliminaryCambiar eliminar;

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
	public String cambiarNombre(Parameters parametros){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (parametros.getLenguajes().equals("kotlin")){
			Path destiantion=Path.of(destinationFile+"/Animal/src/main/kotlin/");
			File file1=new File(destiantion+parametros.getAny().get(0));
			File file2=new File(destiantion+parametros.getAny().get(1));
			if (file1.exists()&&!file2.exists()){
				boolean sucess=file1.renameTo(file2);
				if (!sucess){
					System.out.println("Error al cambiar de nombre");
				}
			}else{
				System.out.println("Error fichero ya creado");
			}
		}else if (parametros.getLenguajes().equals("java")){
			Path destiantion=Path.of(destinationFile+"/Java/src/main/java/");
			File file1=new File(destiantion+parametros.getAny().get(0));
			File file2=new File(destiantion+parametros.getAny().get(1));
			if (file1.exists()&&!file2.exists()){
				boolean sucess=file1.renameTo(file2);
				if (!sucess){
					System.out.println("Error al cambiar de nombre");
				}
			}else{
				System.out.println("Error fichero ya creado");
			}
		}else{
			return "No hay ningun lenguaje";
		}
		return "Devuelto correctamente";
	}


	@Override
	public String cambiarLugarPruebaKotlin(String typeFile, String lenguaje) {
		Path destinationFile = this.rootLocation.toAbsolutePath();
		Path origin;
		Path destination;
			if (lenguaje.equalsIgnoreCase("kotlin")) {
				if ("interfaz".equalsIgnoreCase(typeFile)||"interface".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosKotlin/Interfaz/Interfaz.kt");
					destination = Path.of(destinationFile + "/Animal/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));

					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				} else if ("clase".equalsIgnoreCase(typeFile)||"class".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosKotlin/Class/Class.kt");
					destination = Path.of(destinationFile + "/Animal/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));
					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				}
			} else if (lenguaje.equalsIgnoreCase("java")) {
				if ("interfaz".equalsIgnoreCase(typeFile)||"interface".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosJava/Interfaz/Interfaz.java");
					destination = Path.of(destinationFile + "/Java/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));

					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				} else if ("clase".equalsIgnoreCase(typeFile)||"class".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosJava/Class/Class.kt");
					destination = Path.of(destinationFile + "/Java/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));
					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				}
			} else {
				return "Lenguaje no comprendido prueba con: Java o Kotlin";
			}


			return "true";
		}
	@Override
	public void eliminarFichero(Parameters parameters){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (parameters.getLenguajes().equalsIgnoreCase("kotlin")){
			eliminar.eliminarKotlin(parameters);
		}else if (parameters.getLenguajes().equalsIgnoreCase("java")){
			eliminar.eliminarJava(parameters);
		}
	}



	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
		init();
	}

		
		//FileUtils.forceDelete(FileUtils.getFile(file.normalize().toString()))

	@Override
	public void exit(){
		storageProperties.setLocation(storageProperties.getOriginalLocation());
	}
	

	@Override
	public void creacionZip(Parameters parameters) throws Exception {
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (parameters.getLenguajes().equalsIgnoreCase("kotlin")){
			String nuevoParent = destinationFile+"/Animal";
			String destino = nuevoParent + ".zip";
			comprimir(nuevoParent, destino);
			correo.correoKotlin();

		}else if (parameters.getLenguajes().equalsIgnoreCase("java")){
			String nuevoParent = destinationFile+"/Java";
			String destino = nuevoParent + ".zip";
			comprimir(nuevoParent, destino);
			correo.correoJava();
		}
	}
	public void comprimir(String archivo, String archivoZIP) throws Exception {
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(archivoZIP));
		agregarCarpeta("", archivo, zip);
		zip.flush();
		zip.close();
	}
	public void agregarCarpeta(String ruta, String carpeta, ZipOutputStream zip) throws Exception {
		File directorio = new File(carpeta);
		for (String nombreArchivo : directorio.list()) {
			if (ruta.equals("")) {
				agregarArchivo(directorio.getName(), carpeta + "/" + nombreArchivo, zip);
			} else {
				agregarArchivo(ruta + "/" + directorio.getName(), carpeta + "/" + nombreArchivo, zip);
			}
		}
	}
	public void agregarArchivo(String ruta, String directorio, ZipOutputStream zip) throws Exception {
		File archivo = new File(directorio);
		if (archivo.isDirectory()) {
			agregarCarpeta(ruta, directorio, zip);
		} else {
			byte[] buffer = new byte[4096];
			int leido;
			FileInputStream entrada = new FileInputStream(archivo);
			zip.putNextEntry(new ZipEntry(ruta + "/" + archivo.getName()));
			while ((leido = entrada.read(buffer)) > 0) {
				zip.write(buffer, 0, leido);
			}
		}
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
