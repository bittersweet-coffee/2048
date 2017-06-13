package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.GameScreenModel;

public class GameScreenModelTest {
	
	private GameScreenModel gameScreenModel;

	@Before
	public void setUp() throws Exception {
		this.gameScreenModel = new GameScreenModel();
	}

	@Test
	public void testInit() {
		this.gameScreenModel.init();
		Assert.assertTrue(!this.gameScreenModel.getGameOver());
		Assert.assertTrue(!this.gameScreenModel.getGameWin());
	}

	@Test
	public void testSetBooleanBoolean() {
		this.gameScreenModel.set(true, true);
		Assert.assertTrue(this.gameScreenModel.getGameOver());
		Assert.assertTrue(this.gameScreenModel.getGameWin());
	}

	@Test
	public void testState() {
		// Cannot be tested that easily
	}

	@Test
	public void testGetGameOver() {
		Assert.assertTrue(!this.gameScreenModel.getGameOver());
	}
	
	@Test
	public void testGetGameWin() {
		Assert.assertTrue(!this.gameScreenModel.getGameWin());
	}
}
