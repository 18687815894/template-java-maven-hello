package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


public class Customer {
    private MyUserManager customer=new MyUserManager();
   private boolean islogin =false;
   private HashMap<String,Integer> ShopingCart=new HashMap<String,Integer>();
   private HashMap<String,Integer> ShopingHistory=new HashMap<String,Integer>();

   public void Register(Scanner scanner){
        System.out.println("现在你在用户注册子菜单里");
         while(true){
         System.out.println("请输入用户名：");
         String username =scanner.nextLine();

         System.out.print("请输入密码：");
         String password =scanner.nextLine();

         boolean success = customer.registerUser(username, password);

         if(success)
           break;
       
        }
   }


    public void Login(Scanner scanner){
        System.out.println("现在你在用户登录子菜单里");
        while(true){
            System.out.print("请输入用户名：");
            String name =scanner.nextLine();

            System.out.print("请输入密码：");
            String password =scanner.nextLine();

            boolean success = customer.login(name, password);
            if(success){
                islogin =true;
                System.out.println("登录成功，返回上层目录");
                break;
            }else{
                System.out.println("登录失败，返回上层目录");
                break;
            }
        }
    } 

    public void Logout(){
        islogin=false;
        System.out.println("成功登出");
    }

   public void ChangePassword(Scanner scanner){
    if(!islogin){
        System.out.println("未登录无法操作");
        return;
    }
        try(Connection connection = DriverManager.getConnection(customer.getDB());
        PreparedStatement statement = connection.prepareStatement("UPDATE Users SET password=? WHERE username=?")){
            System.out.println("输入新密码：");
            String newpassword=scanner.nextLine();
            statement.setString(1,newpassword);
            statement.setString(2,"admin");
            statement.executeUpdate();
            System.out.println("Changepassword successfully!");
        } catch (SQLException e){
            System.out.println("Failed to Changepassword:"+e.getMessage());
        }
    }
    
    public void Addgoods(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try (Connection connection = DriverManager.getConnection(customer.getDB());
        PreparedStatement statement = connection.prepareStatement("SELECT num FROM Goods WHERE name=?")){
            System.out.println("输入要添加的商品名字：");
            String name=scanner.nextLine();
            statement.setString(1,name);
         //   statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
         if(resultSet.next()){
            Integer goodsNum = resultSet.getInt("num");
            System.out.println("输入要添加的商品数量：");
            Integer num=scanner.nextInt();
            if(num>goodsNum)
            System.out.println("商品不足");
            else{
                ShopingCart.put(name,num);
                try (Connection connection1 = DriverManager.getConnection(customer.getDB());
                 PreparedStatement statement1 = connection1.prepareStatement("UPDATE Goods SET num=? WHERE name=?")){
                    statement1.setInt(1,goodsNum-num);
                    statement1.setString(2,name);
                    statement.executeUpdate();
                 }catch (Exception e) {
            // TODO: handle exception
                 System.out.println("添加失败"+e.getMessage());
                 }
            }
        } 
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("添加失败"+e.getMessage());
        }
    }

    public void Deletegoods(Scanner scanner){

    }

    public void Pay(Scanner scanner){

    }

    public void viewHistory(){

    }

}

