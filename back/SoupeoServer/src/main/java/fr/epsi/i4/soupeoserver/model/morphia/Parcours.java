package fr.epsi.i4.soupeoserver.model.morphia;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
class Parcours {

	private String start;
	private String url;
	private boolean help_used;
	private String end;
	private int timeSpent;

	public Parcours() {
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isHelp_used() {
		return help_used;
	}

	public void setHelp_used(boolean help_used) {
		this.help_used = help_used;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	@Override
	public String toString() {
		return "Parcours{" +
				"start='" + start + '\'' +
				", url='" + url + '\'' +
				", help_used=" + help_used +
				", end='" + end + '\'' +
				", timeSpent=" + timeSpent +
				'}';
	}
}