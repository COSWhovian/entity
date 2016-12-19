package s2.entities.common;

/**
 * Created by russl on 12/16/2016.
 */
public enum InUseCd {
    DEP("deprecated"),
    OFF("off"),
    ON("on"),
    PND("pending");

    private String code;

    private InUseCd( String code ) {
        this.code = code;
    }
    public String code() {
        return this.code;
    }


}
