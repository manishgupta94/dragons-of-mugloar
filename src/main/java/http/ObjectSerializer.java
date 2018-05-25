package http;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class ObjectSerializer {

	private final Logger logger = Logger.getLogger(ObjectSerializer.class);

	private final Gson gson = new Gson();

	public <T> T getFromJson(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

	public String getJson(Object o) {
		return gson.toJson(o);
	}

	public <T> T getObjectFromXml(String xml, Class<T> clazz) {
		try {
			StringReader reader = new StringReader(xml);

			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			logger.error("Failed to unmarshall", e);
		}
		return null;
	}

}
