package com.codecool.web.servlet;

import com.codecool.web.model.*;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addpage")
public class AddPagesServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubPage sp = null;
        if(req.getParameterMap().containsKey("maxScore")){
            AssignmentPage ap;
            if((AssignmentPage)req.getSession().getAttribute("tmpAssign")!=null){
                ap=(AssignmentPage)req.getSession().getAttribute("tmpAssign");
                String question = req.getParameter("question");
                String answer = req.getParameter("answer");
                Question tmpQ = new Question(question,new Answer(answer));
                ap.addTask(tmpQ);
            }else{
                List<Question> q = new ArrayList<>();
                q.add(new Question(req.getParameter("question"),new Answer(req.getParameter("answer"))));
                ap=new AssignmentPage(req.getParameter("assignTitle"),q,Integer.parseInt(req.getParameter("maxScore")));

            }


            DataStorage.getInstance().addSubPage(ap);
            req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
            req.setAttribute("isSuccess", true);
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);

        }else{
            String title = req.getParameter("textTitle");       //redundant
            String content = req.getParameter("textContent");
            sp = new TextPage(title, content);
            DataStorage.getInstance().addSubPage(sp);
            req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
            req.setAttribute("isSuccess", true);
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
        }

    }


    //for the back bottom
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageList", DataStorage.getInstance().getAllSubPages());
        req.setAttribute("isSuccess", false);
        req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
    }
}
