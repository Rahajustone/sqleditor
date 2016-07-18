/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.sql;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rahmetullahh
 */
public class Lazim extends HttpServlet {
     private Connection connection;
     public void init() throws ServletException{
         try{
             Class.forName("com.mysql.jdbc.Driver");
             String dbURL = "jdbc:mysql://localhost:3306/testtozlu";
             String username = "root";
             String password = "";
             connection = DriverManager.getConnection(
                 dbURL, username, password);
         }
         catch(ClassNotFoundException e){
             System.out.println("Database driver not found.");
         }
         catch(SQLException e){
             System.out.println(
               "Error opening the db connection: "
                 + e.getMessage());
         }
     }
      public void destroy() {
         try{
             connection.close();
         }
         catch(SQLException e){
             System.out.println(
               "Error closing the db connection: "
                 + e.getMessage());
         }
     }


    
 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String sqlStatement =
             request.getParameter("sqlStatement");
      System.out.print("messaj"+sqlStatement);
         String message = "";
         try{
         Statement statement = connection.createStatement();
         sqlStatement = sqlStatement.trim();
         String sqlType = sqlStatement.substring(0, 6);
         if (sqlType.equalsIgnoreCase("select")){
         ResultSet resultSet =
             statement.executeQuery(sqlStatement);
         message =
             SQLUtil.getHtmlRows(resultSet);
     }
          else{
         int i = statement.executeUpdate(sqlStatement);
        
         if (i == 0) // this is a DDL statement
           message ="<table class='table'>"+
             "<tr><td>" +
               "The statement executed successfully." +
             "</td></tr>";
         else        // this is a DML statement
           message =
             "<tr><td>" +
               "The statement executed successfully.<br>" +
               i + " row(s) affected." +
             "</td></tr>";
         }
         statement.close();
     }
         catch(SQLException e){
         message = "Error executing the SQL statement: <br>"
                 + e.getMessage();
     }
           HttpSession session = request.getSession();
         session.setAttribute("message", message);
         session.setAttribute("sqlStatement", sqlStatement);
            RequestDispatcher dispatcher =
             getServletContext().getRequestDispatcher(
                 "/Editor.jsp");
         dispatcher.forward(request, response);
     }
       
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
