package fr.epsi.i4.soupeoserver.model.export;

import fr.epsi.i4.soupeoserver.model.morphia.Parcours;

/**
 * @author Thomas Kint
 */
public class ExportParcours {

	private String url;

	private int help_used;

	public static ExportParcours fromParcours(Parcours parcours) {
		ExportParcours exportParcours = new ExportParcours();
		exportParcours.url = parcours.getUrl().split("\\?")[0].split("&")[0];
		exportParcours.help_used = (parcours.isHelp_used() ? 1 : 0);
		return exportParcours;
	}
}
