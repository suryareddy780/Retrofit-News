package edmt.dev.newsretrofit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edmt.dev.newsretrofit.api.ApiClient;
import edmt.dev.newsretrofit.api.ApiInterface;
import edmt.dev.newsretrofit.models.Article;
import edmt.dev.newsretrofit.models.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit extends AppCompatActivity {

    public static final String API_KEY = "f3628ff9b4b7441da8cb6cc9c822c7ae";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = Retrofit.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        recyclerView =findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(Retrofit.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJson();
    }
    public void LoadJson(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();

        Call<News> call;
        call = apiInterface.getNews(country,API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {

                    if(!articles.isEmpty()){
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new Adapter(articles,Retrofit.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(Retrofit.this,"No Result",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                   Toast.makeText(Retrofit.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}