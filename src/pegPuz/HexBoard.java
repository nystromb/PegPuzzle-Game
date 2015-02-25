package pegPuz;

import java.util.ArrayList;
import java.util.List;

import pegPuz.GamePlayableBoard.Dir;

public class HexBoard extends GamePlayableBoard {
	private static int MAX_LOCATIONS = 19;
	private int[][] locNeighbors = new int[][]{
			{0,0,0,1,2,3,0,4,5,6,7,0,9,10,11,12,14,15,16},
			{2,3,0,5,6,7,0,9,10,11,12,0,14,15,16,0,18,19,0},
			{5,6,7,9,10,11,12,13,14,15,16,0,17,18,19,0,0,0,0},
			{4,5,6,8,9,10,11,0,13,14,15,16,0,17,18,19,0,0,0},
			{0,1,2,0,4,5,6,0,8,9,10,11,0,13,14,15,0,17,18},
			{0,0,0,0,8,2,3,0,4,5,6,7,8,9,10,11,13,14,15}
	};
	
	public HexBoard(){
		super(MAX_LOCATIONS);
	}

	@Override
	public Piece getWhatIsAtLoc(int loc) {
		if(loc < 1 || loc > 19){
			return Piece.UNDEFINED;
		}
		else {
			return gameLocation[loc - 1];
		}
	}

	@Override
	public Dir[] getNeighborDir(int loc) {
		//create list of directions based on the location
		List<Dir> dirList = new ArrayList<Dir>();
		//cycle through 2D array (directions X location) 
		for(int i = 0; i < 6; i++){
			//if the array value is not 0, then there is a neighbor
			if(locNeighbors[i][loc - 1] != 0){
				//add the direction to the list
				switch(i){
				case NE:
					dirList.add(Dir.NE);
					break;
				case E:
					dirList.add(Dir.E);
					break;
				case SE:
					dirList.add(Dir.SE);
					break;
				case SW:
					dirList.add(Dir.SW);
					break;
				case W:
					dirList.add(Dir.W);
					break;
				case NW:
					dirList.add(Dir.NW);
					break;
				}
			}
		}
		
		//create new array from the ArrayList, and return new array
		Dir[] DirArray = new Dir[dirList.size()];
		DirArray = dirList.toArray(DirArray);
		return DirArray;
	}
	
	@Override
	public int getLocInDir (int loc, Dir direction) {
		int locResult = 0; 
		
		switch(direction){
		case NE:
			locResult = locNeighbors[NE][loc - 1];
			break;
		case E:
			locResult = locNeighbors[E][loc - 1];
			break;
		case SE:
			locResult = locNeighbors[SE][loc - 1];
			break;
		case SW:
			locResult = locNeighbors[SW][loc - 1];
			break;
		case W:
			locResult = locNeighbors[W][loc - 1];
			break;
		case NW:
			locResult = locNeighbors[NW][loc - 1];
			break;
		}
		
		return locResult;
	}

}
