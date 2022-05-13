package com.techinc.common.fileupload.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";

	private String originalLocation ="upload-dir";

	public String getOriginalLocation() {
		return originalLocation;
	}

	public  void  setOriginalLocation(String originalLocation){this.originalLocation=originalLocation;}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
