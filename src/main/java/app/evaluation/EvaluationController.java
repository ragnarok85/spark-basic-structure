package app.evaluation;

import static app.Application.documentDao;
import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.clientAcceptsHtml;
import static app.util.RequestUtil.clientAcceptsJson;
import static app.util.RequestUtil.getParamBoxes;
import static app.util.RequestUtil.getTotalBoxes;

import java.util.HashMap;

import app.doc.DocumentDAO;
import app.util.Checkboxes;
import app.util.Path;
import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class EvaluationController {

	public static Route fetchAllDocuments = (Request request, Response response) -> {
//		LoginController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            String boxes = getParamBoxes(request);
            String total = getTotalBoxes(request);
            System.out.println("Total Boxes = " + boxes + " accerted = " + total + " div = " + (Double.parseDouble(boxes)/Double.parseDouble(total)));
            System.out.println("accerted = " + boxes + "  requestQueryParams = " + request.queryParams() + " requestParams= " + request.params() + " requestAttributes= " + request.attributes() );
            model.put("docs", documentDao.getAllDocuments());
            return ViewUtil.render(request, model, Path.Template.DOCS_ALL);
        }
        if (clientAcceptsJson(request)) {
        	System.out.println("no entro");
            return dataToJson(DocumentDAO.getAllDocuments());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
}
