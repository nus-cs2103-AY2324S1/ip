package main;

import command.*;
import task.*;
import util.FileUtil;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Main INSTANCE;
    private String name = "your girlfriend";
    private HashMap<String, ICommandHandler> commands;
    private boolean isRunning;

    private TaskList taskList;

    public Main(){
        this.taskList = FileUtil.createTaskListFromFile();
        this.commands = new HashMap<String,ICommandHandler>();
        this.commands.put("intro", new CommandIntroHandler());
        this.commands.put("list", new CommandListHandler());
        this.commands.put("add", new CommandAddTaskHandler());
        this.commands.put("bye", new CommandExitHandler());
        CommandMarkUnmarkHandler temp = new CommandMarkUnmarkHandler();
        this.commands.put("mark", temp);
        this.commands.put("unmark", temp);
        this.commands.put("todo", new CommandTodoHandler());
        this.commands.put("deadline", new CommandDeadlineHandler());
        this.commands.put("event", new CommandEventHandler());
        this.commands.put("delete", new CommandDeleteHandler());
    }

    public static void main(String[] args) {
        initialize();
        INSTANCE.run();
    }

    private void run(){
        this.executeCommand("intro");
        this.isRunning = true;
        Scanner sc = new Scanner(System.in);
        while(this.isRunning){
            String input = sc.nextLine();
            this.executeCommand(input);
        }
        sc.close();
        return;
    }

    private static void initialize(){
        INSTANCE = new Main();
    }
    private void executeCommand(String command){
        if(command.length() == 0){
            this.say("You didn't say anything.");
            return;
        }
        String[] splitedCommand = command.split(" ");
        try{
            if(command.equals("blah")){
                throw new CommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if(this.commands.containsKey(splitedCommand[0])){
                this.commands.get(splitedCommand[0]).execute(command, splitedCommand);
            }
            else{
                this.commands.get("add").execute(command, splitedCommand);
            }
        }
        catch (CommandException e){
            this.say("An exception happened when executing this command: " + e.getMessage());
        }

    }

    public static Main getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return this.name;
    }

    public void exit(){
        this.isRunning = false;
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

    public TaskList getTaskList(){
        return this.taskList;
    }
}
