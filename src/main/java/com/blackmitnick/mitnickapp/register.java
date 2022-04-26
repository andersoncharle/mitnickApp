package com.blackmitnick.mitnickapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        Connection connection;
        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/StruggleDb","root","");
            if (connection !=null) {
                System.out.println("Successfully connected to MySQL database test");

                String fname = "anderson";
                String lname = "charles";
                int age = 22;
                String username = "blackmitnick";
                String insert= "INSERT INTO student VALUES(2,'"+fname+"','"+lname+"','"+age+"','"+username+"')";

                // String sql = "INSERT INTO student VALUES (100, 'Zara','Ali',22,'anderson')";
                String sql= "INSERT INTO student(fname, lname, age, username) VALUES(?,?,?,?)";

                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,"rose");
                pstmt.setString(2,"charles");
                pstmt.setInt(3,12);
                pstmt.setString(4,"rosemary");

                int count = pstmt.executeUpdate();
                if (count != 0) {
                    System.out.println("Record inserted successfully!!");
                }else{
                    System.out.println("Record not inserted ???");
                }
                Statement statement= connection.createStatement();

                String select = "SELECT * FROM student";

                ResultSet rs= statement.executeQuery(select);

                while (rs.next()){
                    int id =  rs.getInt("id");
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lname");
                    int ageS = rs.getInt("age");
                    String userName = rs.getString("username");
                    System.out.println(", First: " + id);
                    System.out.println(", First: " +firstName);
                    System.out.println(", Last: " +lastName);
                    System.out.println(", age: " +ageS);
                    System.out.println(", User name: " +userName);
                }

//                        statement.executeUpdate(sql);
//                statement.executeUpdate(insert);

            }

            // statement.executeQuery();
            assert connection != null;
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");

    }
}
