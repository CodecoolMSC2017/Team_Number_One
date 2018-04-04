package com.codecool.web.servlet;

import com.codecool.web.model.SubPage;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/publish")
public class PagePublisherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageId = Integer.parseInt(req.getParameter("id"));
        SubPage sp = DataStorage.getInstance().getSubPageById(pageId);
        if(sp.isPublished()){
            sp.setUnPublished();
        } else {
            sp.setPublished();
        }
        req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
        req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
    }
}
