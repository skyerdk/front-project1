package wl.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class FindLadder {
    private final int status;
    private final String errMessage;
    private String ladder;

    public FindLadder(int s, String m, String l){
        this.status = s;
        this.errMessage = m;
        this.ladder = l;
    }
    public int getStatus() {
        return status;
    }

    public String getLadder() {
        return ladder;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
