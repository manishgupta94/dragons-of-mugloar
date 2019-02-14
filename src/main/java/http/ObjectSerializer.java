package http;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import java.io.IOException;

public class ObjectSerializer {

    private final Gson gson = new Gson();
    private final XmlMapper xmlMapper = new XmlMapper();

    public <T> T getFromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public String getJson(Object o) {
        return gson.toJson(o);
    }

    public <T> T getObjectFromXml(String xml, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(xml, clazz);
    }

}
