/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rangepartitioning;

import java.util.Scanner;

/**
 *
 * @author kaustubhrajpathak
 */
public class Main {
    
    public static void main(String[] args) {
        
        //Range Partitioning
        RangePartitioning r = new RangePartitioning();
        int p0,p1;
        int partopt;
        Scanner c = new Scanner(System.in);
        System.out.print("Enter partitioning value 1 :-");
        p0 = Integer.parseInt(c.next());
        System.out.print("Enter partitioning value 2 :-");
        p1 = Integer.parseInt(c.next());
        r.initDatabase(p0,p1);
        int roll;
        String name;
        String opt = "y";
        while(opt.equals("y")){
            System.out.print("Enter roll no and name of student :-");
            roll = Integer.parseInt(c.next());
            name = c.next();
            r.insertDatabase(roll,name);
            System.out.println("Enter y to add more details!");
            opt = c.next();
        }
        System.out.println("Select partition 0,1,2 :-");
        partopt = Integer.parseInt(c.next());
        r.selectPartition(partopt);
        
        //List Partitioning
        ListPartitioning l = new ListPartitioning();
        int pl0[] = new int[10];
        int pl1[] = new int[10];
        System.out.print("Enter no.of partition values for partition 0 :-");
        int plc0 = Integer.parseInt(c.next());
        for(int i=0;i<plc0;i++){
            System.out.println("Enter value in partition 0:-");
            pl0[i] = Integer.parseInt(c.next());
        }
        System.out.print("Enter no.of partition values for partition 1 :-");
        int plc1 = Integer.parseInt(c.next());
        for(int i=0;i<plc1;i++){
            System.out.println("Enter value in partition 1:-");
            pl1[i] = Integer.parseInt(c.next());
        }
        l.initDatabase(pl0, pl1,plc0,plc1);
        String opt1 = "y";
        while(opt1.equals("y")){
            System.out.print("Enter roll no and name of student :-");
            roll = Integer.parseInt(c.next());
            name = c.next();
            l.insertDatabase(roll,name);
            System.out.println("Enter y to add more details!");
            opt1 = c.next();
        }
        System.out.println("Select partition 0,1 :-");
        partopt = Integer.parseInt(c.next());
        l.selectPartition(partopt);
    }
    
}
    
