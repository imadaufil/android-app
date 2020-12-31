package com.example.mysteriousapp.presentation.article.most_popular.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ReyclerViewAdapter;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.MostPopularArticlesViewModel;

import java.util.List;


public class MostPopularFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArticlesViewModel articlesViewModel;
    private ReyclerViewAdapter reyclerViewAdapter;

    private MostPopularArticlesViewModel viewModel;


    public MostPopularFragment() {
        // Required empty public constructor
    }

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);

        /*

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        articlesViewModel.init();
        articlesViewModel.getArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articleList) {
                reyclerViewAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView(view);
         */

        viewModel = new ViewModelProvider(this).get(MostPopularArticlesViewModel.class);
        getMostPopularArticles();
        return view;
    }

    private void getMostPopularArticles() {
        viewModel.getMostPopularArticles().observe(this, new Observer<ArticlesHomeResponse>() {
                    @Override
                    public void onChanged(ArticlesHomeResponse mostPopularArticlesReponse) {
                        Log.d("test",  mostPopularArticlesReponse.getResults().get(0).getTitle());
                        Toast.makeText(MostPopularFragment.this.getContext(), "Total pages" + mostPopularArticlesReponse.getNum_results(), Toast.LENGTH_SHORT).show();
                    }
                }
            );
    }

    /*
    private void initRecyclerView(View view) {
        reyclerViewAdapter = new ReyclerViewAdapter(articlesViewModel.getArticles().getValue(), view.getContext());
        recyclerView.setAdapter(reyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

     */

}