package fr.epsi.i4.soupeoserver.model.morphia;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
class Parcours {

	private String start;
	private String url;
	private boolean help_used;

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
}