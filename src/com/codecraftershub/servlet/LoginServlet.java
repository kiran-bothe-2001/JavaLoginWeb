package com.codecraftershub.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

   private static final String URL = System.getenv("DB_URL");
private static final String DB_USER = System.getenv("DB_USER");
private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM users WHERE email = ?";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    URL,
                    DB_USER,
                    DB_PASSWORD);

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) {

                    // Create login session
                    HttpSession session = request.getSession();

                    session.setAttribute("userEmail", email);
                    session.setAttribute(
                            "fullName",
                            rs.getString("full_name"));

                    // Redirect to HomeServlet
                    response.sendRedirect(
                            request.getContextPath() + "/home");

                } else {

                    response.setContentType("text/html");

                    response.getWriter().println(
                            "<h2>Login Failed</h2>" +
                                    "<p>Invalid email or password.</p>");
                }

            } else {

                response.setContentType("text/html");

                response.getWriter().println(
                        "<h2>Login Failed</h2>" +
                                "<p>Invalid email or password.</p>");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html");

            response.getWriter().println(
                    "<h2>Login Failed</h2>" +
                            "<p>Unable to process login.</p>");
        }
    }
}
