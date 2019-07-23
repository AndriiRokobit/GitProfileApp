package com.github.gitusersapp.di.app;

import com.github.gitusersapp.BuildConfig;
import com.github.gitusersapp.data.api.BaseApi;
import com.github.gitusersapp.di.qualifiers.LongOkHttp;
import com.github.gitusersapp.di.qualifiers.LongRetrofit;
import com.github.gitusersapp.di.qualifiers.LongestOkHttp;
import com.github.gitusersapp.di.qualifiers.LongestRetrofit;
import com.github.gitusersapp.di.qualifiers.SimpleOkHttp;
import com.github.gitusersapp.di.qualifiers.SimpleRetrofit;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.github.gitusersapp.data.constants.Endpoints.WEB_API_URL;
import static com.github.gitusersapp.data.constants.Timings.LONGEST_CONNECT;
import static com.github.gitusersapp.data.constants.Timings.LONGEST_READ;
import static com.github.gitusersapp.data.constants.Timings.LONG_CONNECT;
import static com.github.gitusersapp.data.constants.Timings.LONG_READ;
import static com.github.gitusersapp.data.constants.Timings.SIMPLE_CONNECT;
import static com.github.gitusersapp.data.constants.Timings.SIMPLE_READ;
import static com.github.gitusersapp.data.constants.Timings.SIMPLE_WRITE;

@Module
public abstract class NetworkModule {

    @Singleton
    @Provides
    public static BaseApi provideApi(@SimpleRetrofit Retrofit retrofit) {
        return retrofit.create(BaseApi.class);
    }

    @Provides
    @Singleton
    @SimpleRetrofit
    public static Retrofit provideSimpleRetrofit(Converter.Factory converterFactory, @SimpleOkHttp OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(WEB_API_URL).build();
    }

    @Provides
    @Singleton
    @SimpleOkHttp
    public static OkHttpClient provideSimpleOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(SIMPLE_READ, TimeUnit.SECONDS)
                .writeTimeout(SIMPLE_WRITE, TimeUnit.SECONDS)
                .connectTimeout(SIMPLE_CONNECT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @LongRetrofit
    public static Retrofit provideLongRetrofit(Converter.Factory converterFactory, @LongOkHttp OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(WEB_API_URL).build();
    }

    @Provides
    @Singleton
    @LongOkHttp
    public static OkHttpClient provideLongOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(LONG_READ, TimeUnit.SECONDS)
                .connectTimeout(LONG_CONNECT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @LongestRetrofit
    public static Retrofit provideLongestRetrofit(Converter.Factory converterFactory, @LongestOkHttp OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(WEB_API_URL).build();
    }

    @Provides
    @Singleton
    @LongestOkHttp
    public static OkHttpClient provideLongestOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(LONGEST_READ, TimeUnit.SECONDS)
                .connectTimeout(LONGEST_CONNECT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    public static Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }
}
