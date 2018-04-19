package com.codecool.web.servlet;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.SubPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/text-page")
public class TextPageServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())){
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
        int textId = Integer.parseInt(req.getParameter("id"));
        TextPage tempText = (TextPage)spService.getAllSubPages().get(textId);
        req.setAttribute("tp",tempText);
        req.getRequestDispatcher("protected/displayTextPage.jsp").forward(req, resp);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
