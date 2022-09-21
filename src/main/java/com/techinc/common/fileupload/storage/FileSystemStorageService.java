package com.techinc.common.fileupload.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
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




	@Override
	public String cambiarNombre(List<String> any, String lenguajes){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (lenguajes.equalsIgnoreCase("kotlin")){
			Path destiantion=Path.of(destinationFile+"/Kotlin/src/main/kotlin/");
			File file1=new File(destiantion+"/"+any.get(0)+".kt");
			File file2=new File(destiantion+"/"+any.get(1)+".kt");
			if (file1.exists()&&!file2.exists()){
				file1.renameTo(file2);
			}else{
				System.out.println("Error fichero ya creado");
			}
		}else if (lenguajes.equalsIgnoreCase("java")){
			Path destiantion=Path.of(destinationFile+"/Java/src/main/java/");
			File file1=new File(destiantion+"/"+any.get(0)+".java");
			File file2=new File(destiantion+"/"+any.get(1)+".java");
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
					origin = Path.of(destinationFile + "/ElementosKotlin/Interfaz/interfaz.kt");
					destination = Path.of(destinationFile + "/Kotlin/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));

					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				} else if ("clase".equalsIgnoreCase(typeFile)||"class".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosKotlin/Class/class.kt");
					destination = Path.of(destinationFile + "/Kotlin/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));
					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				}
			} else if (lenguaje.equalsIgnoreCase("java")) {
				if ("interfaz".equalsIgnoreCase(typeFile)||"interface".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosJava/Interfaz/interfaz.java");
					destination = Path.of(destinationFile + "/Java/src/main/kotlin");
					try {
						Files.copy(origin, destination.resolve(origin.getFileName()));

					} catch (IOException ioe) {
						ioe.printStackTrace();

					}
				} else if ("clase".equalsIgnoreCase(typeFile)||"class".equalsIgnoreCase(typeFile)) {
					origin = Path.of(destinationFile + "/ElementosJava/Class/class.kt");
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
	public void eliminarFichero(List<String> any, String lenguajes){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (lenguajes.equalsIgnoreCase("kotlin")){
			eliminarKotlin(any);
		}else if (lenguajes.equalsIgnoreCase("java")){
			eliminarJava(any);
		}
	}



	

	@Override
	public void creacionZip(List<String> any, String gmail, String lenguaje, String salida) throws Exception {
		Path destinationFile = this.rootLocation.toAbsolutePath();
		if (lenguaje.equalsIgnoreCase("kotlin")){
			if (salida.toLowerCase().contains("spring")){
				correoKotlin(any.get(0),gmail,salida);
			}else{
			String nuevoParent = destinationFile+"/Kotlin";
			String destino = nuevoParent + ".zip";
			comprimir(nuevoParent, destino);
			correoKotlin(any.get(0),gmail,salida);
			}

		}else if (lenguaje.equalsIgnoreCase("java")){
			if (salida.toLowerCase().contains("spring")){
				correoJava(any.get(0),gmail,salida);
			}else {
				String nuevoParent = destinationFile + "/Java";
				String destino = nuevoParent + ".zip";
				comprimir(nuevoParent, destino);
				correoJava(any.get(0), gmail, salida);
			}
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

	public void correoJava(String a, String gmail, String salida) throws MessagingException {
		Path destinationFile = this.rootLocation.toAbsolutePath();
		String correo="alvarofalagan29@gmail.com";
		String contra="Alvaro2908";
		String b=a.toLowerCase().replace(" ","");
		String correoDestino=b.replace("á","a")+gmail;

		Properties p =new Properties();
		p.put("mail.smtp.host","smtp.office365.com");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.ssl.trust", "smtp.office365.com");
		p.setProperty("mail.smtp.port", "587");
		p.setProperty("mail.smtp.user",correo);
		p.setProperty("mail.smtp.auth", "true");
		Session s = Session.getDefaultInstance(p);
		BodyPart text =new MimeBodyPart();
		text.setText("Ejemplo");
		BodyPart adjunto =new MimeBodyPart();
		if (salida.toLowerCase().contains("spring")){
			adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/Spring.zip")));
			adjunto.setFileName("Spring.zip");
		}else{
			adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/Java.zip")));
			adjunto.setFileName("Java.zip");
		}
		MimeMultipart m=new MimeMultipart();
		m.addBodyPart(text);
		m.addBodyPart(adjunto);

		MimeMessage mensaje = new MimeMessage(s);
		mensaje.setFrom(new InternetAddress(correo));
		mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
		mensaje.setSubject("Envio de archivos adjuntos");
		mensaje.setContent(m);

		Transport t = s.getTransport("smtp");
		t.connect(correo,contra);
		t.sendMessage(mensaje, mensaje.getAllRecipients());
		t.close();
	}

	public void correoKotlin(String a, String gmail, String salida) throws MessagingException {
		Path destinationFile = this.rootLocation.toAbsolutePath();

		String correo="alvarofalagan29@gmail.com";
		String contra="Alvaro2908";
		String b=a.toLowerCase().replace(" ","");
		String correoDestino=b.replace("á","a")+gmail;

		Properties p =new Properties();
		p.put("mail.smtp.host","smtp.office365.com");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.ssl.trust", "smtp.office365.com");
		p.setProperty("mail.smtp.port", "587");
		p.setProperty("mail.smtp.user",correo);
		p.setProperty("mail.smtp.auth", "true");
		Session s = Session.getDefaultInstance(p);
		BodyPart text =new MimeBodyPart();
		text.setText("Ejemplo");
		BodyPart adjunto =new MimeBodyPart();
		if (salida.toLowerCase().contains("spring")){
			adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/SpringKotlin.zip")));
			adjunto.setFileName("SpringKotlin.zip");
		}else{
			adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/Kotlin.zip")));
			adjunto.setFileName("Kotlin.zip");
		}
		MimeMultipart m=new MimeMultipart();
		m.addBodyPart(text);
		m.addBodyPart(adjunto);

		MimeMessage mensaje = new MimeMessage(s);
		mensaje.setFrom(new InternetAddress(correo));
		mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
		mensaje.setSubject("Envio de archivos adjuntos");
		mensaje.setContent(m);

		Transport t = s.getTransport("smtp");
		t.connect(correo,contra);
		t.sendMessage(mensaje, mensaje.getAllRecipients());
		t.close();
	}

	public void eliminarKotlin(List<String> any){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		Path destiantion=Path.of(destinationFile+"/Kotlin/src/main/kotlin/");
		File file1=new File(destiantion+"\\"+any.get(0)+".kt");
		if (file1.exists()){
			file1.delete();
			System.out.println("Fichero Eliminado");
		}else
			System.out.println("Fichero no existente");
	}

	public void eliminarJava(List<String> any){
		Path destinationFile = this.rootLocation.toAbsolutePath();
		Path destiantion=Path.of(destinationFile+"/Java/src/main/java/");
		File file1=new File(destiantion+"\\"+any.get(0)+".java");
		if (file1.exists()){
			file1.delete();
			System.out.println("Fichero Eliminado");
		}else
			System.out.println("Fichero no existente");
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
