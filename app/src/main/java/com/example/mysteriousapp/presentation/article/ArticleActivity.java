package com.example.mysteriousapp.presentation.article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysteriousapp.R;

public class ArticleActivity extends AppCompatActivity {

    private TextView articleTitle;
    private TextView articleAbstract;
    private ImageView articleThumbnail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        Intent intent =  getIntent();
        String title = intent.getStringExtra("articleTitle");
        String summary = intent.getStringExtra("articleAbstract");
        String thumbnail = intent.getStringExtra("articleThumbnail");
        articleTitle = findViewById(R.id.articleTitle);
        articleAbstract = findViewById(R.id.articleAbstract);
        articleThumbnail = findViewById(R.id.articleThumbnail);

        articleTitle.setText(title);
        articleAbstract.setText(summary);
        Glide.with(this)
                .load(thumbnail)
                .into(articleThumbnail);

        findViewById(R.id.articleBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*

        intent.putExtra("articleTitle", articleTitle);
        intent.putExtra("articleAbstract", articleAbstract);
        intent.putExtra("articleThumbnail", articleThumbnail);
         */
    }
}