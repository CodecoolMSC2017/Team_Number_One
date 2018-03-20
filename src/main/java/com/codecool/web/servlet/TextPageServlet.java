package com.codecool.web.servlet;

import com.codecool.web.model.TextPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/text-page")
public class TextPageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int textId = Integer.parseInt(req.getParameter("id"));
        TextPage tempText = DataStorage.getInstance().getAllTextPages().get(textId);
        req.setAttribute("tp",tempText);
        req.getRequestDispatcher("displayTextPage.jsp").forward(req, resp);
    }
}
