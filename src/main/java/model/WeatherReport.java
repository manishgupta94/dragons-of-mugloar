package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReport {

    private String code;

    public String getCode() {
        return code;
    }

    public WeatherReport setCode(String code) {
        this.code = code;
        return this;
    }

}

