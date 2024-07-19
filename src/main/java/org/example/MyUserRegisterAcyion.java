package org.example;

import java.util.Scanner;

public class MyUserRegisterAcyion implements MyAction{
    private static final String ACTION_NAME = "register";

    private Scanner scanner = null;
    private MyUserManager userManager = null;
    
    public MyUserRegisterAcyion(Scanner scanner,MyUserManager userManager){
        this.scanner=scanner;
        this.userManager= userManager;
    }

    public String getActionName() {
        return MyUserRegisterAcyion.ACTION_NAME;
    }

    public void run (String [] args){
        System.out.println("现在你在用户注册子菜单里");
        while(true){
            System.out.println("请输入用户名：");
            String username =this.scanner.nextLine();

            System.out.print("请输入密码：");
            String password =this.scanner.nextLine();

            boolean success = this.userManager.registerUser(username, password);

            if(success)
                break;
            
        }
    }
}