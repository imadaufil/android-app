package com.example.mysteriousapp.presentation.article.saved_for_later.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.presentation.article.ArticleActivity;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleAdapter;
import com.example.mysteriousapp.presentation.article.saved_for_later.adapter.SavedForLaterActionInterface;
import com.example.mysteriousapp.presentation.article.saved_for_later.adapter.SavedForLaterAdapter;
import com.example.mysteriousapp.presentation.article.saved_for_later.adapter.SavedForLaterViewItem;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.Event;
import com.example.mysteriousapp.presentation.viewmodel.SavedForLaterViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedForLaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedForLaterFragment extends Fragment implements SavedForLaterActionInterface {

    private View view;
    private RecyclerView recyclerView;
    private SavedForLaterAdapter savedForLaterAdapter;
    private SavedForLaterViewModel savedForLaterViewModel;
    private ArticlesViewModel articlesViewModel;


    public SavedForLaterFragment() {
        // Required empty public constructor
    }

    public static SavedForLaterFragment newInstance() {
        return new SavedForLaterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saved_for_later, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        savedForLaterViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(SavedForLaterViewModel.class);
        articlesViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ArticlesViewModel.class);

        System.out.println("FVVM is " + savedForLaterViewModel);

        savedForLaterViewModel.getSavedForLater().observe(getViewLifecycleOwner(), new Observer<List<SavedForLaterViewItem>>() {
            @Override
            public void onChanged(List<SavedForLaterViewItem> savedForLaterViewItems) {
                savedForLaterAdapter.bindViewModels(savedForLaterViewItems);
            }
        });

        savedForLaterViewModel.getArticleAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //
            }
        });

        savedForLaterViewModel.getArticleDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        savedForLaterAdapter = new SavedForLaterAdapter(this);
        recyclerView.setAdapter(savedForLaterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onArticle(String articleTitle, String articleAbstract, String articleThumbnail, boolean savedForLater) {
        Intent intent = new Intent(getActivity(), ArticleActivity.class);
        intent.putExtra("articleTitle", articleTitle);
        intent.putExtra("articleAbstract", articleAbstract);
        intent.putExtra("articleThumbnail", articleThumbnail);
        intent.putExtra("savedForLater", savedForLater);
        startActivity(intent);
    }

    @Override
    public void onRemoveSavedForLater(String id) {
        savedForLaterViewModel.deleteArticleFromSavedForLater(id);
        articlesViewModel.removeArticleFromSavedForLater(id);
    }
}