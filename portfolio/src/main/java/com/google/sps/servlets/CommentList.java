package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comments-list")
public class CommentList extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);

       DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
       PreparedQuery results = datastore.prepare(query);
       

       ArrayList<Comment> allComments = new ArrayList<>();
       for (Entity entity : results.asIterable()) {
            long id = entity.getKey().getId();
            String text = (String) entity.getProperty("text");
            long timestamp = (long) entity.getProperty("timestamp");

            Comment comment = new Comment(id, text, timestamp);
            allComments.add(comment);
        }

        String json = new Gson().toJson(allComments);
        response.setContentType("application/json;");
        response.getWriter().println(json);
  }
}