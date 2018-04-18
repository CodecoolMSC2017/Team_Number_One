package com.codecool.web.servlet;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.*;
import com.codecool.web.service.TempPageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/addpage")
public class AddPagesServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("dataSource");

        if (req.getParameterMap().containsKey("maxScore")) {
            TempPageServlet tmp = new TempPageServlet();
            AssignmentPage tmpAssign = (AssignmentPage) session.getAttribute("sessTmpAssign");
            session.removeAttribute("sessTmpAssign");
            tmpAssign = tmp.tempPageRefresh(req, tmpAssign);

            try (Connection connection = dataSource.getConnection()) {
                SubPageDao spd = new SubPageDao(connection);

                spd.addAssignmentPage(tmpAssign.getTitle(), tmpAssign.getMaxScore(), tmpAssign.getListOfQuestions());
                req.setAttribute("pageList", spd.findAllSubPages());
                req.setAttribute("isSuccess", true);
                req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try (Connection connection = dataSource.getConnection()) {
                SubPageDao spd = new SubPageDao(connection);
                spd.addTextPage(req.getParameter("textTitle"), req.getParameter("textContent"));

                req.setAttribute("pageList", spd.findAllSubPages());
                req.setAttribute("isSuccess", true);
                req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}