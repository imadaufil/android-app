package com.example.mysteriousapp.presentation.article.articles.fragment.articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.presentation.article.ArticleActivity;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleActionInterface;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleAdapter;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesVM.TechnologyArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.SavedForLaterViewModel;

import java.util.List;

public class TechnologyFragment extends Fragment implements ArticleActionInterface {

    private RecyclerView recyclerView;
    private View view;
    private ArticleAdapter articleAdapter;
    private SavedForLaterViewModel savedForLaterViewModel;



    public TechnologyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.layoutToggleBtn).setVisibility(View.GONE);

    }

    public static TechnologyFragment newInstance() {
        return new TechnologyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_most_popular, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        return view;
    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        savedForLaterViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(SavedForLaterViewModel.class);
        initRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        ArticlesViewModel articlesViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(TechnologyArticlesViewModel.class);

        articlesViewModel.getHomeArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> articleViewItems) {
                articleAdapter.bindViewModels(articleViewItems);
            }
        });
    }




    private void initRecyclerView() {
        articleAdapter = new ArticleAdapter(this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onArticle(String articleTitle, String articleAbstract, String articleThumbnail, String articleCaption, String articleCopyright, String articleByline, String articleURL) {

        Intent intent = new Intent(getActivity(), ArticleActivity.class);
        intent.putExtra("articleTitle", articleTitle);
        intent.putExtra("articleAbstract", articleAbstract);
        intent.putExtra("articleThumbnail", articleThumbnail);
        intent.putExtra("articleCaption", articleCaption);
        intent.putExtra("articleCopyright", articleCopyright);
        intent.putExtra("articleByline", articleByline);
        intent.putExtra("savedForLater", true);
        intent.putExtra("articleURL", articleURL);
        startActivity(intent);
    }

    @Override
    public void onSavedForLaterToggle(String id, String title, String summary, String url,
                                      String byline, String published_date, String thumbnailUrl, String caption,
                                      String copyright, String format, boolean savedForLater) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(id);
        articleEntity.setTitle(title);
        articleEntity.setSummary(summary);
        articleEntity.setUrl(url);
        articleEntity.setByline(byline);
        articleEntity.setPublished_date(published_date);
        articleEntity.setThumbnailUrl(thumbnailUrl);
        articleEntity.setCaption(caption);
        articleEntity.setCopyright(copyright);
        articleEntity.setFormat(format);

        if (savedForLater)
            savedForLaterViewModel.addArticleToSavedForLater(articleEntity);
        else
            savedForLaterViewModel.deleteArticleFromSavedForLater(id);
    }
}