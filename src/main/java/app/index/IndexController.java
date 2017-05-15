package app.index;

import app.util.*;
import spark.*;
import java.util.*;
import static app.Application.*;

public class IndexController {
    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", userDao.getAllUserNames());
        model.put("book", bookDao.getRandomBook());
        model.put("docs", documentDao.getAllDocuments());
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };
}
