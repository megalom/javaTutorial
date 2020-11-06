package com.megalom.tutorial.servlettut;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CollectionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getQueryString();
        resp.setContentType("text/html");
        try(PrintWriter printWriter = resp.getWriter()){
            printWriter.write("Collections tutorial:<br>"+query);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
