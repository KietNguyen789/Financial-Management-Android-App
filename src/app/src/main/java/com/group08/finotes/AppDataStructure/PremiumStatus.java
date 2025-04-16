package com.group08.finotes.AppDataStructure;

import java.io.Serializable;
import java.time.Instant;

public class PremiumStatus implements Serializable {
    private String state;
    public long getTimeToDied() {
        return timeToDied;
    }

    @Override
    public String toString() {
        return "PremiumStatus{" +
                "state='" + state + '\'' +
                ", timeToDied=" + timeToDied +
                '}';
    }

    public void setTimeToDied(long timeToDied) {
        this.timeToDied = timeToDied;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private long timeToDied;
    PremiumStatus(){
        this.state = "guest";
        this.timeToDied = 0;
    }

}
