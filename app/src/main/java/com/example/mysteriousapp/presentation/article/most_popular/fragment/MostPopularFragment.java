package com.example.mysteriousapp.presentation.article.most_popular.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleActionInterface;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleAdapter;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ReyclerViewAdapter;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.MostPopularArticlesViewModel;

import java.util.List;


public class MostPopularFragment extends Fragment implements ArticleActionInterface {

    private RecyclerView recyclerView;
    private View view;
    private ArticleAdapter articleAdapter;
    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());



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
        view = inflater.inflate(R.layout.fragment_most_popular, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        ArticlesViewModel articlesViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ArticlesViewModel.class);

        articlesViewModel.getMostPopularArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> articleViewItems) {
                articleAdapter.bindViewModels(articleViewItems);
            }
        });
    }


    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        articleAdapter = new ArticleAdapter(this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }



}