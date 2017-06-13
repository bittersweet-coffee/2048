package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import generic.GameLogic;
import model.BoardModel;

public class BoardModelTest {

	private BoardModel boardModel;

	@Before
	public void setUp() throws Exception {
		this.boardModel = new BoardModel();
	}

	@Test
	public void testInit() {
		boardModel.init();
		Assert.assertEquals(4, this.boardModel.getModel().length);
	}

	@Test
	public void testSetIntegerArrayArray() {
		this.boardModel.set(new Integer[10][10]);
		Assert.assertEquals(10, this.boardModel.getModel().length);
	}

	@Test
	public void testResetModel() {
		this.boardModel.set(new Integer[10][10]);
		this.boardModel.resetModel();
		Assert.assertEquals(GameLogic.INIT_FIELD_VALUE,
				this.boardModel.getModel()[0][0].intValue());
	}

	@Test
	public void testGetModel() {
		Assert.assertEquals(4, this.boardModel.getModel().length);
	}
}
