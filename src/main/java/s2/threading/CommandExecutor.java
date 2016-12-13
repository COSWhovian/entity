package s2.threading;

/**
 * Created by russl on 12/10/2016.
 */

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * General class for executing a command by creating a new process with a given command string
 *
 * @author Robert
 */
public class CommandExecutor implements Runnable {
    private File directory;
    //    private String command;
    List<String> commandList;
    private Process process;
    private String[] environment = null;
    private int result;
    private boolean activeInd = false;
    StreamWatcher streamWatcher;


    //    private static Logger logger = Logger.getLogger(CommandExecutor.class.getTypeName());
    final static Logger logger = Logger.getLogger(CommandExecutor.class);


    //    protected void initializeLogging() {
//        this.streamWatcher = streamWatcher;
//    }
    public CommandExecutor(List<String> command) {
        this.commandList = command;
        this.directory = new File("."); // set the execution directory to be the current dir.

    }

    public void setStreamWatcher(StreamWatcher streamWatcher) {
        this.streamWatcher = streamWatcher;
    }

    public CommandExecutor(List<String> command, StreamWatcher streamWatcher) {
        this.commandList = command;
        this.streamWatcher = streamWatcher;
        this.directory = new File("."); // set the execution directory to be the current dir.

    }

    public CommandExecutor(List<String> command, File directory) {
        this.commandList = command;
        this.directory = directory;
    }

    public String[] getEnvironment() {
        return environment;
    }

    public void setEnvironment(String[] environment) {
        this.environment = environment;
    }

    public void halt() {
        logger.info("halt");
        setActiveInd(false);

        this.process.destroy();

        if ( outReader != null) {
            outReader.interrupt();
        }
    }

    protected void setActiveInd(boolean ind) {
        activeInd = ind;
    }

    public boolean getActiveInd() {
        return activeInd;
    }

    public int execute() throws ExecutionException {
        logger.info("command " + this.commandList);
        Runtime rt = Runtime.getRuntime();
        try {
            if (commandList == null || commandList.isEmpty()) {
                System.out.println("no command specified");
                throw new ExecutionException("no command specified");
            }


            ProcessBuilder processBuilder = new ProcessBuilder(commandList);
            processBuilder.redirectErrorStream(true);

            Process p1 = processBuilder.start();


             this.process = p1;
            this.setActiveInd(true);
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
            String format = localDateTime.format(dateTimeFormatter);
            if (streamWatcher == null) {
                streamWatcher = new DefaultStreamWatcher();
            }
            // set up stream reader for the standard   output of the process

              outReader = new ThreadedStreamReader(process.getInputStream(),
                    "exec." + format + ".out.log", streamWatcher);

            outReader.start();
            logger.info("stream readers started");
            this.result = process.waitFor();
            outReader.join();
            logger.info("process result:  " + result);
        } catch (IOException e) {
            logger.error("IOException-- issues with execution" + e.getLocalizedMessage());

            throw new ExecutionException(e);
        } catch (InterruptedException e) {
            logger.error("InterruptedException:  the process was interrupted:  " + e.getLocalizedMessage());
            halt();

            this.result = -1;
        }

        return result;
    }

    ThreadedStreamReader outReader ;

    public int getCommandResult() {
        return this.result;
    }

    public boolean commandFailed() {
        return this.result != 0;
    }

    public boolean commandSucceeded() {
        return this.result == 0;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public void setDirectory(String directory) {
        this.directory = new File(directory);
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<String> command) {
        this.commandList = command;
    }

    @Override
    public void run() {
System.out.println(" running ...");
        try {
            int execute = execute();
        } catch (ExecutionException e) {
            // TODO
            e.printStackTrace();
        }


    }
}

