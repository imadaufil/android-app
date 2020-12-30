package com.example.mysteriousapp.presentation.article.saved_for_later.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysteriousapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedForLaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedForLaterFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_saved_for_later, container, false);
    }

}