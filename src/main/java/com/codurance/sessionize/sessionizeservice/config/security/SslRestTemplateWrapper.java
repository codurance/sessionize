package com.codurance.sessionize.sessionizeservice.config.security;

import com.azure.keyvault.jca.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import com.azure.keyvault.jca.org.apache.hc.client5.http.impl.classic.HttpClients;
import com.azure.keyvault.jca.org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import com.azure.keyvault.jca.org.apache.hc.core5.ssl.SSLContexts;
import com.azure.security.keyvault.jca.KeyVaultLoadStoreParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;

@Component
public class SslRestTemplateWrapper {
//                System.getProperty("azure.keyvault.uri"),
//                System.getProperty("azure.keyvault.tenant-id"),
//                System.getProperty("azure.keyvault.client-id"),
//                System.getProperty("azure.keyvault.client-secret")
    @Value("${azure.keyvault.uri}");
    String keyVaultUri;

    @Value("${azure.keyvault.tenant-id}");
    String tenantId;

    @Value("${azure.keyvault.client-id}");
    String clientId;

    @Value("${azure.keyvault.client-secret}");
    String clientSecret;

    @Bean
    public RestTemplate restTemplateWithTLS() throws Exception {
        KeyStore trustStore = KeyStore.getInstance("AzureKeyVault");
        KeyVaultLoadStoreParameter parameter = new KeyVaultLoadStoreParameter(
                keyVaultUri,
                "",
                tenantId,
                clientId,
                clientSecret);
        trustStore.load(parameter);
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null)
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
                (hostname, session) -> true);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(requestFactory);
    }
}
