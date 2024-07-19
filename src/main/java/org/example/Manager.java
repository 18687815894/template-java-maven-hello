package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager {
   private MyUserManager Manager=new MyUserManager();
   private boolean islogin =false;
    Manager()
    {
        Manager.registerUser("admin", "ynuinfo#777");
    }

    public void Login(Scanner scanner){
        System.out.println("现在你在管理员登录子菜单里");
        while(true){
            System.out.print("请输入密码：");
            String password =scanner.nextLine();

            boolean success = this.Manager.login("admin", password);
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
        try(Connection connection = DriverManager.getConnection(Manager.getDB());
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

    public void ListUserinfomation(){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        while(true){
            try (Connection connection = DriverManager.getConnection(Manager.getDB());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users")){
                ResultSet resultSet = statement.executeQuery();

             if(resultSet.next()){
                String storedname = resultSet.getString("username");
                System.out.println("客户"+storedname);
            } 
            }catch (Exception e) {
                // TODO: handle exception
                System.out.println("查找完毕");
                break;
            }
        }
    }


    public void DeleteUserinfomation(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try(Connection connection = DriverManager.getConnection(Manager.getDB());
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE username=?")){
            System.out.println("输入要删除的用户名：");
            String name=scanner.nextLine();
            statement.setString(1,name);
            statement.executeUpdate();
            System.out.println(" DeleteUserinfomation successfully!");
        } catch (SQLException e){
            System.out.println("Failed to DeleteUserinfomation:"+e.getMessage());
        }
    }

    public void FindUserinfomation(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try (Connection connection = DriverManager.getConnection(Manager.getDB());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username=?")){
            System.out.println("输入要查询的用户名：");
            String name=scanner.nextLine();
            statement.setString(1,name);
         //   statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
         if(resultSet.next()){
            Integer id = resultSet.getInt("id");
            System.out.println("客户id:"+id);
        } 
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("查找失败"+e.getMessage());
        }
    }

    public void Deletegoods(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try(Connection connection = DriverManager.getConnection(Manager.getDB());
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Goods WHERE name=?")){
            System.out.println("输入要删除的商品名称：");
            String name=scanner.nextLine();
            statement.setString(1,name);
            statement.executeUpdate();
            System.out.println(" Deletegoods successfully!");
        } catch (SQLException e){
            System.out.println("Failed to Deletegoods:"+e.getMessage());
        }
    }

    public void Findgoods(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try (Connection connection = DriverManager.getConnection(Manager.getDB());
        PreparedStatement statement = connection.prepareStatement("SELECT (name,num) FROM Goods WHERE id=1")){
            System.out.println("输入要查询的商品id：");
            Integer id=scanner.nextInt();
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
         if(resultSet.next()){
            String name = resultSet.getString("name");
            Integer num = resultSet.getInt("num");
            System.out.println("商品:"+name+"数量"+num);
        } else
        System.out.println("未查找到");
        //statement.executeUpdate();
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("查找失败"+e.getMessage());
        }
    }

    public void Addgoods(Scanner scanner){
        if(!islogin){
            System.out.println("未登录无法操作");
            return;
        }
        try (Connection connection = DriverManager.getConnection(Manager.getDB());
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Goods (name,num) VALUES (?,?)")){
            System.out.println("输入要添加的商品名：");
            String name=scanner.nextLine();
            System.out.println("输入要添加商品的数量：");
            Integer num=scanner.nextInt();
            statement.setString(1,name);
            statement.setInt(2,num);
            statement.executeUpdate();
            System.out.println("添加成功");
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("添加失败"+e.getMessage());
        }
    }
}
