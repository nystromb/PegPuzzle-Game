package pegPuz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pegPuz.GamePlayableBoard.Dir;
import pegPuz.GamePlayableBoard.Piece;

public class PegPuzBoardTest {

	GamePlayableBoard board;
	
	@Before
	public void setUp() throws Exception {
		board = new GamePlayableBoard();
	}

	@Test
	public void checkInitialBoard() {
		int firstLoc = 1;
		assertEquals("check first location on board", Piece.EMPTY, board.getWhatIsAtLoc(firstLoc));	
	}
	
	@Test
	public void checkSetLoc(){
		assertEquals("", Piece.EMPTY, board.getWhatIsAtLoc(1));
		board.setWhatIsAtLoc(1, Piece.PEG);
		assertEquals("", Piece.PEG, board.getWhatIsAtLoc(1));
	}
	
	@Test
	public void checkBadLoc(){
		int undef = 16;
		int firstloc = 0;
		assertEquals("check for bad location", Piece.UNDEFINED, board.getWhatIsAtLoc(undef));
		assertEquals("check location zero", Piece.UNDEFINED, board.getWhatIsAtLoc(firstloc));
	}

	@Test
	public void checkIsEmpty(){
		assertTrue("check if board is empty", board.isEmpty());
		board.setWhatIsAtLoc(8, Piece.PEG);
		assertFalse("check if board is empty", board.isEmpty());
	}
	
	@Test
	public void checkFillWith(){
		board.fillWith(Piece.PEG);
		assertEquals("board should be filled with PEGS", Piece.PEG, board.getWhatIsAtLoc(10));
		}
	
	@Test
	public void checkGetNeighborDir(){
		Dir[] firstNeighbor = new Dir[2];
		firstNeighbor[0] = Dir.SE;
		firstNeighbor[1] = Dir.SW;
		assertArrayEquals("Check if expected and returned array are equal", firstNeighbor, board.getNeighborDir(1));
		Dir[] fiveNeighbor = {Dir.NE, Dir.E, Dir.SE, Dir.SW, Dir.W, Dir.NW};
		assertArrayEquals("Check middle peg's neighbors", fiveNeighbor, board.getNeighborDir(5));
	}	
	
	@Test
	public void checkGetLocInDir(){
		int neighborLoc = 12;
		assertEquals("neighbor location should match expected location", neighborLoc, board.getLocInDir(13, Dir.W));
	}
	
	@Test
	public void checkCountOnBoard(){
		board.setWhatIsAtLoc(1, Piece.PEG);
		board.setWhatIsAtLoc(5, Piece.PEG);
		board.setWhatIsAtLoc(15, Piece.PEG);
		assertEquals("check the piece count on board", 3, board.countOnBoard(Piece.PEG));
	}
}
