package com.codecool.web.servlet;

import com.codecool.web.model.SubPage;
import com.codecool.web.model.User;
import com.codecool.web.service.AvailablePages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/backToMain")
public class BackToMainServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SubPage> availablePages = new ArrayList<>();
        AvailablePages ap = new AvailablePages();
        try(Connection connection = getConnection(req.getServletContext())) {
            availablePages = ap.selectPages(connection,(User) req.getSession().getAttribute("user"));
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        req.setAttribute("pageList", availablePages);
        req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
    }
}
