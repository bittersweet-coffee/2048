package tests;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.StatsModel;

public class StatsModelTest {
	
	private StatsModel statsModel;
	private StatsModel statsModel2;

	@Before
	public void setUp() throws Exception {
		this.statsModel = new StatsModel();
		String path = Paths.get(
				System.getProperty("user.dir"),
				"src",
				"tests",
				"statsTest.xml"
				).toString();
		this.statsModel2 = new StatsModel(path);
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBooleanBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testStatsModel() {
		StatsModel model = new StatsModel();
		Assert.assertTrue(model.equals(statsModel));
	}

	@Test
	public void testStatsModelString() {
		String path = Paths.get(
				System.getProperty("user.dir"),
				"src",
				"tests",
				"statsTest.xml"
				).toString();
		StatsModel model = new StatsModel(path);
		Assert.assertTrue(model.equals(statsModel2));
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentHighscore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAbort() {
		Assert.assertTrue(!statsModel.getAbort());
	}

	@Test
	public void testGetSave() {
		Assert.assertTrue(!statsModel.getSave());
	}

	@Test
	public void testGetCurrentName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPath() {
		Assert.assertTrue(this.statsModel.getPath().contains("stats.xml"));
	}

}
