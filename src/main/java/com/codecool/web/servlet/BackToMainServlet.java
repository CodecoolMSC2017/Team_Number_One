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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/backToMain")
public class BackToMainServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SubPage> availablePages = new ArrayList<>();
        AvailablePages ap = new AvailablePages();
        availablePages = ap.selectPages((User)req.getSession().getAttribute("user"));
        req.setAttribute("pageList", availablePages);
        req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
    }
}
