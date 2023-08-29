package main.java.Command;

import main.java.Task.ListOfTask;
import main.java.Ui.Ui;

import java.time.LocalDateTime;

public class Commands {
    public enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, SORT, FIND, UNKNOWN}

    private COMMANDS state;
    private String name;
    private int index;
    private LocalDateTime dateTime;

    public Commands(COMMANDS command) {
        this.state = command;
    }

    public Commands(COMMANDS command, String str) {
        this.state = command;
        this.name = str;
    }

    public Commands(COMMANDS command, int index) {
        this.state = command;
        this.index = index;
    }

    public Commands(COMMANDS command, LocalDateTime dateTime) {
        this.state = command;
        this.dateTime = dateTime;
    }

    public COMMANDS getCommand() {
        return this.state;
    }

    public int execute(ListOfTask taskList, Ui ui) {
        switch (this.state) {

        case BYE:
            ui.exit();
            return 0;

        case LIST:
            taskList.listTasks();
            break;

        case TODO:
            taskList.addTask(this.name);
            break;

        case FIND:
            //taskList.find(this.name);
            break;

        case SORT:
            break;

        case MARK:
            taskList.mark(this.index);
            break;

        case UNMARK:
            taskList.unMark(this.index);
            break;

        case DELETE:
            taskList.delete(this.index);
            break;

        case UNKNOWN:
            System.out.println("Unknown Command");
            break;
        }
        return 1;
    }

    public boolean compareTime(Commands c) {
        if (this.dateTime.compareTo(c.dateTime) < 0) {
            return true;
        }
        return false;
    }

    public static class TwoCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private Commands secondaryCommand;
        public TwoCommands(COMMANDS command, String str, COMMANDS command2, String str2) {
            super(command, str);
            this.state2 = command2;
            this.name2 = str2;
        }
        public TwoCommands(COMMANDS command, String str, Commands secondaryCommand) {
            super(command,str);
            this.secondaryCommand = secondaryCommand;
        }
        @Override
        public int execute(ListOfTask taskList, Ui ui) {
            switch (super.state) {

            case DEADLINE:
                taskList.addTask(super.name,this.secondaryCommand.dateTime);
                break;
            }
            return 1;
        }
    }

    public static class ThreeCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private COMMANDS state3;
        private String name3;
        private Commands phaseTwo;
        private Commands phaseThree;
        public ThreeCommands(COMMANDS command, String str, COMMANDS command2, String str2, COMMANDS command3, String str3) {
            super(command,str);
            this.state2 = command2;
            this.name2 = str2;
            this.state3 = command3;
            this.name3 = str3;
        }

        public ThreeCommands(COMMANDS command, String str, Commands phaseTwo, Commands phaseThree) {
            super(command,str);
            this.phaseTwo = phaseTwo;
            this.phaseThree = phaseThree;
        }

        @Override
        public int execute(ListOfTask taskList, Ui ui) {
            switch (super.state) {

            case EVENT:
                taskList.addTask(super.name, this.phaseTwo.dateTime, this.phaseThree.dateTime);
                break;
            }
            return 1;
        }
    }
}
