package main;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public void update(){
        String input = this.scanner.nextLine();
        Main.getInstance().getParser().executeCommand(input);
    }

    public void say(String content){
        this.say(content, true,true);
    }
    public void say(String content, boolean outputUpperLine, boolean outputLowerLine){
        if(outputUpperLine){
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    " + content);
        if(outputLowerLine){
            System.out.println("    ____________________________________________________________");
        }

    }

    public void dispose(){
        this.scanner.close();
    }
}
