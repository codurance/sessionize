package com.codurance.sessionize.sessionizeservice.pairings;

import com.codurance.sessionize.sessionizeservice.preferences.repository.UserLanguagePreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class MatchingClient {
    private final URI matchingUri;

    public MatchingClient(String matchingUrl) {
        matchingUri = URI.create(matchingUrl);
    }

    public List<PairingsResponse> getPairings(List<UserLanguagePreferences> userLanguagePreferences) throws IOException {
        String data = new Gson().toJson(userLanguagePreferences);
        HttpPost post = new HttpPost(matchingUri);
        post.setEntity(new StringEntity(data));
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try (httpClient) {
            CloseableHttpResponse response = httpClient.execute(post);
            String formattedResponse = EntityUtils.toString(response.getEntity());
            return new Gson().fromJson(formattedResponse, new TypeToken<List<PairingsResponse>>(){}.getType());
        }
    }
}
