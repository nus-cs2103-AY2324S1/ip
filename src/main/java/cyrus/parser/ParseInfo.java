package cyrus.parser;

import cyrus.commands.CommandType;

import java.util.HashMap;

/**
 * Packet of information about the parsing response.
 */
public class ParseInfo {
  /**
   * Default {@code ParseInfo} for an empty command.
   */
  public final static ParseInfo EMPTY = new ParseInfo(
      CommandType.UNKNOWN,
      "",
      new HashMap<>()
  );

  private final CommandType commandType;
  private final String argument;
  private final HashMap<String, String> options;

  public ParseInfo(CommandType commandType, String argument, HashMap<String, String> options) {
    this.commandType = commandType;
    this.argument = argument;
    this.options = options;
  }

  /**
   * Returns if parsed command has no argument.
   * @return true if parsed command has no arguments
   */
  public boolean hasNoArgument() {
    return this.argument.equals("");
  }

  /**
   * Returns {@code argument} of the command.
   * @return argument of command
   */
  public String getArgument() {
    return this.argument;
  }

  /**
   * Create a copy of the options to avoid accidentally modifying the current options map.
   * @return Copy of options hashmap.
   */
  public HashMap<String, String> getOptions() {
    return new HashMap<>(this.options);
  }

  /**
   * Returns {@code commandType} of the command.
   * @return command type
   */
  public CommandType getCommandType() {
    return this.commandType;
  }
}
