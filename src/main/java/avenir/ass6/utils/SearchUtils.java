package avenir.ass6.utils;

import avenir.ass6.model.IndexSingleton;
import avenir.ass6.model.article.Article;
import avenir.ass6.model.article.TruncatedArticle;
import avenir.ass6.service.ArticleDAO;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;


/**
 * Methods for string manipulations.
 */
@Log4j2
public class SearchUtils {

    public static Boolean checkString(String str) {
        var b = str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"';
        LOGGER.info(b);
        return b;

    }

}
