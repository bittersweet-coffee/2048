package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.ScoreModel;

public class ScoreModelTest {
	
	private ScoreModel scoreModel;

	@Before
	public void setUp() throws Exception {
		this.scoreModel = new ScoreModel();
	}

	@Test
	public void testInit() {
		this.scoreModel.init();
		Assert.assertEquals(0, this.scoreModel.getScore().intValue());
	}

	@Test
	public void testSetInteger() {
		this.scoreModel.set(10);
		Assert.assertEquals(10, this.scoreModel.getScore().intValue());
	}

	@Test
	public void testGetScore() {
		Assert.assertNull(this.scoreModel.getScore());
	}
}
