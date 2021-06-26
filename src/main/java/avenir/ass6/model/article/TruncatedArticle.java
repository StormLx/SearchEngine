package avenir.ass6.model.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruncatedArticle {

    private Article article;
    private String truncatedArticle;

    public TruncatedArticle(Article article) {
        this.article = article;
    }

    public void truncate(String search) {

        int index = article.getContent().indexOf(search);

        if (index > 100 && (article.getContent().length() - (index + search.length())) > 100) {
            truncatedArticle = ("..." + article.getContent().substring((index - 100), (index + search.length() + 100)) + "...");
        } else if (index > 100 && (article.getContent().length() - (index + search.length())) < 100) {
            truncatedArticle = ("..." + article.getContent().substring((index - 100), (article.getContent().length() - 1)));
        } else if (index < 100 && (article.getContent().length() - (index + search.length())) > 100) {
            truncatedArticle = (article.getContent().substring(0, (index + search.length() + 100)) + "...");
        } else {
            truncatedArticle = (article.getContent());
        }
    }

    public String getArticleName() {
        return this.article.getName();
    }
}
