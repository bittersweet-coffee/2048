package tests;

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
		this.statsModel2.setName("johndoe");
	}

	@Test
	public void testInit() {
		this.statsModel.init();
		Assert.assertEquals("noName", this.statsModel.getCurrentName());
		Assert.assertEquals(0, this.statsModel.getCurrentHighscore());
	}

	@Test
	public void testSetBooleanBoolean() {
		this.statsModel2.set(true, true);
		Assert.assertTrue(this.statsModel2.getAbort());
		Assert.assertTrue(this.statsModel2.getSave());
	}

	@Test
	public void testSetInteger() {
		this.statsModel2.set(10);
		Assert.assertEquals(10, this.statsModel2.getCurrentHighscore());
	}

	@Test
	public void testSetBoolean() {
		// Cannot be tested that easily
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
		Assert.assertTrue(model.equals(this.statsModel2));
	}

	@Test
	public void testSetName() {
		this.statsModel2.setName("johnedoe2");
		Assert.assertEquals("johnedoe2", this.statsModel2.getCurrentName());
	}

	@Test
	public void testGetCurrentHighscore() {
		Assert.assertEquals(0, this.statsModel2.getCurrentHighscore());
	}

	@Test
	public void testGetAbort() {
		Assert.assertTrue(!this.statsModel.getAbort());
	}

	@Test
	public void testGetSave() {
		Assert.assertTrue(!this.statsModel.getSave());
	}

	@Test
	public void testGetCurrentName() {
		Assert.assertEquals("johndoe", this.statsModel2.getCurrentName());
	}

	@Test
	public void testGetPath() {
		Assert.assertTrue(this.statsModel.getPath().contains("stats.xml"));
	}
}
