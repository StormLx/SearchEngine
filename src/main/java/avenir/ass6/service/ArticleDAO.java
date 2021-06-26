package avenir.ass6.service;

import avenir.ass6.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article, Integer> {
    Article findByName(String name);
    Article findById(int id);
}
