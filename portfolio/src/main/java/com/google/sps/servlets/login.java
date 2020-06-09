package com.google.sps.servlets;

import com.google.sps.data.Comment;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@WebServlet("/login")
public class login extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    String loginStat = "0";
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      String userEmail = userService.getCurrentUser().getEmail();
      loginStat = "1";

      String urlToRedirectToAfterUserLogsOut = "/login";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
      response.getWriter().write(loginStat);
      response.getWriter().println("<p>Hello " + userEmail + "!</p>");
      response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
      response.getWriter().println("<p>Get back to home <a href=\""+"/index.html"+"\">here</a>.</p>");
    } else {
      String urlToRedirectToAfterUserLogsIn = "/login";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
      loginStat = "0";

      response.getWriter().write(loginStat);
      response.getWriter().println("<p>Hello stranger.</p>");
      response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
      response.getWriter().println("<p>Get back to home <a href=\""+"/index.html"+"\">here</a>.</p>");
    }
  }
}
