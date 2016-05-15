package jundl77.izou.addon.izoucecoutputcontroller;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.output.OutputController;

import java.io.IOException;

/**
 * <p>
 * </p>
 *
 * @author Julian Brendl
 */
public class CECOutputController extends OutputController {
    /**
     * The ID of the CECOutputController
     */
    public static final String ID = CECAddOn.class.getCanonicalName();

    private String commandPrefix = "echo \"";
    private String commandSuffix = "\" | cec-client";
    private String commandEndNow = " -s";

    public CECOutputController(Context context) {
        super(context, ID);
    }

    @Override
    public boolean turnOn() {
        String onCommand = getContext().getPropertiesAssistant().getProperty(CECCommands.ON.name());
        return executeCommand(onCommand, true);
    }

    @Override
    public boolean turnOff() {
        String offCommand = getContext().getPropertiesAssistant().getProperty(CECCommands.OFF.name());
        return executeCommand(offCommand, true);
    }

    private boolean executeCommand(String command, boolean end) {
        CommandLine cmdLine = new CommandLine(commandPrefix);
        cmdLine.addArgument(command);
        cmdLine.addArgument(commandSuffix);
        if (end) {
            cmdLine.addArgument(commandEndNow);
        }

        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(15000);
        executor.setWatchdog(watchdog);
        try {
            executor.execute(cmdLine);
            return true;
        } catch (IOException e) {
            error("Unable to execute CEC command: " + command);
            return false;
        }
    }
}
