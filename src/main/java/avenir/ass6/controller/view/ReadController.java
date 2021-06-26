package avenir.ass6.controller.view;

import avenir.ass6.model.article.Article;
import avenir.ass6.service.ArticleDAO;
import avenir.ass6.service.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ReadController {

    private final ArticleDAO articleDAO;

    @GetMapping("/read")
    public String getRead(
            @RequestParam(name = "id", required = false) int id,
            Model model){
        if (id > 0) {
            Article art = articleDAO.findById(id);
            if (art != null) {
                model.addAttribute("articles", art);
            } else {
                throw new ResourceNotFoundException();
            }
        }
        return "read";
    }
}
