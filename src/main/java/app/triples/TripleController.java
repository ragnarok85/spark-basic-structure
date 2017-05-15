package app.triples;

import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.clientAcceptsHtml;
import static app.util.RequestUtil.clientAcceptsJson;
import static app.Application.tripleDao;
import static app.Application.documentDao;
import static app.util.RequestUtil.*;
import java.util.HashMap;

import app.doc.DocumentDAO;
import app.login.LoginController;
import app.util.Path;
import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class TripleController {
	
	public static Route fetchAllTriples = (Request request, Response response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            System.out.println("getParamDocument: " + request.attribute("documents"));
            model.put("triples", tripleDao.getAllTriples(getParamDocument(request)));
            model.put("docs", documentDao.getAllDocuments());
            return ViewUtil.render(request, model, Path.Template.TRIPLES_ALL);
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(DocumentDAO.getAllDocuments());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
	
	
}
