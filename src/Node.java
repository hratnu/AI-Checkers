import java.util.ArrayList;
import java.util.Iterator;
public class Node {
	String player;
	int utility;
	char[][] state;
	ArrayList<Node> children;
	

	public Node(String player, char[][] state) {
		this.utility=-99;
		this.player = player;
		this.state = state;
		this.children= new ArrayList<Node>();
	}

	public String getPlayer() {
		return player;
	}

	
	public void setPlayer(String player) {
		this.player = player;
	}

	public char[][] getState() {
		return state;
	}

	public void setState(char[][] state) {
		this.state = state;
	}
	
	
	
	
}
	

		
