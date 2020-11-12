package edmt.dev.newsretrofit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    //.client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
