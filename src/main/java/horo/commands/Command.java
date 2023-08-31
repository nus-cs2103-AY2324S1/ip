package horo.commands;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import horo.HoroException;
import horo.Storage;
import horo.Ui;
import horo.data.TaskList;

public abstract class Command {

  protected String name;
  protected String regex;
  protected String displayFormat;

  public Command(String name, String regex, String displayFormat) {
    this.name = name;
    this.regex = regex;
    this.displayFormat = displayFormat;
  }

  /**
   * Executes the command.
   *
   * @param tasks   The TaskList to be worked on.
   * @param storage The Storage to be worked on.
   * @throws HoroException Error.
   */
  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HoroException;

  public MatchResult validateAndParse(String input) throws HoroException {
    Pattern pattern = Pattern.compile(regex);
    Matcher m = pattern.matcher(input);
    if (!m.find()) {
      throw new HoroException("Wrong command format\n" + displayFormat);
    }
    return m.toMatchResult();
  }
}
