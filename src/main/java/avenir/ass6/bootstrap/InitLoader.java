package avenir.ass6.bootstrap;

import avenir.ass6.model.IndexSingleton;
import avenir.ass6.model.article.Article;
import avenir.ass6.model.user.User;
import avenir.ass6.service.ArticleDAO;
import avenir.ass6.service.UserDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Component
@Log4j2
public class InitLoader implements CommandLineRunner {

    private final UserDAO userDAO;

    private final ArticleDAO articleDAO;

    private final PasswordEncoder passwordEncoder;

    public InitLoader(UserDAO userDAO, ArticleDAO articleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.articleDAO = articleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    private final IndexSingleton indexSingleton = IndexSingleton.INSTANCE;

    public void initIndex() throws IOException, ClassNotFoundException {
        final File file = new File("/home/ac/Téléchargements/ass6/src/main/resources/resources/index/");
        String[] serialPath = file.list();
        assert serialPath != null;

       /* File f = new File("/home/ac/Téléchargements/ass6/src/main/resources/resources/articles/");
        String[] paths = f.list();
        for (String path : paths) {
            final BufferedReader br = new BufferedReader(new FileReader("/home/ac/Téléchargements/ASS-5/src/corpus/" + path));
            String str;
            String text = "";
            while ((str = br.readLine()) != null) {
                text += " " + str;
            }
            Article article = new Article(path, text);
            articleDAO.save(article);
        }*/

        if (serialPath.length == 0) {
            FileOutputStream fileOutputStream = new FileOutputStream("/home/ac/Téléchargements/ass6/src/main/resources/resources/index/index.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(indexSingleton.getHashtable());
            objectOutputStream.close();
            fileOutputStream.close();
            LOGGER.info("Serialized HashTable data is saved in resources/index.ser");
        } else {
            FileInputStream fileInputStream = new FileInputStream("/home/ac/Téléchargements/ass6/src/main/resources/resources/index/index.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            indexSingleton.setHashtable((Hashtable<String, ArrayList<String>>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
            LOGGER.info("Deserialized HashTable data have been stored in hashtable variable.");

        }
    }

    /**
     * Init db.
     *
     * @throws Exception - exception
     */
    @Override
    public void run(String... args) throws Exception {
        initIndex();
        final List<User> users = userDAO.findAll();
        if (users.isEmpty()) {
            String hPass = passwordEncoder.encode("aaa");
            User user = new User("alex", hPass, true, "USER", "2626");
            userDAO.save(user);
        }

    }
}


