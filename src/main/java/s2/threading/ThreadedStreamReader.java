package s2.threading;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by russl on 12/10/2016.
 */
public class ThreadedStreamReader extends Thread {
    private InputStream inputStream;
    private BufferedReader bufferedReader;

     final static  Logger logger =  LogManager.getLogger(ThreadedStreamReader.class);

     StreamWatcher streamWatcher;

    public ThreadedStreamReader(InputStream inputStream, String loggingFilename, StreamWatcher streamWatcher) {
         this.streamWatcher = streamWatcher;




        this.inputStream = inputStream;
         initReader();
     }

    private void initReader() {
        InputStreamReader streamreader = new InputStreamReader(this.inputStream);
        this.bufferedReader = new BufferedReader(streamreader);
    }

    public void run() {
        String line = null;
        logger.debug("ThreadedStreamReader -- begin reading stream...");
        try {
            while ((line = this.bufferedReader.readLine()) != null) {

                logger.info(line);
                streamWatcher.observe(line);
            }
        } catch (IOException ioe) {
            logger.info("ThreadedStreamReader -- issues reading stream");
        }
        logger.info("ThreadedStreamReader -- stream reading complete ...");
    }
}
