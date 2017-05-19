package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import model.StatsModel;

public class dummyControllerTester {

	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		String path = System.getProperty("user.dir") + "\\model";
		String file = "\\stats.xml";
		
		File fileCheck = new File(path, file);
		StatsController dummyController = new StatsController(JAXBContext.newInstance(StatsModel.class));
		if (fileCheck.exists()) {
			dummyController.addStatsModel(path, file);
		} else {
			StatsModel statsModel = new StatsModel();
			statsModel.setHighscore(10);
			statsModel.setName("Jan");
			dummyController.addStatsModel(statsModel);
		}
		dummyController.generateXML();
	}

}
