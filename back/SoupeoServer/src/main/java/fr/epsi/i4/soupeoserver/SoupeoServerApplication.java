package fr.epsi.i4.soupeoserver;

import fr.decisiontree.DecisionTree;
import fr.epsi.i4.soupeoserver.analyzer.decisiontree.AnalyseDecisionTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoupeoServerApplication {

	public static String mongoDBUrl = "localhost";

	public static int mongoDBPort = 27017;

	public static String mongoDBDatabase = "soupeo";

	public static AnalyseDecisionTree analyseDecisionTree;

	public static void main(String[] args) {
		analyseDecisionTree.initDecisionTree();
		SpringApplication.run(SoupeoServerApplication.class, args);
	}
}
