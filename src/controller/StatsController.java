package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.StatsModel;

public class StatsController {

	private StatsModel statsModel;
	private JAXBContext context;

	public StatsController(JAXBContext context) {
		this.context = context;
	}

	public void addStatsModel(String path, String file)
			throws JAXBException, FileNotFoundException {
		try {

			Unmarshaller unMarshaller = this.context.createUnmarshaller();
			this.statsModel = (StatsModel) unMarshaller
					.unmarshal(new FileReader(path + file));
			System.out.println(this.statsModel.getCurrentName());
			System.out.println(this.statsModel.getCurrentHighscore());
		} catch (JAXBException e) {
			System.out.println("JAXBContext could not load Model.");
			e.printStackTrace();
		}

	}

	public void addStatsModel(StatsModel statsModel) {
		this.statsModel = statsModel;
	}

	public void generateXML() throws JAXBException {
		Marshaller marshaller = this.context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// Write to System.out
		marshaller.marshal(this.statsModel, System.out);
		String path = System.getProperty("user.dir");
		path = path + "\\model";
		String file = "stats.xml";
		marshaller.marshal(this.statsModel, new File(path, file));

	}

}
