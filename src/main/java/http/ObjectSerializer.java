package http;

import com.google.gson.Gson;
import generated.Report;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class ObjectSerializer {

	private final Gson gson = new Gson();

	private final Logger logger = Logger.getLogger("Logger");

	public  <T> T getFromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public String getJson(Object o) {
		return gson.toJson(o);
	}

	public <T> T getObjectFromXml(String xml, Class<T> clazz) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			logger.error("Failed to unmarshall");
		}
		return null;
	}

}
