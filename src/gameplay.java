import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class gameplay {
	static Scanner scan = new Scanner(System.in);
	static Scanner sc;
	static state s= new state();
	static Action a= new Action();
	static char[][] board;
	static Node root;
	static int size;
	static String whoIsplaying=" ";
	public static boolean gameover=false;
	public static void main(String[] args)  {
	
		
		while(true) {
		System.out.println("Do you want to play checkers 4*4(enter 1) or checkers 8*8(enter 2)?");
		try {
		sc=new Scanner(System.in);
		int choice= sc.nextInt();
		
		
		if(choice==1) {
			size=4;
			
			break;
		}
		else if(choice==2) {
			size=8;
			
			break;
		}
		else {
			System.out.println("Invalid input");
			continue;
			
		}
		}
		catch(Exception e) {
			System.out.println("Invalid Choice " + "\n" );
	
		}
		
		
		}
		
		if(size==4) {
			board= new char[4][4];
		}
		else if(size==8) {
			board= new char[8][8];
			
		}
			root= new Node("comp",board);
			
			s.initialBoard(board);
			System.out.println("START");
			s.printBoard(root.state);
			System.out.println("\n");
		
			for(int i=0; i<size*size; i++) {
				HI();
				if(gameover==true) {
					System.out.println("Good Game, but you lost!");
					System.out.println("Better luck next time...");
					return;
					
				}
				AI();
				if(gameover==true) {
					System.out.println("What a game!, You won!!");
					return;
				}
			}
			
			System.out.println("That was a long game! Let's call it a Draw!");

		}
	
			public static void HI() {
				gameover=false;
				whoIsplaying="alien";
				String turn;
				while(true) {
				System.out.println("White's turn, enter a move(for Hint press Enter) :");
				turn= scan.nextLine();
				 Boolean x = a.validate_turn(turn,root);
				if(gameover==true) {
					return;
				}
				 if(x==true) {
					 break;
				 }else {
					 System.out.println("Invalid move, try again");
					 continue;
				 }
				}
				ArrayList<String> turns = new ArrayList<String>();
				turns.add(turn);
				s.deepClone(a.expand(root, turns, "comp"), root.state);
				root.children=new ArrayList<Node>();
				s.printBoard(root.state);
			
	
				
			}
			
			public static void AI() {
				gameover=false;
				a.move(root);
				
				if(gameover==true) {
					return;
				}
				
				a.movelayer2(root);
				a.movelayer3(root);
				if (gameover==true){
					gameover=false;
				}
				a.movelayer4(root);
				s.deepClone(s.chooseBest(root).state,root.state);
				root.children=new ArrayList<Node>();
				s.printBoard(root.state);
				
			}
	
}
