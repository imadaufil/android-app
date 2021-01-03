package com.example.mysteriousapp.presentation.article.articles.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.presentation.article.ArticleActivity;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleActionInterface;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleAdapter;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleGridAdapter;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesVM.HomeArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.SavedForLaterViewModel;

import java.util.List;


public class MostPopularFragment extends Fragment implements ArticleActionInterface {

    private RecyclerView recyclerView;
    private View view;
    private ImageView layoutToggleBtn;
    private ArticleAdapter articleAdapter;
    private ArticleGridAdapter articleGridAdapter;
    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private static boolean isList = true;
    private HomeArticlesViewModel articlesViewModel;
    final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
    private SavedForLaterViewModel savedForLaterViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;



    public MostPopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.layoutToggleBtn).setVisibility(View.VISIBLE);
        Log.d("TESTOPP", isList? "true" : "false");

        if (isList)
            layoutToggleBtn.setImageResource(R.drawable.ic_viewlist);
        else
            layoutToggleBtn.setImageResource(R.drawable.ic_viewgrid_2);


        layoutToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isList) {
                    recyclerView.setAdapter(articleAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isList = true;
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewlist);
                } else {
                    recyclerView.setAdapter(articleGridAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
                    isList = false;
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewgrid_2);
                }
            }
        });


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
        layoutToggleBtn = (ImageView) getActivity().findViewById(R.id.layoutToggleBtn);

        Log.d("TESTT", layoutToggleBtn.toString());
        recyclerView.setHasFixedSize(true);



        //recyclerView.setLayoutManager(linearLayoutManager);




        return view;
    }



    @Override
    public void onPause() {
        super.onPause();
        isList = true;
        layoutToggleBtn.setOnClickListener(null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        savedForLaterViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(SavedForLaterViewModel.class);
        initRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        articlesViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(HomeArticlesViewModel.class);

        articlesViewModel.getHomeArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> articleViewItems) {
                Log.i("TESTZ", "je viens de passer par là mon pote");
                articleAdapter.bindViewModels(articleViewItems);
                articleGridAdapter.bindViewModels(articleViewItems);
            }
        });
    }




    private void initRecyclerView() {
        //recyclerView = view.findViewById(R.id.recycler_view);
        articleAdapter = new ArticleAdapter(this);
        articleGridAdapter = new ArticleGridAdapter(this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onArticle(String articleTitle, String articleAbstract, String articleThumbnail, String articleCaption, String articleCopyright, String articleByline, String articleURL) {
//        Intent intent = new Intent(getActivity(), ArticleActivity.class);
//        intent.putExtra("articleTitle", articleTitle);
//        intent.putExtra("articleAbstract", articleAbstract);
//        intent.putExtra("articleThumbnail", articleThumbnail);


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