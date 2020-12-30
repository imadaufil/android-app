package com.example.mysteriousapp.presentation.articledisplay.mostpopular.fragment;

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

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter.ArticleActionInterface;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter.ArticleListAdapter;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.viewmodel.ArticleViewModel;

import java.util.List;


public class MostPopularFragment extends Fragment implements ArticleActionInterface {

    private View view;
    private ArticleListAdapter articleListAdapter;
    private ArticleViewModel articleViewModel;


    public MostPopularFragment() {
        // Required empty public constructor
    }

    public static MostPopularFragment newInstance() {
        return new MostPopularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerViewList();
    }

    public void initRecyclerViewList() {
        Log.d("TEST", "initRecyclerView call");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        articleListAdapter = new ArticleListAdapter(this);
        recyclerView.setAdapter(articleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerViewModelsList();
    }

    private void registerViewModelsList() {
        articleViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ArticleViewModel.class);
        articleViewModel.getHomeArticles();
        articleViewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> articleViewItems) {
                articleListAdapter.bindViewModels(articleViewItems);
            }
        });
    }

    @Override
    public void onInfoClicked(String articleTitle, String urlImage) {
        Log.d("TEST", "ON INFO call");
    }

    @Override
    public void onFav(String articleTitle, String urlImage) {
        Log.d("TEST", "ON FAV call");
    }
}