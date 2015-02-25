package pegPuz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pegPuz.GamePlayableBoard.Piece;
import pegPuz.PegPuzGameModel.GameState;

public class HexModelTest {

	HexModel model;
	@Before
	public void setUp() throws Exception {
		model = new HexModel();
	}

	@Test
	public void checkState() {
		assertEquals("check for correct state on instantiation", GameState.Ready, model.getState());
	}
	
	@Test
	public void newGame(){
		model.newGame();
		assertEquals("", Piece.PEG ,model.getWhatIsAtLoc(2));
		assertEquals("", Piece.PEG, model.getWhatIsAtLoc(15));
		assertEquals("", Piece.PEG, model.getWhatIsAtLoc(16));
		assertEquals("", Piece.PEG, model.getWhatIsAtLoc(19));
	}

}
