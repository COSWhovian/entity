package s2.threading;

/**
 * Created by russl on 12/11/2016.
 */
public class DefaultStreamWatcher implements StreamWatcher {
    @Override
    public synchronized void observe(String line) {
        if ( line.contains("Exception") || line.contains("exception") || line.contains("EXCEPTION")) {
            // exception encountered
        }
    }
}
