package fr.epsi.i4.soupeoserver;

import fr.epsi.i4.soupeoserver.analyzer.decisiontree.AnalyseDecisionTree;
import fr.epsi.i4.soupeoserver.dao.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoupeoServerApplication {

	public static String mongoDBUrl = "localhost";

	public static int mongoDBPort = 27017;

	public static String mongoDBDatabase = "soupeo";

	public static final Connection connection = new Connection();

	public static AnalyseDecisionTree analyseDecisionTree = new AnalyseDecisionTree();

	public static void main(String[] args) {
		analyseDecisionTree.initDecisionTree();
		SpringApplication.run(SoupeoServerApplication.class, args);
	}
}
