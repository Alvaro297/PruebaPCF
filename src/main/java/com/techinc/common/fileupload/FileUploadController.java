package com.techinc.common.fileupload;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;


import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;




import com.techinc.common.fileupload.storage.StorageService;

@RestController

public class FileUploadController{

    private final StorageService storageService;


	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	  @PostMapping("/api/uploadFile/{pathName}")
		public String handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String pathName){

			storageService.store(file, pathName);
			return 	"You successfully uploaded " + file.getOriginalFilename() + "!";
	    }
    @PostMapping("/api/uploadFilePrueba/{typeFile}/{lenguaje}")
    public String updatePrueba1(@PathVariable String typeFile, @PathVariable String lenguaje){

        storageService.cambiarLugarPruebaKotlin(typeFile, lenguaje);
        return 	"You successfully uploaded " + typeFile + "!";
    }
    


	@GetMapping("/api/getFiles")
    public  List<String> listUploadedFiles() {
        
        Stream<Path> filesInFolder = storageService.loadAll();
        
        List<Path> result =   filesInFolder.sorted().collect(Collectors.toList());
        List<String> items = new ArrayList<>();
       
        for(Path file: result) {
        	 
        	 if(file.getParent()!= null) {
        		 items.add("http://localhost:8081/api/files/"+file.getParent()+"/"+ file.getFileName());
        	 }

        }
        

        
        return items;
    }
   

 //  
 //   public String deleteUploadedFiles() {
 //       storageService.deleteAll();
 //       return "Files have been deleted!";
 //   }


	@DeleteMapping("/api/deleteFile/{pathName:.+}/{fileName:.+}")
    public String deleteUploadedFiles(@PathVariable String pathName, @PathVariable String fileName) {
        storageService.deleteAllbyName(pathName, fileName);
        return "This file has been deleted!";
    }

    @GetMapping("/api/files/{pathName:.+}/{fileName:.+}")
    @ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String pathName, @PathVariable String fileName, HttpServletRequest request) {

        Resource resource =  storageService.loadAsResource(fileName, pathName);
        String contentType = null;
   
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
       
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		
    }

    @GetMapping("/api/files/{pathName:.+}/{pathName2:.+}/{pathName3:.+}/{pathName4:.+}/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String pathName, @PathVariable String pathName2, @PathVariable String pathName3, @PathVariable String pathName4, @PathVariable String fileName, HttpServletRequest request) {

        Resource resource =  storageService.loadAsResource(fileName, pathName,pathName2, pathName3,pathName4);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {

            e.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping("/exit")
    public void exit(){
        storageService.exit();
    }
   
}