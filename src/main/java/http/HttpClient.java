package http;

import java.io.IOException;

public interface HttpClient {

    String makeGetRequest(String url) throws IOException;

    String makePutRequest(String url, String data) throws IOException;

}
