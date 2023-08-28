package ui.inputparser;

import exceptions.KniazRuntimeException;


/**
 * Abstract class encapsulating logic of parsing & tokenizing commands given to Kniaz
 */
public abstract class KniazParser {
    // doesn't need to be instantiated to do its job
    // contains just the logic for parsing input/outputs after Kniaz decides what kind of input it is
    // helps keep Kniaz neat

    /**
     * Valid pattern for Event commands
     * TODO : Implement similar regex for all commands
     */
    private static final String EVENTPATTERN  = ".*from.*to.*";

    /**
     * Parses the given line and breaks it down into a KniazCommand
     * If an unknown instruction is entered, throws a KniazRunTimeException
     * @param rawLine the line to parse
     * @return the Command that line represented
     * @throws KniazRuntimeException what went wrong, such as invalid commands
     */
    public static KniazCommand parseCommand(String rawLine) throws KniazRuntimeException {
        String strippedLine = rawLine.strip();


        // The lines below are fairly self-explanatory
        // Just checks for each alias in our enum and makes the right KniazCommand
        // TODO : refactor this to get rid of the giant if-else ladder (if we can!)
        if (strippedLine.equals(InstructionType.QUIT.alias)) {
            return new KniazCommand(InstructionType.QUIT);
        }

        if (strippedLine.equals(InstructionType.LIST.alias)) {

            return new KniazCommand((InstructionType.LIST));

            // print out if we are asked to list
        } else if (strippedLine.startsWith(InstructionType.MARK.alias)) {

            // handle parsing which entry the user wants to mark here
            String entryAsString = KniazParser.getAfter(strippedLine,InstructionType.MARK.alias);

            return new KniazCommand(InstructionType.MARK, entryAsString);

        } else if (strippedLine.startsWith(InstructionType.UNMARK.alias)) {

            // handle parsing which entry user wants to unmark here
            String entryAsString = KniazParser.getAfter(strippedLine,InstructionType.UNMARK.alias);

            return new KniazCommand(InstructionType.UNMARK, entryAsString);


        } else if (strippedLine.startsWith(InstructionType.DELETE.alias)) {

            //handle parsing which entry user wants to delete here
            String entryAsString = KniazParser.getAfter(strippedLine,InstructionType.DELETE.alias);

            return new KniazCommand(InstructionType.DELETE,entryAsString);

        } else if (strippedLine.startsWith(InstructionType.TODO.alias)) {


            String taskName = KniazParser.getAfter(strippedLine,InstructionType.TODO.alias).strip();
            return new KniazCommand(InstructionType.TODO, taskName);

        } else if (strippedLine.startsWith(InstructionType.DEADLINE.alias)) {

            // pull the args for this command
            String deadlineArgs = KniazParser.getAfter(strippedLine, InstructionType.DEADLINE.alias);

            String[] tokenizedDlineArgs = deadlineArgs.split("\\s*/\\s*(by)\\s*");
            // regex \s* represents any arbitrary number of whitespace
            // Also strips the by out
            // so this split input strips whitespace in between delims
            // Split it up by the slash and strip whitespace
            return new KniazCommand(InstructionType.DEADLINE, tokenizedDlineArgs);

        } else if (strippedLine.startsWith(InstructionType.EVENT.alias)) {
            String eventArgs = KniazParser.getAfter(strippedLine, InstructionType.EVENT.alias);

            if (!eventArgs.matches(KniazParser.EVENTPATTERN)) { //Handles format validation here for Events
                throw new KniazRuntimeException(
                        String.format("Wrong input format in %s",eventArgs),
                        "Your input is formatted wrongly, try again.",
                        null);
            }
            String[] tokenizedEventArgs = eventArgs.split("\\s*/\\s*(from|to)\\s*");
            // gets rid of the from/to also, in addition to splitting and stripping whitespace

            return new KniazCommand(InstructionType.EVENT, tokenizedEventArgs);
        } else {
            throw new KniazRuntimeException(
                    String.format("Unrecognised input : %s", strippedLine),
                    String.format("I did not recognise %s as an input, try again.", strippedLine),
                    null);
        }
    }

    /**
     * Helper function to get after a given substring
     * @param fullString full string to scan
     * @param subString what substring to look for
     * @return everything in fullString that comes after subString
     */
    private static String getAfter(String fullString, String subString) {
        int indexOfSubString = fullString.indexOf(subString);
        return fullString.substring(indexOfSubString + subString.length()).strip();
        // add the substring length to skip to the end of it
    }

}
