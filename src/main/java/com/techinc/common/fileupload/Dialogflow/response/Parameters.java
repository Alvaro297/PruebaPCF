package com.techinc.common.fileupload.Dialogflow.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;


public class Parameters {
    @Getter
    @Setter
    private Optional<List<String>> any;

    @Getter
    @Setter
    private Optional<String> clases;

    @Getter
    @Setter
    private String lenguajes;
}
