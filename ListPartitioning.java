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
public class ListPartitioning {
    
        
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db = "jdbc:mysql://localhost/anchal";
    static final String user = "root";
    static final String pass = "root";
    static int maxvalue = 20;
    
    Connection con;
    Statement stmt;
    
    public void initDatabase(int p0[],int p1[],int plc0,int plc1){
        try{
            Class.forName(jdbc_driver);
            con = (Connection) DriverManager.getConnection(db, user, pass);
            stmt = (Statement) con.createStatement();
            String p0v = getString(p0,plc0);
            String p1v = getString(p1,plc1);
            String  query = "create table student_list(roll_no int not null, name varchar(50))"
                    + "partition by list(roll_no)("
                    + "partition p0 values in("
                    + p0v+"),"
                    + "partition p1 values in("
                    + p1v+"));";
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
            String  query = "insert into student_list values("
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
            String  query = "select * from student_list partition("+"p"+part+");";
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
    
    public String getString(int p[],int len){
        
        String ret = "";
        for(int i=0;i<len;i++){
            if(i==len-1)
                ret = ret+p[i];
            else
                ret = ret+p[i]+",";
        }
        
        return ret;
    }
    
}
