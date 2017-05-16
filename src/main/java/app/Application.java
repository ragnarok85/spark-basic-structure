package app;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

import app.book.BookController;
import app.book.BookDao;
import app.doc.DocumentController;
import app.doc.DocumentDAO;
import app.index.IndexController;
import app.login.LoginController;
import app.triples.TripleController;
import app.triples.TripleDAO;
import app.user.UserDao;
import app.util.Filters;
import app.util.Path;
import app.util.ViewUtil;

public class Application {

    // Declare dependencies
    public static BookDao bookDao;
    public static UserDao userDao;
    public static DocumentDAO documentDao;
    public static TripleDAO tripleDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        bookDao = new BookDao();
        userDao = new UserDao();
        documentDao = new DocumentDAO();
        tripleDao = new TripleDAO();
        
        // Configure Spark
        port(4568);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX,          IndexController.serveIndexPage);
        get(Path.Web.BOOKS,          BookController.fetchAllBooks);
        get(Path.Web.ONE_BOOK,       BookController.fetchOneBook);
        get(Path.Web.LOGIN,          LoginController.serveLoginPage);
        get(Path.Web.DOCUMENTS,	 	 DocumentController.fetchAllDocuments);
        post(Path.Web.TRIPLES,		 TripleController.fetchAllTriplesPost);
        post(Path.Web.LOGIN,         LoginController.handleLoginPost);
        post(Path.Web.LOGOUT,        LoginController.handleLogoutPost);
        
        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);

    }

}
