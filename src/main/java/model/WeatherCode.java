package model;

import generated.Report;

public enum WeatherCode {
    NORMAL_WEATHER("NMR"),
    DROUGHT("FUNDEFINEDG"),
    RAIN("HVA"),
    FOG("T E"),
    STORM("SRO");

    private final String weather;

    WeatherCode(String weather) {
        this.weather = weather;
    }

    public static WeatherCode fromReport(Report report) {
        final String code = report.getCode();
        for (WeatherCode weatherCode : WeatherCode.values()) {
            if (weatherCode.weather.equals(code)) {
                return weatherCode;
            }
        }
        throw new IllegalArgumentException("Weather code not present in enum: " + code);
    }

}
