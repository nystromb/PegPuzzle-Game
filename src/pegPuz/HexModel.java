package pegPuz;

import pegPuz.GamePlayableBoard.Piece;

public class HexModel extends PegPuzGameModel {

	private HexBoard board;
	
	public HexModel() {
		board = new HexBoard();
	}

	@Override
	public GameState getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}

	@Override
	public int getSelectedMove() {
		// TODO Auto-generated method stub
		return super.getSelectedMove();
	}

	@Override
	public boolean isFirstMoveLegal(int locID) {
		// TODO Auto-generated method stub
		return super.isFirstMoveLegal(locID);
	}

	@Override
	public boolean isSecMoveLegal(int locID) {
		// TODO Auto-generated method stub
		return super.isSecMoveLegal(locID);
	}

	@Override
	public void playLoc(int locID) {
		// TODO Auto-generated method stub
		super.playLoc(locID);
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		super.newGame();
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return super.isGameOver();
	}

	@Override
	public Piece getWhatIsAtLoc(int locID) {
		// TODO Auto-generated method stub
		return board.getWhatIsAtLoc(locID);
	}

	@Override
	public void setWhatIsAtLoc(int i, Piece pieceType) {
		// TODO Auto-generated method stub
		board.setWhatIsAtLoc(i, pieceType);
	}
	
}
