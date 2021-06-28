package avenir.ass6.controller.view;

import avenir.ass6.model.IndexSingleton;
import avenir.ass6.model.article.Article;
import avenir.ass6.model.article.TruncatedArticle;
import avenir.ass6.service.ArticleDAO;
import avenir.ass6.utils.SearchUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@Log4j2
public class SearchController {

    private final ArticleDAO articleDAO;

    private final IndexSingleton indexSingleton = IndexSingleton.INSTANCE;

    public SearchController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping({"/index", "/"})
    public String getIndex(
            ArrayList<TruncatedArticle> articles,
            Model model
    ) {

        model.addAttribute("trunc", articles);
        return "index";
    }

    @RequestMapping("/search")
    public String postIndex(
            @RequestParam("search") String search,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        Hashtable<String, ArrayList<String>> ht = indexSingleton.getHashtable();
        ArrayList<String> arrz = new ArrayList<>();
        if (!SearchUtils.checkString(search)) {
            String[] searchArr = search.split(" ");
            for (String word : searchArr) {
                if (ht.containsKey(word)){
                    arrz.addAll(ht.get(word)); // TODO list of article names and compare them
                }
            }

            if (ht.containsKey(search)) {
                LOGGER.info(ht.get(search));
                LOGGER.info(search);
                ArrayList<String> arr = ht.get(search);
                ArrayList<TruncatedArticle> articles = new ArrayList<>();
                for (String name : arr) {
                    Article article = articleDAO.findByName(name);
                    TruncatedArticle truncatedArticle = new TruncatedArticle(article);
                    truncatedArticle.truncate(search);
                    articles.add(truncatedArticle);
                }
                articles.sort(Comparator.comparing(TruncatedArticle::getArticleName));
                Collections.reverse(articles);
                redirectAttributes.addFlashAttribute(articles);
            } else {
                LOGGER.info("This word does not exist in our database.");
            }
        }

        return "redirect:/index";
    }
}
