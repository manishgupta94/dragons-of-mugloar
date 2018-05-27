package http;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class HttpClientImpl implements HttpClient {

    @Override
    public String makeGetRequest(String url) throws IOException {
        return Request.Get(url)
            .execute()
            .returnContent()
            .asString();
    }

    @Override
    public String makePutRequest(String url, String data) throws IOException {
        return Request.Put(url)
            .bodyString(data, ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();
    }

}
