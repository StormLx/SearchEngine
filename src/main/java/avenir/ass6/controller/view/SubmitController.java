package avenir.ass6.controller.view;

import avenir.ass6.model.IndexSingleton;
import avenir.ass6.model.article.Article;
import avenir.ass6.service.ArticleDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

@Controller
@Log4j2
public class SubmitController {

    private final ArticleDAO articleDAO;

    private final IndexSingleton indexSingleton = IndexSingleton.INSTANCE;

    public SubmitController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping("/submit")
    public String getSubmit(){
        return "submit";
    }

    @PostMapping("/submit")
    public String postSubmit(
            @RequestParam("content") final String content,
            @RequestParam("fileName") final String fileName
    ) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        final String name = date.format(new Date()) + "_" + fileName;
        final Article article = new Article(name, content);

        Hashtable<String, ArrayList<String>> ht = indexSingleton.getHashtable();
        final String[] words = content.toLowerCase().split("[-.,(): \"']+");
        for (String word : words) {
            if (!word.equals("")) {
                if (!ht.containsKey(word)) {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(name);
                    ht.put(word, arr);
                } else {
                    ArrayList<String> ar = ht.get(word);
                    if (!ar.contains(name)) {
                        ar.add(name);
                    }
                }
            }
        }
        indexSingleton.setHashtable(ht);
        articleDAO.save(article);
        LOGGER.info("Article saved");
        return "submit";
    }
}
