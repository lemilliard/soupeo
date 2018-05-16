package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Embedded;

import java.util.Date;

@Embedded
class IndicePb {

    private int level;
    private Date date;
    private String cause;

    IndicePb(int level, Date date, String cause) {
        this.setLevel(level);
        this.setDate(date);
        this.setCause(cause);
    }

    protected int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    protected String getCause() {
        return cause;
    }

    protected void setCause(String cause) {
        this.cause = cause;
    }
}