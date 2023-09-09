package Alex;

/**
 * An abstract class represents general command which is going to be extended from a more concrete command class.
 * The classes that extend from this class need to overrite the method "public abstract void execute()" in order
 * to be a concrete class.
 */

public abstract class Command {
    /**
     * A common method that needs to be overriden by the class extending from Command class.
     */
    public abstract void execute();
}
