package com.example.mysteriousapp.presentation.article;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysteriousapp.R;

public class ArticleActivity extends AppCompatActivity {

    private TextView articleTitle;
    private TextView articleAbstract;
    private TextView articleCaption;
    private TextView articleCopyright;
    private TextView articleByline;
    private ImageView articleThumbnail;
    private CheckBox savedForLater;
    private Button readBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent =  getIntent();
        String title = intent.getStringExtra("articleTitle");
        String summary = intent.getStringExtra("articleAbstract");
        String thumbnail = intent.getStringExtra("articleThumbnail");
        String caption = intent.getStringExtra("articleCaption");
        String copyright = intent.getStringExtra("articleCopyright");
        String byline = intent.getStringExtra("articleByline");
        final String url = intent.getStringExtra("articleURL");
        boolean isSavedForLater = intent.getBooleanExtra("savedForLater", false);
        articleTitle = findViewById(R.id.articleTitle);
        articleAbstract = findViewById(R.id.articleAbstract);
        articleThumbnail = findViewById(R.id.articleThumbnail);
        articleCaption = findViewById(R.id.articleCaption);
        articleCopyright = findViewById(R.id.articleImgCopyright);
        articleByline = findViewById(R.id.articleByline);
        savedForLater = findViewById(R.id.isSavedForLater);
        readBtn = findViewById(R.id.button);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browser);
            }
        });


        savedForLater.setChecked(isSavedForLater);
        articleTitle.setText(title);
        articleAbstract.setText(summary);
        articleCopyright.setText(copyright);
        articleCaption.setText(caption);
        articleByline.setText(byline);
        Glide.with(this)
                .load(thumbnail)
                .into(articleThumbnail);

        findViewById(R.id.articleBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}