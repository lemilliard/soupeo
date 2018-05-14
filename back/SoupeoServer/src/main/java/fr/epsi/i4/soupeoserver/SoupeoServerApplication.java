package fr.epsi.i4.soupeoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoupeoServerApplication {

	public static String mongoDBUrl = "localhost";
	public static int mongoDBPort = 27017;



	public static void main(String[] args) {
		SpringApplication.run(SoupeoServerApplication.class, args);
	}
}
