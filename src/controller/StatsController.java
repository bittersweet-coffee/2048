package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.ScoreModel;
import model.StatsModel;
import view.HighScoreView;

/**
 * TODO
 */
public class StatsController {

	private StatsModel statsModel;
	private JAXBContext context;
	private HighScoreView highScoreView;

	/**
	 * TODO
	 */
	public void addStatsModel(StatsModel statsModel) {
		this.statsModel = statsModel;
	}

	public void setHighScore(Integer score) {
		if (this.statsModel.getCurrentHighscore() <= score) {
			this.statsModel.setHighscore(score);
		}
	}

	/**
	 * TODO
	 * 
	 * @throws JAXBException
	 */
	public void generateXML() throws JAXBException {
		Marshaller marshaller = this.context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(this.statsModel,
				new File(this.statsModel.getPath(), this.statsModel.getFile()));
	}

	public void init() {

		try {
			File fileCheck = new File(this.statsModel.getPath(),
					this.statsModel.getFile());
			this.context = JAXBContext.newInstance(StatsModel.class);
			if (fileCheck.exists()) {

				loadModel();
				this.statsModel.addObserver(this.highScoreView);
				this.statsModel
						.setHighscore(this.statsModel.getCurrentHighscore());
			} else {
				this.statsModel.addObserver(this.highScoreView);
				this.statsModel.init();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addstatsModel(StatsModel statsModel) {
		this.statsModel = statsModel;

	}

	private void loadModel() throws JAXBException, FileNotFoundException {
		try {
			Unmarshaller unMarshaller = this.context.createUnmarshaller();
			this.statsModel = (StatsModel) unMarshaller
					.unmarshal(new FileReader(this.statsModel.getPath()
							+ this.statsModel.getFile()));
		} catch (JAXBException e) {
			System.out.println("JAXBContext could not load Model.");
			e.printStackTrace();
		}
	}

	public void addHighScoreView(HighScoreView highScoreView) {
		this.highScoreView = highScoreView;

	}

}
