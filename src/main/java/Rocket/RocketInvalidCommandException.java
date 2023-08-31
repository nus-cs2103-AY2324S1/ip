package Rocket;

import Rocket.RocketException;

public class RocketInvalidCommandException extends RocketException {
    public RocketInvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
