package com.example.mysteriousapp.presentation.article.most_popular.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.presentation.article.ArticleActivity;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleActionInterface;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleAdapter;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleGridAdapter;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ReyclerViewAdapter;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.MostPopularArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.SavedForLaterViewModel;

import java.util.List;


public class MostPopularFragment extends Fragment implements ArticleActionInterface {

    private RecyclerView recyclerView;
    private View view;
    private ImageView layoutToggleBtn;
    private ArticleAdapter articleAdapter;
    private ArticlesViewModel articlesViewModel;
    private ArticleGridAdapter articleGridAdapter;
    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    private static boolean isList = true;
    final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
    private SavedForLaterViewModel savedForLaterViewModel;



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
        layoutToggleBtn = (ImageView) getActivity().findViewById(R.id.layoutToggleBtn);

        Log.d("TESTT", layoutToggleBtn.toString());
        recyclerView.setHasFixedSize(true);

        layoutToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView.getLayoutManager().equals(gridLayoutManager)) {
                    recyclerView.setAdapter(articleAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    isList = true;
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewlist);
                } else {
                    recyclerView.setAdapter(articleGridAdapter);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    isList = false;
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewgrid_2);
                }
            }
        });



        //recyclerView.setLayoutManager(linearLayoutManager);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        articlesViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ArticlesViewModel.class);

        savedForLaterViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(SavedForLaterViewModel.class);

        articlesViewModel.getMostPopularArticles().observe(getViewLifecycleOwner(), new Observer<List<ArticleViewItem>>() {
            @Override
            public void onChanged(List<ArticleViewItem> articleViewItems) {
                articleAdapter.bindViewModels(articleViewItems);
                articleGridAdapter.bindViewModels(articleViewItems);
            }
        });
    }


    private void initRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        articleAdapter = new ArticleAdapter(this);
        articleGridAdapter = new ArticleGridAdapter(this);
        if (isList){
            recyclerView.setAdapter(articleAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else {
            recyclerView.setAdapter(articleGridAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        }
    }

    @Override
    public void onArticle(String articleTitle, String articleAbstract, String articleThumbnail) {
        Intent intent = new Intent(getActivity(), ArticleActivity.class);
        intent.putExtra("articleTitle", articleTitle);
        intent.putExtra("articleAbstract", articleAbstract);
        intent.putExtra("articleThumbnail", articleThumbnail);
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