package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Question;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addpage")
public class AddPagesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubPage sp = null;

        String title = req.getParameter("textTitle");       //redundant
        String content = req.getParameter("textContent");
        sp = new TextPage(title, content);
        DataStorage.getInstance().addSubPage(sp);
        req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
        req.setAttribute("isSuccess", true);
        resp.sendRedirect("/protected/curriculum.jsp");
    }


    //for the back bottom
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
        req.setAttribute("isSuccess", false);
        resp.sendRedirect("curriculum.jsp");
    }
}
