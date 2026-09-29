package com.codecraftershub.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null || session.getAttribute("userEmail") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        String fullName = (String) session.getAttribute("fullName");

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println(
                "<!DOCTYPE html>" +
                        "<html lang='en'>" +

                        "<head>" +
                        "<meta charset='UTF-8'>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                        "<title>Code Crafters Hub Institute</title>" +

                        "<style>" +

                        "* {" +
                        "margin: 0;" +
                        "padding: 0;" +
                        "box-sizing: border-box;" +
                        "font-family: Arial, sans-serif;" +
                        "}" +

                        "body {" +
                        "background: #f5f7fb;" +
                        "color: #222;" +
                        "}" +

                        "nav {" +
                        "background: #111827;" +
                        "padding: 18px 7%;" +
                        "display: flex;" +
                        "justify-content: space-between;" +
                        "align-items: center;" +
                        "}" +

                        ".logo {" +
                        "color: white;" +
                        "font-size: 22px;" +
                        "font-weight: bold;" +
                        "}" +

                        ".logo span {" +
                        "color: #38bdf8;" +
                        "}" +

                        "nav ul {" +
                        "display: flex;" +
                        "list-style: none;" +
                        "gap: 25px;" +
                        "}" +

                        "nav ul li a {" +
                        "color: white;" +
                        "text-decoration: none;" +
                        "font-size: 15px;" +
                        "}" +

                        "nav ul li a:hover {" +
                        "color: #38bdf8;" +
                        "}" +

                        ".logout {" +
                        "background: #ef4444;" +
                        "padding: 9px 16px;" +
                        "border-radius: 6px;" +
                        "}" +

                        ".hero {" +
                        "min-height: 80vh;" +
                        "display: flex;" +
                        "justify-content: center;" +
                        "align-items: center;" +
                        "text-align: center;" +
                        "padding: 40px 20px;" +
                        "}" +

                        ".hero-content {" +
                        "max-width: 800px;" +
                        "}" +

                        ".hero h1 {" +
                        "font-size: 48px;" +
                        "margin-bottom: 15px;" +
                        "color: #111827;" +
                        "}" +

                        ".hero h1 span {" +
                        "color: #2563eb;" +
                        "}" +

                        ".tagline {" +
                        "font-size: 22px;" +
                        "color: #555;" +
                        "margin-bottom: 25px;" +
                        "}" +

                        ".welcome {" +
                        "font-size: 18px;" +
                        "margin-bottom: 30px;" +
                        "}" +

                        ".btn {" +
                        "display: inline-block;" +
                        "background: #2563eb;" +
                        "color: white;" +
                        "padding: 13px 25px;" +
                        "border-radius: 7px;" +
                        "text-decoration: none;" +
                        "margin: 5px;" +
                        "}" +

                        ".btn:hover {" +
                        "background: #1d4ed8;" +
                        "}" +

                        "footer {" +
                        "background: #111827;" +
                        "color: white;" +
                        "text-align: center;" +
                        "padding: 20px;" +
                        "}" +

                        "@media(max-width: 700px) {" +
                        "nav { flex-direction: column; gap: 15px; }" +
                        "nav ul { flex-wrap: wrap; justify-content: center; }" +
                        ".hero h1 { font-size: 34px; }" +
                        "}" +

                        "</style>" +
                        "</head>" +

                        "<body>" +

                        "<nav>" +

                        "<div class='logo'>" +
                        "Code Crafters <span>Hub</span>" +
                        "</div>" +

                        "<ul>" +
                        "<li><a href='home'>Home</a></li>" +
                        "<li><a href='#courses'>Courses</a></li>" +
                        "<li><a href='#about'>About</a></li>" +
                        "<li><a href='#contact'>Contact</a></li>" +
                        "<li><a class='logout' href='logout'>Logout</a></li>" +
                        "</ul>" +

                        "</nav>" +

                        "<section class='hero'>" +

                        "<div class='hero-content'>" +

                        "<h1>Welcome to <span>Code Crafters Hub</span></h1>" +

                        "<p class='tagline'>" +
                        "Crafting Coders. Creating Futures." +
                        "</p>" +

                        "<p class='welcome'>" +
                        "Welcome, <strong>" + fullName + "</strong>! " +
                        "You are successfully logged in." +
                        "</p>" +

                        "<a class='btn' href='#courses'>Explore Courses</a>" +
                        "<a class='btn' href='#contact'>Contact Us</a>" +

                        "</div>" +

                        "</section>" +

                        "<footer>" +
                        "&copy; 2026 Code Crafters Hub Institute. All Rights Reserved." +
                        "</footer>" +

                        "</body>" +
                        "</html>");
    }
}
