package org.example;

import java.util.Scanner;

public class MyUserLoginAction implements MyAction{
    private static final String ACTION_NAME ="login";

    private Scanner scanner = null;
    private MyUserManager userManager=null;

    public MyUserLoginAction(Scanner scanner,MyUserManager userManager){
        this.scanner=scanner;
        this.userManager=userManager;
    }

    public String getActionName(){
        return MyUserLoginAction.ACTION_NAME;
    }

    public void run(String[] arg){
        System.out.println("现在你在用户登录子菜单里");

        while(true){
            System.out.print("用户名：");
            String username =scanner.nextLine();

            System.out.print("密码：");
            String password =scanner.nextLine();

            boolean success= this.userManager.login(username, password);

            if((success)){
                System.out.println("登录成功，返回上层目录");
                break;
            }else{
                System.out.println("登录失败，返回上层目录");
                break;
            }
        }
    }
}