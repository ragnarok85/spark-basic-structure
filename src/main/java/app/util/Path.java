package app.util;

import app.triples.TripleController;

public class Path {

    public static class Web {
        public static final String INDEX = "/index/";
        public static final String LOGIN = "/login/";
        public static final String LOGOUT = "/logout/";
        public static final String BOOKS = "/books/";
        public static final String ONE_BOOK = "/books/:isbn/";
        public static final String DOCUMENTS = "/docs/";
//        public static final String TRIPLES = "/triples/:docName/";
        public static final String TRIPLES = "/triples/";
        public static final String EVALUATION = "/evaluation/";
		
        public static String getINDEX() {
			return INDEX;
		}

		public static String getLOGIN() {
			return LOGIN;
		}

		public static String getLOGOUT() {
			return LOGOUT;
		}

		public static String getBOOKS() {
			return BOOKS;
		}

		public static String getONE_BOOK() {
			return ONE_BOOK;
		}

		public static String getDOCUMENTS() {
			return DOCUMENTS;
		}

		public static String getTRIPLES() {
			return TRIPLES;
		}

		public static String getEVALUATION() {
			return EVALUATION;
		}
        
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String LOGIN = "/velocity/login/login.vm";
        public final static String BOOKS_ALL = "/velocity/book/all.vm";
        public final static String DOCS_ALL = "/velocity/triple/docs.vm";
        public static final String BOOKS_ONE = "/velocity/book/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
        public static final String TRIPLES_ALL = "/velocity/triple/triples.vm";
        public static final String EVALUATION_ALL = "/velocity/evaluation/evaluation.vm";
    }

}
