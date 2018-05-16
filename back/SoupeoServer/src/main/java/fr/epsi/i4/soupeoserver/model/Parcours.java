package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Embedded;

import java.util.Date;

@Embedded
class Parcours {

    private Date start;
    private String url;
    private boolean help_used;
    private Date end;

    Parcours(Date start, String url, boolean help_used, Date end) {
        this.setStart(start);
        this.setUrl(url);
        this.setHelp_used(help_used);
        this.setEnd(end);
    }

    protected Date getStart() {
        return start;
    }

    protected void setStart(Date start) {
        this.start = start;
    }

    protected String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    protected boolean isHelp_used() {
        return help_used;
    }

    protected void setHelp_used(boolean help_used) {
        this.help_used = help_used;
    }

    protected Date getEnd() {
        return end;
    }

    protected void setEnd(Date end) {
        this.end = end;
    }
}