/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rangepartitioning;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kaustubhrajpathak
 */
public class RangePartitioning {
    
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db = "jdbc:mysql://localhost/anchal";
    static final String user = "root";
    static final String pass = "root";
    static int maxvalue = 20;
    
    Connection con;
    Statement stmt;
    
    public void initDatabase(int p0,int p1){
        try{
            Class.forName(jdbc_driver);
            con = (Connection) DriverManager.getConnection(db, user, pass);
            stmt = (Statement) con.createStatement();
            String  query = "create table student(roll_no int not null, name varchar(50))"
                    + "partition by range(roll_no)("
                    + "partition p0 values less than("
                    + p0+"),"
                    + "partition p1 values less than("
                    + p1+"),"
                    + "partition p2 values less than("
                    + maxvalue+"));";
            System.out.println(query);
            stmt.execute(query);
            System.out.print("Created Successfully!\n");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.print(e.getMessage());
        }
    }

    public void insertDatabase(int roll,String name){
         try{
            String  query = "insert into student values("
                    + roll+","+"'"+name+"'"+");";
            System.out.println(query);
            stmt.execute(query);
            System.out.print("Inserted Successfully!\n");
        }
        catch(SQLException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void selectPartition(int part){
            try{   
            String  query = "select * from student partition("+"p"+part+");";
            System.out.print(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.print("Roll no : "+rs.getInt("roll_no")+" |\t");
                System.out.print("Name : "+rs.getString("name")+"\n");
            }
        }
        catch(SQLException e){
            System.out.print(e.getMessage());
        }
    }
}

