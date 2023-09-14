package duke;

import command.Executable;
import dukeexception.InvalidVarException;

/**
 * Represents a function in the parser that can throw an InvalidVarException.
 */
@FunctionalInterface
public interface ParserFunction {
    Executable apply(String paramString) throws InvalidVarException;
}
