package s2.threading;

/**
 * Created by russl on 12/11/2016.
 */
public class ExecutionException extends Exception {

    public ExecutionException() {
        super();
    }

    public ExecutionException(String message) {
        super(message);
    }


    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }


    public ExecutionException(Throwable cause) {
        super(cause);
    }


    protected ExecutionException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
