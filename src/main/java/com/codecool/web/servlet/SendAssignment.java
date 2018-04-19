package com.codecool.web.servlet;

import com.codecool.web.dao.ResultDao;
import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.*;
import com.codecool.web.service.ResultService;
import com.codecool.web.service.SubPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/check-answers")
public class SendAssignment extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int assignId = Integer.parseInt(req.getParameter("id"));
        try (Connection connection = getConnection(req.getServletContext())) {
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
            ResultDao rDao = new ResultDao(connection);
            ResultService rService = new ResultService(rDao);
            AssignmentPage tempAss = (AssignmentPage) spService.getSubPageById(assignId);
            HashMap<Question, String> tempHash = new HashMap<>();
            for (Question q : tempAss.getListOfQuestions()) {
                tempHash.put(q, req.getParameter(q.getAnswer()));
            }
            user.addCompletedAssignment(assignId, tempHash);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Result result = new Result(assignId, user, ts, spService);
            rService.saveResult(result);

            req.setAttribute("pageList", spService.getAllSubPages());
            req.setAttribute("isSucces", true);
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}



