package parser;

import exceptions.KniazRuntimeException;


public abstract class KniazParser {
    // doesn't need to be instantiated to do its job
    // contains just the logic for parsing input/outputs after Kniaz decides what kind of input it is
    // helps keep Kniaz neat

    private static final String EVENTPATTERN  = ".*from.*to.*";

    public enum InstructionType {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        MARK("mark"),
        UNMARK("unmark"),
        LIST("list"),
        QUIT("bye"),
        INVALID("");

        public final String alias;

        private InstructionType(String alias){
            this.alias = alias;
        }



    }

    public static KniazCommand parseCommand(String line) throws KniazRuntimeException {

        if (line.equals(InstructionType.QUIT.alias)) {

            return new KniazCommand(InstructionType.QUIT);
        }

        if (line.equals(InstructionType.LIST.alias)) {

            return new KniazCommand((InstructionType.LIST));

            // print out if we are asked to list
        } else if (line.startsWith(InstructionType.MARK.alias)) {

            // handle parsing which entry the user wants to mark here
            String entryAsString = KniazParser.getAfter(line,"mark");

            return new KniazCommand(InstructionType.MARK, entryAsString);

        } else if (line.startsWith(InstructionType.UNMARK.alias)) {

            // handle parsing which entry user wants to unmark here
            String entryAsString = KniazParser.getAfter(line,"unmark");
            int entryAsInt = Integer.parseInt(entryAsString.strip());
            int entryToMark = entryAsInt - 1;

            return new KniazCommand(InstructionType.UNMARK, entryAsString);
        } else if (line.startsWith(InstructionType.TODO.alias)) {

            String taskName = KniazParser.getAfter(line,"todo").strip();
            // interpret everything else that isn't special as a task to add
            return new KniazCommand(InstructionType.TODO, taskName);

        } else if (line.startsWith(InstructionType.DEADLINE.alias)) {

            // pull the args for this command
            String deadlineArgs = KniazParser.getAfter(line, "deadline");

            String[] tokenizedDlineArgs = deadlineArgs.split("\\s*/\\s*(by)\\s*");
            // regex \s* represents any arbitrary number of whitespace
            // so this split input strips whitespace in between delims
            // Split it up by the slash and strip whitespace
            return new KniazCommand(InstructionType.DEADLINE, tokenizedDlineArgs);

        } else if (line.startsWith(InstructionType.EVENT.alias)) {
            String eventArgs = KniazParser.getAfter(line, "event");

            if (!eventArgs.matches(KniazParser.EVENTPATTERN)) {
                throw new KniazRuntimeException(
                        String.format("Wrong input format in %s",eventArgs),
                        "Your input is formatted wrongly, try again.",
                        null);
            }
            String[] tokenizedEventArgs = eventArgs.split("\\s*/\\s*(from|to)\\s*");

            return new KniazCommand(InstructionType.EVENT, tokenizedEventArgs);
        } else {
            throw new KniazRuntimeException(
                    String.format("Unrecognised input : %s", line),
                    String.format("I did not recognise %s as an input, try again.", line),
                    null);
        }
    }

    private static String getAfter(String fullString, String subString) {
        int indexOfSubString = fullString.indexOf(subString);
        return fullString.substring(indexOfSubString + subString.length()).strip();
        // add the substring length to skip to the end of it
    }

}
