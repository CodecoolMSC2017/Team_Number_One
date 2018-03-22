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
public class AddPagesServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubPage sp = null;

        if(req.getParameter("textTitle") != null && req.getParameter("textContent") != null){  //not effective, refractor later
            String title = req.getParameter("textTitle");       //redundant
            String content = req.getParameter("textContent");
            sp = new TextPage(title, content);
        }
        else if (req.getParameter("assTitle") != null){
            String title = req.getParameter("assTitle");
            sp = new AssignmentPage(title, new ArrayList<Question>());  //INCOMPLETE
        }

        if (sp != null) {
            DataStorage.getInstance().addSubPage(sp);
        }

        req.setAttribute("isSuccess", true);
        req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
    }


    //for the back bottom
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("curriculum.jsp").forward(request, response);
    }
}
