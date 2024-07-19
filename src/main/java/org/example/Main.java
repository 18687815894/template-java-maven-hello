package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello word");

        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.initializeDatabase();

        MyUserManager userManager = new MyUserManager();
        Manager manager = new Manager();

        Scanner scanner =new Scanner(System.in);

        List<MyAction>  actionList =new ArrayList<MyAction>();

        MyUserRegisterAcyion userRegister = new MyUserRegisterAcyion(scanner,userManager);
        actionList.add(userRegister);

        MyUserLoginAction userLogin = new MyUserLoginAction(scanner,userManager);
        actionList.add(userLogin);


       // for(int i=0;i<actionList.size();i++)
       // {
       //     actionList.get(i).run(args);
       // }
        manager.Login(scanner);

       // manager.ChangePassword(scanner);

       // manager.ListUserinfomation();
       // manager.FindUserinfomation(scanner);
        //manager.DeleteUserinfomation(scanner);
       // manager.Addgoods(scanner);
       // manager.Findgoods(scanner);//有问题
        manager.Deletegoods(scanner);

    }
}