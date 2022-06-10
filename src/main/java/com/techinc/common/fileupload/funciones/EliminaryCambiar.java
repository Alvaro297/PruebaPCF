package com.techinc.common.fileupload.funciones;

import com.techinc.common.fileupload.Dialogflow.response.Parameters;
import com.techinc.common.fileupload.storage.StorageProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EliminaryCambiar {
    private final Path rootLocation;

    public EliminaryCambiar(StorageProperties rootLocation) {
        this.rootLocation = Paths.get(rootLocation.getLocation());;
    }


    public void eliminarKotlin(Parameters parameters){
        Path destinationFile = this.rootLocation.toAbsolutePath();
        Path destiantion=Path.of(destinationFile+"/Animal/src/main/kotlin/");
        File file1=new File(destiantion+parameters.getAny().get(0));
        if (file1.exists()){
            file1.delete();
            System.out.println("Fichero Eliminado");
        }else
            System.out.println("Fichero no existente");
    }

    public void eliminarJava(Parameters parameters){
        Path destinationFile = this.rootLocation.toAbsolutePath();
        Path destiantion=Path.of(destinationFile+"/Java/src/main/java/");
        File file1=new File(destiantion+parameters.getAny().get(0));
        if (file1.exists()){
            file1.delete();
            System.out.println("Fichero Eliminado");
        }else
            System.out.println("Fichero no existente");
    }

    public void cambiarKotlin(String typeFile, String lenguaje){
        Path destinationFile = this.rootLocation.toAbsolutePath();
        Path origin;
        Path destination;
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
    }

    public void cmbiarJava(String typeFile, String lenguaje){
        Path destinationFile = this.rootLocation.toAbsolutePath();
        Path origin;
        Path destination;
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
    }
}
