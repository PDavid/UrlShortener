package hu.example.urlshortener.web;

import hu.example.urlshortener.UrlShortener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paksyd
 */
@WebServlet(name = "UrlShortenerServlet", urlPatterns = {"/", "/shorten"})
public class UrlShortenerServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(UrlShortenerServlet.class.getName());

    private static String URL_BASE;

    private static final String PATH_CREATE = "/shorten";
    private static final String PATH_RESULT = "/result";

    private static final String PARAM_URL = "url";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        initializeUrlBase(request);

        response.setContentType("text/html;charset=UTF-8");

        String userPath = request.getServletPath();

        if (userPath.isEmpty()) {
            LOGGER.log(Level.WARNING,
                    "Could not determine what to do based on the request. userPath: {0}", userPath);
            return;
        }

        switch (userPath) {
            case PATH_CREATE:
                shortenUrl(request, response);
                break;
            default:
                serveUrl(request, response);
                break;
        }
    }

    private void shortenUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String url = request.getParameter(PARAM_URL);
        if (url == null || url.isEmpty()) {
            System.out.println("Missing url parameter."); // TODO: Change this to error on the UI!
            sendRedirect(request, response, PATH_CREATE);
            return;
        }

        try {
            String shortCode = UrlShortener.createShortCodeForUrl(url);
            String shortUrl = URL_BASE + shortCode;

            request.setAttribute("result", shortUrl);

            sendRedirect(request, response, PATH_RESULT);

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Error while tried to short the url.", ex);
        }
    }

    private void serveUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String shortCode = request.getServletPath();
        shortCode = shortCode.replaceAll("/", "");

        if (shortCode == null || shortCode.isEmpty()) {
            sendRedirect(request, response, PATH_CREATE);
            return;
        }

        LOGGER.log(Level.INFO, 
                "Getting the long url based on the shortCode: ''{0}''", shortCode);

        String url = UrlShortener.getUrlForShortCode(shortCode);
        if (url != null && !url.isEmpty()) {
            response.sendRedirect(url);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response,
            String userPath) throws IOException {

        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException | IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "Could not redirect to url: {0}", url);
        }
    }

    private void initializeUrlBase(HttpServletRequest request) {
        if (URL_BASE == null) {
            String requestURL = request.getRequestURL().toString();
            String servletPath = request.getServletPath();

            requestURL = requestURL.replaceAll(servletPath, "");

            URL_BASE = requestURL + "/";

            LOGGER.log(Level.INFO, 
                "URL_BASE initialized: ''{0}''", URL_BASE);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
