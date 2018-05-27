package http;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class ObjectSerializer {

    private final Gson gson = new Gson();

    public <T> T getFromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public String getJson(Object o) {
        return gson.toJson(o);
    }

    public <T> T getObjectFromXml(String xml, Class<T> clazz) throws JAXBException {
        StringReader reader = new StringReader(xml);

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(reader);
    }

}
