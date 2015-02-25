package pegPuz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pegPuz.GamePlayableBoard.Dir;
import pegPuz.GamePlayableBoard.Piece;

public class HexBoardTest {

	HexBoard board;
	
	@Before
	public void setUp() throws Exception {
		board = new HexBoard();
	}

	@Test
	public void checkInitBoard() {
		assertEquals("check for an undefined location", Piece.UNDEFINED, board.getWhatIsAtLoc(0));


		assertEquals("check for empty location: 1", Piece.EMPTY, board.getWhatIsAtLoc(1));
		assertEquals("check for empty location: 1", Piece.EMPTY, board.getWhatIsAtLoc(15));
		assertEquals("check for empty location: 1", Piece.EMPTY, board.getWhatIsAtLoc(16));
		assertEquals("check for empty location: 1", Piece.EMPTY, board.getWhatIsAtLoc(17));
		assertEquals("check for empty location: 19", Piece.EMPTY, board.getWhatIsAtLoc(19));
	}

	@Test
	public void checkSetLoc(){
		//location should be empty at first
		assertEquals("", Piece.EMPTY, board.getWhatIsAtLoc(19));
		
		board.setWhatIsAtLoc(19, Piece.PEG);
		assertEquals("", Piece.PEG, board.getWhatIsAtLoc(19));
		
		board.setWhatIsAtLoc(1, Piece.PEG);
		assertEquals("", Piece.PEG, board.getWhatIsAtLoc(1));
	}
	
	@Test
	public void checkNeighborLoc () {
		int loc = board.getLocInDir(1, Dir.E);
		assertEquals("", 2, loc);
		
		assertEquals("", 7, board.getLocInDir(3, Dir.SE));
	}
	
	@Test
	public void checkValidDir(){
		Dir[] directions = board.getNeighborDir(1);
		Dir [] validDir = {Dir.E, Dir.SE, Dir.SW};
		assertArrayEquals("", validDir, directions);
	}
	
	@Test
	public void newGame(){
	
	}
	
	@Test
	public void countBoard(){
		assertEquals("", 19, board.countOnBoard(Piece.EMPTY));
	}
	
	@Test
	public void getMax() {
		assertEquals("", 19, board.getMaxLoc());
		GamePlayableBoard b = new GamePlayableBoard();
		assertEquals("", 15, b.getMaxLoc());

	}
}
