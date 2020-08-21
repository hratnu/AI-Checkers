
/**
 * 
 */
/**
 * @author hratnu
 *
 */
import java.util.Arrays;
import java.util.Iterator;

public class state{
	int depth=3;
	
//	public static void main(String[] args)  {
//		System.out.println(rtrans(1));
//	}

	
	public void deepClone(char[][] arr, char[][] copy) {
	int cap= gameplay.size;
	//System.out.println("size is"+ cap);
	for(int i=0; i<cap; i++) 
		for(int j=0; j<cap; j++)
			copy[i][j]=arr[i][j];
			
	}
	
	
	public void printBoard(char[][] arr) {
		int counter=0;
		System.out.print("\t");
		for (int i=0; i<gameplay.size; i++) {
			System.out.print(i+1 + "\t");
		}
		System.out.println("\n");
		for (char[] i : arr) {
			System.out.print(intTochar(counter)+ "\t");
			for (char j : i)
				System.out.print(j + "\t");
			System.out.println("\n");
			counter++;
		}
		System.out.println("....");
	}
	
	
	
	public void printAll(Node node) {
		
		minimax(node,-500, 500);
	
		Iterator<Node> it= node.children.iterator();	
		while(it.hasNext()) {
			
			Node child = it.next();		
			printBoard(child.state);
			System.out.println("utility- "+child.utility+"\n");
			System.out.println("player-"+ child.player);
			printAll(child);
			
		
			System.out.println("-----"+"\n");
		}
			
	}
	
	
	public int minimax(Node node, int alpha , int beta) {// trying to find the best possible move from hence forth;
	
		if(node.children.isEmpty()==true) {//terminal test
			//System.out.println("raj!!!");
			node.utility=utility(node);
			return node.utility;
			
		}
		
		Iterator<Node> it= node.children.iterator(); //means has some children
		if(node.player.equals("comp")) { //means u want to find the children with max utility
			int max=-500;// random negative  number
		while(it.hasNext()) {		
			Node child= it.next();
			child.utility= (minimax(child, alpha, beta));
			if(child.utility>max) {
				max=child.utility;
			}
			alpha= Math.max(alpha, child.utility);
			if(beta<=alpha) 
				break;
			
		}
		return max;
		}
		
		else if(node.player.equals("human")) {
			int min=500;
		while(it.hasNext()) {
			Node child= it.next();
			child.utility= (minimax(child,alpha, beta));
			if(child.utility<min) {
				min=child.utility;
			}
			beta=Math.min(beta, child.utility);
			if(beta<=alpha)
				break;
		}
		return min;
		}
		
		return 999;
	}
	

	public  void fillSpace(char[][] arr, String rowcol, char peg) {
		int[] cells=trans(rowcol).clone();

		arr[cells[0]][cells[1]] = peg;

		cells= null;
		
		return;
	}
	
	
	public  void fillCell(char[][] arr, int r1, int c1, char peg) {
		arr[r1][c1] = peg;
		return;
	}
	
	public char intTochar(int i) {
		char c='z';
		switch(i) {
		case 0:
			c='a';
			break;
		case 1:
			c='b';
			break;
		case 2:
			c='c';
			break;
		case 3:
			c='d';
			break;
			
		case 4:
			c='e';
			break;
			
		case 5:
			c='f';
			break;
			
		case 6:
			c='g';
			break;
			
		case 7:
			c='h';
			break;
		}
		return c;
	}
	
	public int charToint(char c) {
		int i=9;
		switch(c) {
		case 'a':
			i = 0;
			break;
		case 'b':
			i = 1;
			break;
		case 'c':
			i = 2;
			break;
		case 'd':
			i = 3;
			break;
		case 'e':
			i = 4;
			break;
		case 'f':
			i = 5;
			break;
		case 'g':
			i = 6;
			break;
		case 'h':
			i = 7;
			break;
		case '1':
			i=0;
			break;
		case '2':
			i=1;
			break;
		case '3':
			i=2;
			break;
		case '4':
			i=3;
			break;
		case '5':
			i=4;
			break;
		case '6':
			i=5;
			break;
		case '7':
			i=6;
			break;
		case '8':
			i=7;
			break;
			
			
		}
		return i;
	}
	
	
	
	
	public static int[] trans(String rowcol) {
		int[] cells= new int[2];
		char row = rowcol.charAt(0);
		char col = rowcol.charAt(1);
		int colNum = Character.getNumericValue(col) - 1;

		int rowNum = 0;
		switch (row) {
		case 'a':
			rowNum = 0;
			break;
		case 'b':
			rowNum = 1;
			break;
		case 'c':
			rowNum = 2;
			break;
		case 'd':
			rowNum = 3;
			break;
		case 'e':
			rowNum = 4;
			break;
		case 'f':
			rowNum = 5;
			break;
		case 'g':
			rowNum = 6;
			break;
		case 'h':
			rowNum = 7;
			break;
		}
		cells[0]=rowNum;
		cells[1]=colNum;
		return cells;
	}
	
	public static char rtrans(int a) {
		char symb='z';
		switch (a) {
		case 0:
			symb = 'a';
			break;
		case 1:
			symb = 'b';
			break;
		case 2:
			symb = 'c';
			break;
		case 3:
			symb = 'd';
			break;
		case 4:
			symb = 'e';
			break;
		case 5:
			symb = 'f';
			break;
		case 6:
			symb = 'g';
			break;
		case 7:
			symb = 'h';
			break;
		}
		return symb;		
	}

	
	public static int[] trans2(String rowcol) {
		int[] cells= new int[4];
		char row = rowcol.charAt(0);
		char col = rowcol.charAt(1);
		char row2= rowcol.charAt(3);
		char col2= rowcol.charAt(4);
		int colNum = Character.getNumericValue(col) - 1;
		int colNum2 = Character.getNumericValue(col2) - 1;

		int rowNum = 0;
		switch (row) {
		case 'a':
			rowNum = 0;
			break;
		case 'b':
			rowNum = 1;
			break;
		case 'c':
			rowNum = 2;
			break;
		case 'd':
			rowNum = 3;
			break;
		}
		
		int rowNum2 = 0;
		switch (row2) {
		case 'a':
			rowNum2 = 0;
			break;
		case 'b':
			rowNum2 = 1;
			break;
		case 'c':
			rowNum2 = 2;
			break;
		case 'd':
			rowNum2 = 3;
			break;
		}
		cells[0]=rowNum;
		cells[1]=colNum;
		cells[2]=rowNum2;
		cells[3]=colNum2;
		return cells;
	}
	
	
	public void initialBoard(char[][] arr) {
int cap=gameplay.size;
		for (char[] i : arr)
			Arrays.fill(i, '-');
		
		if(gameplay.size==4) {
		for(int i=0; i<gameplay.size; i+=2) {
			arr[0][i]='b';
			arr[3][i+1]='w';
	
		}
	
		}
		
		if(gameplay.size==8) {
			for(int i=0; i<gameplay.size; i+=2) {
				arr[0][i+1]='b';
				arr[1][i]='b';
				arr[2][i+1]='b';
				arr[cap-1][i]='w';
				arr[cap-2][i+1]='w';
				arr[cap-3][i]='w';
				
				
			}
			
			
			
			
		}
		
		

		/*fillSpace(arr, "a2", 'b');

		fillSpace(arr, "a4", 'b');

		fillSpace(arr, "d1", 'w');

		fillSpace(arr, "d3", 'w');
		*/

	}

	public Node chooseBest(Node node) {
		int best_util=minimax(node,-500,500);
		//System.out.println("best possible util is"+ best_util);
		System.out.println("Computer moved a piece");
		Iterator<Node> it= node.children.iterator();	
		while(it.hasNext()) {
			
			Node child = it.next();	
			
			if(child.utility==best_util) {
				deepClone(child.state,node.state);
				break;
			}
		}
		return node;

	}
	

	public int utility(Node node) {
		int cap=gameplay.size;
		int terminal=0;
		for (int i = 0; i < cap; i++) {// capture by normal black
			for (int j = 0; j < cap; j++) {
				char c= node.state[i][j];
				if(c=='b') {
					terminal+=2;
				}if(c=='B') {
					terminal+=3;
				}
				if(c=='w') {
					terminal+=-1;
				}
				if(c=='W') {
					terminal+=-2;
				}
			}
		}

		return terminal;
	}
//make a method to convert blocks like a3 to 3 and b4 to 8
}