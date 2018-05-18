package fr.epsi.i4.soupeoserver.ws;

import com.google.gson.Gson;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.model.export.ExportParcours;
import fr.epsi.i4.soupeoserver.model.morphia.Mood;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class ExportDataService {

	@GetMapping("/export")
	public void exportUserSession() throws Exception {
		List<UserSession> userSessions = MainDAO.findAll(UserSession.class);

		List<ExportParcours> parcours = new ArrayList<>();
		for (UserSession userSession : userSessions) {
			for (Parcours p : userSession.getParcours()) {
				parcours.add(ExportParcours.fromParcours(p));
			}
		}

		List<Mood> mood = new ArrayList<>();
		for (UserSession userSession : userSessions) {
			mood.addAll(userSession.getMood());
		}

		Gson gson = new Gson();
		String parcoursJson = gson.toJson(parcours);
		String moodJson = gson.toJson(mood);

		File parcoursFile = new File("../../data/parcours.json");
		FileOutputStream parcoursOutputStream = new FileOutputStream(parcoursFile);
		parcoursOutputStream.write(parcoursJson.getBytes());

		File moodFile = new File("../../data/mood.json");
		FileOutputStream moodOutputStream = new FileOutputStream(moodFile);
		moodOutputStream.write(moodJson.getBytes());

		parcoursOutputStream.close();
		moodOutputStream.close();
	}
}
