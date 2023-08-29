package main.java;

import java.time.LocalDateTime;

public class Commands {
    enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, SORT, FIND, UNKNOWN}

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

    public void execute() {

    }

    static class TwoCommands extends Commands {
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
    }

    static class ThreeCommands extends Commands {
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
    }
}
