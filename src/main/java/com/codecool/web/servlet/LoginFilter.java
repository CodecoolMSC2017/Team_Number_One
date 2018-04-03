package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.DataStorage;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<User> users = DataStorage.getInstance().getUserList();
        boolean notAuthorised = true;

        for (User usr : users) {
            if (usr.equals(user)) {
                notAuthorised = false;
                chain.doFilter(request, res);
            }
        }


        if (notAuthorised){
            HttpServletResponse httpResponse = (HttpServletResponse) res;
            httpResponse.sendRedirect("index.html");
        }

    }

    @Override
    public void destroy() {

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
