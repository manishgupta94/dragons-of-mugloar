package http;

import com.google.gson.Gson;
import generated.Report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class SerializationService {

	private final Gson gson = new Gson();

	public SerializationService() {}

	public  <T> T getFromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public String getStringFromJson(Object o) {
		return gson.toJson(o);
	}

	public <T> T getObjectFromXml(String xml, Class<T> clazz) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			System.err.println("Failed to unmarshall: " + clazz.getName());
		}
		return null;
	}
}
