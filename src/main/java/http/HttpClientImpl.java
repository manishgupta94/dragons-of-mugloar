package http;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class HttpClientImpl implements HttpClient {

	public HttpClientImpl() {}

	@Override
	public String makeGetRequest(String url) throws IOException {
		return Request.Get(url)
				.execute()
				.returnContent()
				.asString();
	}

	@Override
	public String makePostRequest(String url, String data) throws IOException {
		return Request.Put(url)
				.bodyString(data, ContentType.APPLICATION_JSON)
				.execute()
				.returnContent()
				.asString();
	}
}
