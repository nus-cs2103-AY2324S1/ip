package catbot.internal;

import catbot.LambdaOutput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandMapTest {

    @Test
    public void run_validCommand_runCommand() {
        LambdaOutput<String> defaultOutput = new LambdaOutput<>();
        LambdaOutput<String> output = new LambdaOutput<>();

        CommandMap map = new CommandMap()
                .setDefaultCommand(defaultOutput::setOutput)
                .addCommand("cmd", output::setOutput);

        map.run("cmd", "args");

        assertNull(defaultOutput.getOutput());
        assertEquals(output.getOutput(), "args");
    }

    @Test
    public void run_noCommands_runDefaultCommand() {
        LambdaOutput<String> defaultOutput = new LambdaOutput<>();

        CommandMap map = new CommandMap()
                .setDefaultCommand(defaultOutput::setOutput);

        map.run("cmd", "args");

        assertEquals(defaultOutput.getOutput(), "cmd");
    }

    @Test
    public void run_invalidCommand_runDefaultCommand() {
        LambdaOutput<String> defaultOutput = new LambdaOutput<>();
        LambdaOutput<String> output = new LambdaOutput<>();

        CommandMap map = new CommandMap()
                .setDefaultCommand(defaultOutput::setOutput)
                .addCommand("test", output::setOutput);

        map.run("cmd", "args");

        assertEquals(defaultOutput.getOutput(), "cmd");
        assertNull(output.getOutput());
    }

}
