package pegPuz;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PegPuzBoardViewer extends JPanel {
	public static final int PREFER_WID = 550;
	public static final int PREFER_HT = 400;
	public static final float HT_TO_WID_RATIO = 0.866f;
	public static final int GRID_UNITS_PER_SIDE = 10;
	private int horizontalOffset;
	private int verticalOffset;
	private int pixPerVertGridUnit;
	private int pixPerHorGridUnit;
	Shape gameBoard;
	Color backColor = Color.YELLOW;
	Color holeColor = Color.BLACK;
	Color pegColor = Color.GRAY;
	Color selColor = Color.RED;
	
	int previousWidth = 0;
	int previousHeight = 0;
	PegPuzGameModel model;
	
	int[][] locByGrid = { {5,1}, {4,3}, {6,3}, {3,5}, {5, 5}, {7,5}, {2,7}, {4,7}, {6,7}, {8,7}, {1,9}, {3,9}, {5,9}, {7,9}, {9,9} };
	int MAX_LOCATIONS = locByGrid.length;
	int[][] locXY;
	
	public PegPuzBoardViewer(PegPuzGameModel model, int preferWid, int preferHt){
		this.model = model;
		setPreferredSize(new Dimension(preferWid,preferHt));
		locXY = new int[MAX_LOCATIONS][];
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
	}
	
	public PegPuzBoardViewer(PegPuzGameModel model) {
		this(model, PREFER_WID,PREFER_HT);
	}
	
	public void computeDrawingSetup(int wid, int ht) {
		if (previousWidth == wid && previousHeight == ht) return;
		System.out.printf("DEBUG: computeDrawingSetup(%d, %d)\n", wid, ht);
		if ((float) ht/wid < HT_TO_WID_RATIO) {
			pixPerVertGridUnit = ht / GRID_UNITS_PER_SIDE;
			verticalOffset = pixPerVertGridUnit;
			pixPerHorGridUnit = Math.round(pixPerVertGridUnit/HT_TO_WID_RATIO);
			horizontalOffset = (wid-8*pixPerHorGridUnit)/2;
		} else {
			pixPerHorGridUnit = wid / GRID_UNITS_PER_SIDE;
			horizontalOffset = pixPerHorGridUnit;
			pixPerVertGridUnit = Math.round(pixPerHorGridUnit * HT_TO_WID_RATIO);
			verticalOffset = (ht-8*pixPerVertGridUnit)/2;
		}
		previousWidth = wid;
		previousHeight = ht;
		
		gameBoard = createBoardBacking(wid,ht);
		for (int p=0; p<MAX_LOCATIONS; p++) {
			locXY[p][0] = (locByGrid[p][0]-1)*pixPerHorGridUnit+ horizontalOffset;
			locXY[p][1] = (locByGrid[p][1]-1)*pixPerVertGridUnit+ verticalOffset;
		}
	}
	
	public Shape createBoardBacking(int wid, int ht) {
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo(horizontalOffset, ht-verticalOffset);
		triangle.lineTo(wid-horizontalOffset, ht-verticalOffset);
		triangle.lineTo(horizontalOffset+4*pixPerHorGridUnit, verticalOffset);
		triangle.closePath();
		return triangle;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Color savedColor = g2.getColor();
		g2.setColor(backColor);
		computeDrawingSetup(getWidth(), getHeight());
		g2.fill(gameBoard); 
		g2.setColor(holeColor);
		paintLine(g2, 10, 0);
		paintLine(g2, 11, 2);
		paintLine(g2, 12, 5);
		paintLine(g2, 13, 9);
		paintLine(g2, 0, 14);
		paintLine(g2, 1, 13);
		paintLine(g2, 3, 12);
		paintLine(g2, 6, 11);
		paintLine(g2, 1, 2);
		paintLine(g2, 3, 5);
		paintLine(g2, 6, 9);
		paintLine(g2, 10, 14);
		for (int p=0; p<MAX_LOCATIONS; p++) {
			paintSpot(g2, p, model.getWhatIsAtLoc(p+1) == GamePlayableBoard.Piece.PEG);
		}
		g2.setColor(savedColor);
	}
	
	private void paintLine(Graphics2D g2, int spotID, int spotID2){
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(locXY[spotID][0]-pixPerHorGridUnit/128, locXY[spotID][1]-pixPerHorGridUnit/128, locXY[spotID2][0]-pixPerHorGridUnit/128, locXY[spotID2][1]-pixPerHorGridUnit/128);
	}
	
	private void paintSpot(Graphics2D g2, int spotID, boolean isPeg) {
		if (isPeg)
			g2.setColor(pegColor);
		else 
			g2.setColor(holeColor);
		
		if(model.getSelectedMove() == (spotID+1))
			g2.setColor(selColor);
		
		
		g2.fillOval(locXY[spotID][0]-pixPerHorGridUnit/2, locXY[spotID][1]-pixPerHorGridUnit/2, pixPerHorGridUnit, pixPerHorGridUnit);
	}
	
	public int whichSpot(int x, int y) {
		int pickRadius = pixPerHorGridUnit/2;
		for (int p = 0; p < MAX_LOCATIONS; p++) {
			if (Math.abs(locXY[p][0]-x) <= pickRadius && Math.abs(locXY[p][1]-y) <= pickRadius)
				return p+1;
		}
		return -1;
	}
	
	public void buildGUI() {
		for (int p=0; p< MAX_LOCATIONS; p++) {
			locXY[p] = new int[2]; // space for x and y for each loc
		}
	}
	
	public void updateGUI() {
		repaint();
	}
}
