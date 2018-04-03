package com.codecool.web.servlet;

import com.codecool.web.model.*;
import com.codecool.web.service.DataStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/check-answers")
public class AnswerCheckerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, String[]> m = (HashMap<String, String[]>) req.getParameterMap();

        int assignId = 0;
        for(String mKey : m.keySet()){
            if (mKey.equals("id")){
                assignId = Integer.parseInt(m.get(mKey).toString());
                m.remove(mKey);
                break;
            }
        }

        Cookie[] cookies = req.getCookies();
        List<User> users = DataStorage.getInstance().getUserList();
        User tempUser = null;
        for (Cookie ck : cookies) {
            for (User usr: users){
                if(usr.getUniqueId().equals(ck.getValue())){
                    tempUser = usr;
                    break;
                }
            }
            if (tempUser != null) break;
        }

        AssignmentPage tempAssign = null;
        for(SubPage tempPage : DataStorage.getInstance().getAllSubPages()){
            if (tempPage.getId() == assignId) {
                tempAssign = (AssignmentPage) tempPage;
                break;
            }
        }

        Set s = m.entrySet();
        Iterator it = s.iterator();
        int result = 0;
        while(it.hasNext()) {

            HashMap.Entry<String, String[]> entry = (HashMap.Entry<String, String[]>) it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            List<String> rightAnswers = new ArrayList<>();
            for (Question tempQuestion: tempAssign.getListOfTasks()) {

                if (tempQuestion.getQuestion().equals(key)){

                    rightAnswers = tempQuestion.getRightStringAnswers();
                    for (String tempAns: value) {

                       if (!rightAnswers.contains(tempAns)){
                           continue;
                       }
                       result++;
                    }
                }
            }
        }

        boolean assignCompleted = false;
        for (int tempAssignId: tempUser.getListOfCompletedAssignments().keySet()) {
            if (tempAssignId == assignId) {
                assignCompleted = true;
                break;
            }
        }

        if (!assignCompleted) tempUser.addCompletedAssignment(assignId, result);

        req.setAttribute("assignTitle",tempAssign.getTitle());
        req.setAttribute("result", result);
        req.setAttribute("userName", tempUser.getName());
        req.getRequestDispatcher("protected/displayAssignmentResult.jsp").forward(req, resp);
    }
}
