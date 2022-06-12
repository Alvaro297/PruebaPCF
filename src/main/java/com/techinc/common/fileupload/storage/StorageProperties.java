package com.techinc.common.fileupload.storage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.flogger.Flogger;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	@Getter
	@Setter
	private String location = "upload-dir";

	@Getter
	@Setter
	private String originalLocation ="upload-dir";

}
