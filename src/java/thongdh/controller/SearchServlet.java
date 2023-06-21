package thongdh.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thongdh.dto.RegistrationDTO;
import thongdh.dao.RegistrationDAO;

/**
 *
 * @author thongdhse160015
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_RESULT_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;
        try {
            if (!searchValue.trim().isEmpty()) {
                //1. call Model - DAO
                //1.1 new DAO object
                RegistrationDAO dao = new RegistrationDAO();
                //1.2 call method of DAO
                dao.searchLastname(searchValue);
                //2. process result
                List<RegistrationDTO> result = dao.getAccountList();
                request.setAttribute("SEARCH RESULT", result);
                url = SEARCH_RESULT_PAGE;
            }//end user had tyype a valid keyword

            //SQLException ex
            //ClassNotFoundException
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
        } finally {
            //sendRedirect or getDispatcherForward?
            //sendRedirect: remove request attributes
            //getDispatcherForward: attach 
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
