package com.codecool.web.servlet;

import com.codecool.web.dao.SubPageDao;
import com.codecool.web.model.SubPage;
import com.codecool.web.model.User;
import com.codecool.web.service.AvailablePages;
import com.codecool.web.service.SubPageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/publish")
public class PagePublisherServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SubPageDao spDao = new SubPageDao(connection);
            SubPageService spService = new SubPageService(spDao);
            AvailablePages ap = new AvailablePages();
            int pageId = Integer.parseInt(req.getParameter("id"));

            SubPage sp = spService.getSubPageById(pageId);
            if (sp.isPublished()) {
                sp.setUnPublished();
            } else {
                sp.setPublished();
            }

            User user = (User)req.getSession().getAttribute("user");
            System.out.println(user.getName());

            List<SubPage> availables = ap.selectPages(connection, user);

            req.setAttribute("pageList", availables);
            req.getRequestDispatcher("protected/curriculum.jsp").forward(req, resp);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
