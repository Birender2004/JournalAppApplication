package com.build.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class weatherResponse {

    private Current current;
    private Boolean success;
    private ApiError error;

    @Data
    public static class Current {

        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;
    }

    @Data
    public static class ApiError {
        private int code;
        private String type;
        private String info;
    }

}