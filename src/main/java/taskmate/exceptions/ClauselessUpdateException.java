package taskmate.exceptions;

/**
 * ClauselessUpdateException is thrown when an update command is created, but there are no /name, /by, /from, or /to
 * clauses in the command.
 */
public class ClauselessUpdateException extends InvalidDescriptionException {
}
