package command;

import org.junit.jupiter.api.Test;
import command.Commands;
import command.Commands.TwoCommands;
import command.Commands.ThreeCommands;
import command.Commands.COMMANDS;
import task.Task;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommandsTest {
    @Test
    public void commandsEqualCommands() {
        for (Commands.COMMANDS c : Commands.COMMANDS.values()) {
            for (Commands.COMMANDS d : Commands.COMMANDS.values()) {
                if (c == d) {
                    assertEquals(new Commands(c), new Commands(d));
                } else {
                    assertNotEquals(new Commands(c), new Commands(d));
                }
            }
        }
    }


    @Test
    public void commandsExecute() {

    }

}
