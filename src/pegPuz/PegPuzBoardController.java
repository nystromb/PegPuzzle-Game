package pegPuz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import pegPuz.PegPuzGameModel.GameState;


public class PegPuzBoardController extends MouseAdapter {
	
	PegPuzBoardViewer view;
	PegPuzGameModel model;
	
	public PegPuzBoardController(PegPuzGameModel model, PegPuzBoardViewer view) {
		this.view = view;
		this.model = model; 
	}
	
	public void mouseClicked(MouseEvent e) {
		int locId = view.whichSpot(e.getX(), e.getY());
		
		if (locId != -1)
			model.playLoc(locId);
		
		System.out.printf("DEBUG:Controller: mouse clicked at %d,%d --> %d\n", e.getX(), e.getY(), locId);
		view.updateGUI();
		
		if(model.isGameOver()){
			JOptionPane.showMessageDialog(null, "No more valid moves. The game is over!");
			model.newGame();
			view.updateGUI();
		}

	}
	
	public void newGameClicked(){
		model.newGame();
		view.updateGUI();
	}
}
