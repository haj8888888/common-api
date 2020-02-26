package com.common.api.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        /**
         * 代理使用
         * Proxy.Type proxyType = Proxy.Type.valueOf(SystemProperties.get("http.proxy.type"));
         *         if (proxyType != Proxy.Type.DIRECT) {
         *             String proxyHost = SystemProperties.get("http.proxy.host");
         *             int proxyPort = SystemProperties.getInt("http.proxy.port");
         *             this.clientBuilder.proxy(new Proxy(proxyType, new InetSocketAddress(proxyHost, proxyPort)));
         *         }
         */
        return clientBuilder.connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
    }
}
