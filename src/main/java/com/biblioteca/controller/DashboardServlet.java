package com.biblioteca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("rol") == null){
            response.sendRedirect("index.jsp");
            return;
        }

        String rol = (String) session.getAttribute("rol");

        if("Administrador".equals(rol)){

            request.getRequestDispatcher("/dashboard_admin.jsp")
                    .forward(request,response);

        }else{

            request.getRequestDispatcher("/dashboard_user.jsp")
                    .forward(request,response);

        }
    }
}
