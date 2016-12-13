package s2.threading;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.apache.logging.log4j.spi.LoggerContext;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by russl on 12/11/2016.
 */
public class CmdExecDemo1 {
    public CmdExecDemo1() {
        System.setProperty("log4j.configurationFile", "C:\\logs\\log4j2.xml");
        System.setProperty("logFilename", "C:\\logs\\demo.log.txt");
        LoggerContext lContect = LogManager.getContext();

        Field f = null;
        try {
            f = lContect.getClass().getDeclaredField("configuration");
            f.setAccessible(true);
            XmlConfiguration iWantThis = (XmlConfiguration) f.get(lContect);
            System.out.println("Config File: " + iWantThis.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Logger logger = org.apache.log4j.LogManager.getRootLogger();

        logger.debug("HEHEHEHEHHE");


        String[] cmd = {"cmd.exe", "/c", "dir", "c:\\local\\movie-buffer\\"};
        List<String> cmdList = Arrays.asList(cmd);

        CommandExecutor commandExecutor = new CommandExecutor(cmdList);
        CmdWatch sw = new CmdWatch();

        commandExecutor.setStreamWatcher(sw);

        Thread t = new Thread(commandExecutor);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" sw::errStat=" + sw.getErrStat());
    }

    public class CmdWatch implements StreamWatcher {
        boolean errStat = false;

        public boolean getErrStat() {
            return errStat;
        }

        public void setErrStat(boolean errStat) {
            this.errStat = errStat;
        }

        @Override
        public void observe(String line) {
            String conditional = "primer";
            if (line.contains(conditional)) {
                System.out.println("**** " + conditional + " FOUND ****");
                setErrStat(true);

            }
        }
    }

    public static void main(String[] args) {
        CmdExecDemo1 cmdExecDemo1 = new CmdExecDemo1();






    }


}
