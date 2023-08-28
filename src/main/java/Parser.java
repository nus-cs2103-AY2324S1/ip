package main.java;

public class Parser {
    private String command;
    private String[] initialParse;
    private String[] phaseParse;
    public Parser(String command) {
        this.command = command;
    }
    public Commands.COMMANDS mainCommand() {
        this.initialParse = command.split(" ",2);
        switch (initialParse[0]) {
            case ("bye"):
                return Commands.COMMANDS.BYE;
            case ("list"):
                return Commands.COMMANDS.LIST;
            case ("todo"):
                return Commands.COMMANDS.TODO;
            case ("deadline"):
                return Commands.COMMANDS.DEADLINE;
            case ("event"):
                return Commands.COMMANDS.EVENT;
            case ("mark"):
                return Commands.COMMANDS.MARK;
            case ("unmark"):
                return Commands.COMMANDS.UNMARK;
            case ("delete"):
                return Commands.COMMANDS.DELETE;
            case ("by"):
                return Commands.COMMANDS.BY;
            case ("from"):
                return Commands.COMMANDS.FROM;
            case ("to"):
                return Commands.COMMANDS.TO;
            case ("sort"):
                return Commands.COMMANDS.SORT;
            case ("find"):
                return Commands.COMMANDS.FIND;
            default:
                return Commands.COMMANDS.UNKNOWN;
        }
    }

    protected String secondWord() {
        try {
            if (this.initialParse[1].equals("")) {
                return null;
            } else {
                return this.initialParse[1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    protected String phaseParse() {
        try {
            this.phaseParse = this.initialParse[1].split("/");
            return phaseParse[0];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    protected String phaseTwo() {
        try {
            return this.phaseParse[1];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    protected String phaseThree() {
        try {
            return this.phaseParse[2];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
