package com.codecraftershub.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
private static final String URL =
        System.getenv("DB_URL") != null
        ? System.getenv("DB_URL")
        : System.getenv("MYSQL_URL");

private static final String DB_USER =
        System.getenv("DB_USER") != null
        ? System.getenv("DB_USER")
        : System.getenv("MYSQLUSER");

private static final String DB_PASSWORD =
        System.getenv("DB_PASSWORD") != null
        ? System.getenv("DB_PASSWORD")
        : System.getenv("MYSQLPASSWORD");    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        response.setContentType("text/html");

        if (!password.equals(confirmPassword)) {

            response.getWriter().println(
                    "<h2>Registration Failed</h2>" +
                    "<p>Passwords do not match.</p>");

            return;
        }

        String hashedPassword = BCrypt.hashpw(
                password,
                BCrypt.gensalt(12));

        String sql = "INSERT INTO users (full_name, email, mobile, password) " +
                     "VALUES (?, ?, ?, ?)";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    URL,
                    DB_USER,
                    DB_PASSWORD);

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, hashedPassword);

            int result = ps.executeUpdate();

            ps.close();
            connection.close();

            if (result > 0) {

             response.sendRedirect(
            request.getContextPath() + "/login.html"
           );

          } else {

                response.getWriter().println(
                        "<h2>Registration Failed</h2>");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<h2>Registration Failed</h2>" +
                    "<p>Unable to create account.</p>");
        }
    }
}
