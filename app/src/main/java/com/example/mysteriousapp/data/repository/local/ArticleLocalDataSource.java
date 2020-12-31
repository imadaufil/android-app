package com.example.mysteriousapp.data.repository.local;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.db.ArticleDataBase;
import com.example.mysteriousapp.data.entity.ArticleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ArticleLocalDataSource {

    private ArticleDataBase articleDataBase;

    public ArticleLocalDataSource(ArticleDataBase articleDataBase) {
        this.articleDataBase = articleDataBase;
    }

    public Flowable<List<ArticleEntity>> loadSavedForLater() {
        return articleDataBase.articleDao().loadSavedForLater();
    }

    public Completable addArticleToSavedForLater(ArticleEntity articleEntity) {
        return articleDataBase.articleDao().addArticleToSavedForLater(articleEntity);
    }

    public Completable deleteArticleToSavedForLater(String id) {
        return articleDataBase.articleDao().deleteArticleToSavedForLater(id);
    }

    public Single<List<String>> getSavedForLaterList() {
        return articleDataBase.articleDao().getSavedForLaterList();
    }

    /*

    public Collection<Article> setArticlesLocaly() {
        List<Article> data = new ArrayList<>();
        data.add(new Article("0", "25 Days That Changed the World: How Covid-19 Slipped China’s Grasp", "https://static01.nyt.com/images/2020/12/28/world/00china-covid-1/00china-covid-1-thumbStandard.jpg"));
        data.add(new Article("1", "Most Americans Are Expected to Save, Not Spend, Their $600 Check", "https://static01.nyt.com/images/2020/12/30/us/30stimulus-checks/30stimulus-checks-thumbStandard.jpg"));
        data.add(new Article("2", "U.K. Authorizes Covid-19 Vaccine From Oxford and AstraZeneca", "https://static01.nyt.com/images/2020/12/31/world/30virus-astrazeneca1sub/merlin_180213432_f5855d0e-2f6f-432a-8c8c-6abaffda5486-thumbStandard.jpg"));
        data.add(new Article("3", "Let There Be Light, and Art, in the Moynihan Train Hall", "https://static01.nyt.com/images/2020/12/30/arts/30moynihan-art-11/30moynihan-art-11-thumbStandard.jpg"));
        data.add(new Article("4", "New Train Hall Opens at Penn Station, Echoing Building’s Former Glory", "https://static01.nyt.com/images/2020/12/30/nyregion/30moynihanstation1/30moynihanstation1-thumbStandard.jpg"));
        data.add(new Article("5", "Nashville Suspect’s Girlfriend Told Police Last Year That He Was Making Bombs", "https://static01.nyt.com/images/2020/12/29/us/NASHVILLE-BOMBER-photo/NASHVILLE-BOMBER-photo-thumbStandard.jpg"));
        data.add(new Article("6", "Was the College Football Season Worth It?", "https://static01.nyt.com/images/2020/08/01/nyregion/01nyrooftop-02/01nyrooftop-02-superJumbo.jpg"));
        data.add(new Article("7", "Finding Euphoria in Bangkok’s Food Scene", "https://static01.nyt.com/images/2020/06/02/travel/02travel-thailand-promo/02travel-thailand-promo-thumbStandard-v2.jpg"));
        data.add(new Article("8", "New York Love Story: The Submarine Officer and the Beatles Cover Band", "https://static01.nyt.com/images/2020/08/01/nyregion/01nyrooftop-02/01nyrooftop-02-thumbStandard.jpg"));
        data.add(new Article("9", "The Great News Quiz of 2020", "https://static01.nyt.com/images/2020/12/29/briefing/30themorning-2020newsquiz-1609280911149/30themorning-2020newsquiz-1609280911149-thumbStandard.jpg"));
        return data;
    }

     */
}
