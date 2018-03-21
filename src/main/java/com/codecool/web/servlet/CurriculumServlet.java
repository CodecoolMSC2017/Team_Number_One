package com.codecool.web.servlet;

import com.codecool.web.model.SubPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {
    DataStorage ds = DataStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id").equals("newText")) {
            resp.sendRedirect("submitTextPage.html");
        } else if (req.getParameter("id").equals("newAssignment")) {
            resp.sendRedirect("submitTextPage.html");
        } else {
            for (SubPage sp : )
            DataStorage.getInstance().addList(newUser);

            resp.sendRedirect("index.html");
        }

    }
}
