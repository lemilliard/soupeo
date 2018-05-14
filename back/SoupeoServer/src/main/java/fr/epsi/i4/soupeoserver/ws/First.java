package fr.epsi.i4.soupeoserver.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Kint
 */
@RestController
public class First {

	@GetMapping("/hello")
	public String hello() {

		return "Hello World!!";
	}
}
