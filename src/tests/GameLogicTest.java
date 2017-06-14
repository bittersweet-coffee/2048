package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import generic.GameLogic;

public class GameLogicTest {
	
	Integer[][] testBoard;

	@Before
	public void setUp() throws Exception {
		this.testBoard = new Integer[GameLogic.ROW][GameLogic.COL];
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				testBoard[i][j] = GameLogic.INIT_FIELD_VALUE;
			}
		}
	}

	@Test
	public void testMoveRight() {
		this.testBoard[0][3] = 2;
		this.testBoard[0][2] = 2;
		this.testBoard[1][3] = 4;
		this.testBoard[1][2] = 8;
		
		Integer[][] realBoard = new Integer[GameLogic.COL][GameLogic.ROW];
		realBoard[0][3] = 4;
		realBoard[0][2] = 0;
		realBoard[1][3] = 4;
		realBoard[1][2] = 8;
		
		GameLogic.moveRight(this.testBoard, new Integer(0));
		Assert.assertEquals(4, GameLogic.getScore().intValue());
		Assert.assertEquals(this.testBoard[0][3], realBoard[0][3]);
		Assert.assertEquals(this.testBoard[0][2], realBoard[0][2]);
		Assert.assertEquals(this.testBoard[1][3], realBoard[1][3]);
		Assert.assertEquals(this.testBoard[1][2], realBoard[1][2]);
	}

	@Test
	public void testMoveLeft() {
		this.testBoard[0][0] = 2;
		this.testBoard[0][1] = 2;
		this.testBoard[1][0] = 4;
		this.testBoard[1][1] = 8;
		
		Integer[][] realBoard = new Integer[GameLogic.COL][GameLogic.ROW];
		realBoard[0][0] = 4;
		realBoard[0][1] = 0;
		realBoard[1][0] = 4;
		realBoard[1][1] = 8;
		
		GameLogic.moveLeft(this.testBoard, new Integer(0));
		Assert.assertEquals(4, GameLogic.getScore().intValue());
		Assert.assertEquals(this.testBoard[0][0], realBoard[0][0]);
		Assert.assertEquals(this.testBoard[0][1], realBoard[0][1]);
		Assert.assertEquals(this.testBoard[1][0], realBoard[1][0]);
		Assert.assertEquals(this.testBoard[1][1], realBoard[1][1]);
	}

	@Test
	public void testMoveUp() {
		this.testBoard[0][0] = 2;
		this.testBoard[1][0] = 2;
		this.testBoard[0][1] = 4;
		this.testBoard[1][1] = 8;
		
		Integer[][] realBoard = new Integer[GameLogic.COL][GameLogic.ROW];
		realBoard[0][0] = 4;
		realBoard[1][0] = 0;
		realBoard[0][1] = 4;
		realBoard[1][1] = 8;
		
		GameLogic.moveUp(this.testBoard, new Integer(0));
		Assert.assertEquals(4, GameLogic.getScore().intValue());
		Assert.assertEquals(this.testBoard[0][0], realBoard[0][0]);
		Assert.assertEquals(this.testBoard[1][0], realBoard[1][0]);
		Assert.assertEquals(this.testBoard[0][1], realBoard[0][1]);
		Assert.assertEquals(this.testBoard[1][1], realBoard[1][1]);
	}

	@Test
	public void testMoveDown() {
		this.testBoard[3][0] = 2;
		this.testBoard[2][0] = 2;
		this.testBoard[3][1] = 4;
		this.testBoard[2][1] = 8;
		
		Integer[][] realBoard = new Integer[GameLogic.COL][GameLogic.ROW];
		realBoard[3][0] = 4;
		realBoard[2][0] = 0;
		realBoard[3][1] = 4;
		realBoard[2][1] = 8;
		
		GameLogic.moveDown(this.testBoard, new Integer(0));
		Assert.assertEquals(4, GameLogic.getScore().intValue());
		Assert.assertEquals(this.testBoard[3][0], realBoard[3][0]);
		Assert.assertEquals(this.testBoard[2][0], realBoard[2][0]);
		Assert.assertEquals(this.testBoard[3][1], realBoard[3][1]);
		Assert.assertEquals(this.testBoard[2][1], realBoard[2][1]);
	}

	@Test
	public void testAddValue() {
		int counter = 0;
		GameLogic.addValue(this.testBoard, 2);
		for (int i = 0; i < GameLogic.ROW; i++) {
			for (int j = 0; j < GameLogic.COL; j++) {
				if (this.testBoard[i][j] > 0) {
					counter++;
				}
			}
		}
		Assert.assertEquals(2, counter);
	}

	@Test
	public void testGetScore() {
		Assert.assertEquals(0, GameLogic.getScore().intValue());
	}

	@Test
	public void testGetScoreValue() {
		Assert.assertEquals(0, GameLogic.getScoreValue().intValue());
	}

	@Test
	public void testSetGameOver() {
		GameLogic.setGameOver(true);
		Assert.assertTrue(GameLogic.getGameOver());
	}

	@Test
	public void testGetGameOver() {
		Assert.assertTrue(GameLogic.getGameOver());
	}

	@Test
	public void testSetGameWin() {
		GameLogic.setGameWin(true);
		Assert.assertTrue(GameLogic.getGameWin());
	}

	@Test
	public void testGetGameWin() {
		Assert.assertTrue(!GameLogic.getGameWin());
	}
}
