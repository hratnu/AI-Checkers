import java.util.ArrayList;
import java.util.Iterator;

public class Action extends state {

	String turn = " ";
	char[][] rboard;


	/*public void make_smart(Node node, int cut_off) {
		gameplay.whoIsplaying = "computer";
		
		if(cut_off==0) {
			return;
		}
		
		Iterator<Node> it = node.children.iterator();
		while(it.hasNext()) {
			Node child=it.next();
			make_smart(child, cut_off--);
			if(cut_off%2==0) {
				movew(child);
			}
		};


	}*/


	
	public ArrayList<String> movew(Node node) {
		turn = "human";
		int cap = gameplay.size;
		// will return list of all possible moves
		// so need a list here now!( this would be the list of all possible next states)
		ArrayList<String> movesw = new ArrayList<String>();

		for (int i = 0; i < cap; i++) {
			for (int j = 0; j < cap; j++) {
				if (node.state[i][j] == 'w' || node.state[i][j] == 'W') {
					// System.out.println("piece at- " + i+ " * "+ j);
					int h = i - 1;
					for (int k = 0; k < cap - 1; k++) {
						if (h > 0 && h <= cap - 1 && k > 0 && k < cap - 1) {
							if ((node.state[h][k] == 'b' || node.state[h][k] == 'B') && Math.abs(k - j) == 1) {// captures

								if (node.state[h - 1][k + (k - j)] == '-') {
//									System.out.println("in this");
									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h - 1);
									String s4 = Integer.toString(k + 1 + k - j);
									String s = s1 + s2 + "x" + s3 + s4;
									
									h--;
									if(j>k) {k--;} else {k++; }
									
									int m = h - 1;
									for (int n = 0; n < cap; n++) {
										if (m > 0 && n > 0 && n < cap - 1) {
											if ((node.state[m][n] == 'b' || node.state[m][n] == 'B') && Math.abs(n - k) == 1) {// captures
												if (node.state[m - 1][n + (n - k)] == '-') {
													char s5= rtrans(m-1);
													String s6=Integer.toString(n+1+n-k);
													
													s = s+"x"+s5 + s6;
													movesw.add(s);
													
												}
											}
										}
									}
									
									
									
									movesw.add(s);
								}
							}
						}
					}

				}

				if (node.state[i][j] == 'W') {// enlist all back captures by the king
					int h = i + 1;
					for (int k = 0; k < cap; k++) {
						if (h < cap - 1 && k > 0 && k < cap - 1) {
							if ((node.state[h][k] == 'b' || node.state[h][k] == 'B') && Math.abs(k - j) == 1) {// captures

								if (node.state[h + 1][k + (k - j)] == '-') {
//									System.out.println("in this");
									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h + 1);
									String s4 = Integer.toString(k + 1 + k - j);
									String s = s1 + s2 + "x" + s3 + s4;
									
									h++;
									if(j>k) {k--;} else {k++; }
									
									int m = h + 1;
									for (int n = 0; n < cap; n++) {
										if (m < cap - 1 && n > 0 && n < cap - 1) {
											if ((node.state[m][n] == 'b' || node.state[m][n] == 'B') && Math.abs(n - k) == 1) {// captures
												if (node.state[m + 1][n + (n - k)] == '-') {
													char s5= rtrans(m+1);
													String s6=Integer.toString(n+1+n-k);
													
													s = s +"x"+ s5 + s6;
													//System.out.println("lalalallallalalala");
													movesw.add(s);
													
												}
											}
										}
									}
									
									
									movesw.add(s);
								}
							}
						}
					}

				}

			}
		}

		if (movesw.isEmpty()) { // if capture count is zero then
			for (int i = 0; i < cap; i++) {
				for (int j = 0; j < cap; j++) {
					if (node.state[i][j] == 'w' || node.state[i][j] == 'W') {
						// System.out.println("piece at- " + i+ " * "+ j);
						int h = i - 1;

						for (int k = 0; k < cap; k++) {
							if (h >= 0 && h <= cap - 1 && k >= 0 && k <= cap - 1) {
								if (node.state[h][k] == '-' && Math.abs(k - j) == 1) {// moves

									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h);
									String s4 = Integer.toString(k + 1);

									// Concatenate both strings
									String s = s1 + s2 + "-" + s3 + s4;
									
									
									movesw.add(s);

								}

							}
						}
					}

					if (node.state[i][j] == 'W') {
						// System.out.println("piece at- " + i+ " * "+ j);
						int h = i + 1;

						for (int k = 0; k < cap; k++) {
							if (h >= 0 && h <= cap - 1 && k >= 0 && k <= cap - 1) {
								if (node.state[h][k] == '-' && Math.abs(k - j) == 1) {// moves

									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h);
									String s4 = Integer.toString(k + 1);

									// Concatenate both strings
									String s = s1 + s2 + "-" + s3 + s4;
									movesw.add(s);

								}

							}
						}
					}

				}

			}
			// decide a move for black
		}

		//System.out.println(movesw.toString());

		if (gameplay.whoIsplaying.equals("computer")) {
			expand(node, movesw, turn);
		}
		return movesw;

	}

	public ArrayList<String> move(Node node) { // will return list of all possible moves
		// so need a list here now!( this would be the list of all possible next states)
		ArrayList<String> moves = new ArrayList<String>();
		turn = "comp";
		int cap = gameplay.size;
		for (int i = 0; i < cap; i++) {// capture by normal black
			for (int j = 0; j < cap; j++) {
				
				
				
				
				
				
				
				if (node.state[i][j] == 'b' || node.state[i][j] == 'B') {
					// System.out.println("piece at- " + i+ " * "+ j);
					int h = i + 1;
					for (int k = 0; k < cap; k++) {
						if (h < cap - 1 && k > 0 && k < cap - 1) {
							if ((node.state[h][k] == 'w' || node.state[h][k] == 'W') && Math.abs(k - j) == 1) {// captures
								if (node.state[h + 1][k + (k - j)] == '-') {
									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h + 1);
									String s4 = Integer.toString(k + 1 + k - j);
									String s = s1 + s2 + "x" + s3 + s4;
												
												h++;
												if(j>k) {k--;} else {k++; }
												
												int m = h + 1;
												for (int n = 0; n < cap; n++) {
													if (m < cap - 1 && n > 0 && n < cap - 1) {
														if ((node.state[m][n] == 'w' || node.state[m][n] == 'W') && Math.abs(n - k) == 1) {// captures
															if (node.state[m + 1][n + (n - k)] == '-') {
																char s5= rtrans(m+1);
																String s6=Integer.toString(n+1+n-k);
																
																s = s +"x"+ s5 + s6;
																//System.out.println("lalalallallalalala");
																moves.add(s);
																
															}
														}
													}
												}
							
											
									// System.out.println("in this");
									
									moves.add(s);
									}
								}
							}
						}
					}

				

				if (node.state[i][j] == 'B') {// enlist all captures by the king
					int h = i - 1;
					for (int k = 0; k < cap; k++) {
						if (h > 0 && k > 0 && k < cap - 1) {
							if ((node.state[h][k] == 'w' || node.state[h][k] == 'W') && Math.abs(k - j) == 1) {// captures
								if (node.state[h - 1][k + (k - j)] == '-') {
									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h - 1);
									String s4 = Integer.toString(k + 1 + k - j);
									String s = s1 + s2 + "x" + s3 + s4;
									
											h--;
											if(j>k) {k--;} else {k++; }
											
											int m = h - 1;
											for (int n = 0; n < cap; n++) {
												if (m > 0 && n > 0 && n < cap - 1) {
													if ((node.state[m][n] == 'w' || node.state[m][n] == 'W') && Math.abs(n - k) == 1) {// captures
														if (node.state[m - 1][n + (n - k)] == '-') {
															char s5= rtrans(m-1);
															String s6=Integer.toString(n+1+n-k);
															
															s = s+"x"+s5 + s6;
															moves.add(s);
															
														}
													}
												}
											}
									
									
									// System.out.println("in that");
								
									moves.add(s);
								}
							}
						}
					}

				}

			}
		}

		if (moves.isEmpty()) { // if capture count is zero then normal moves by black

			for (int i = 0; i < cap; i++) {
				for (int j = 0; j < cap; j++) {
					if (node.state[i][j] == 'b' || node.state[i][j] == 'B') {
						// System.out.println("piece at- " + i+ " * "+ j);
						int h = i + 1;
						for (int k = 0; k < cap; k++) {
							if (h >= 0 && h <= cap - 1 && k >= 0 && k <= cap - 1) {
								if (node.state[h][k] == '-' && Math.abs(k - j) == 1) {// moves

									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h);
									String s4 = Integer.toString(k + 1);

									// Concatenate both strings
									String s = s1 + s2 + "-" + s3 + s4;
									moves.add(s);

								}

							}
						}
					}

					if (node.state[i][j] == 'B') {
						// System.out.println("piece at- " + i+ " * "+ j);
						int h = i - 1;
						for (int k = 0; k < cap; k++) {
							if (h >= 0 && h <= cap - 1 && k >= 0 && k <= cap - 1) {
								if (node.state[h][k] == '-' && Math.abs(k - j) == 1) {// moves

									char s1 = rtrans(i);
									String s2 = Integer.toString(j + 1);
									char s3 = rtrans(h);
									String s4 = Integer.toString(k + 1);

									// Concatenate both strings
									String s = s1 + s2 + "-" + s3 + s4;
									moves.add(s);

								}

							}
						}
					}

				}

			}
		}
		// decide a move for black

		// player's moveo
		//System.out.println(moves.toString());
		if(moves.size()==0) {
			gameplay.gameover=true;
		}
			expand(node, moves, turn);

		return moves;

	}

	public char[][] expand(Node node, ArrayList<String> moves, String turn) {
		int cap = gameplay.size;
		while (!(moves.isEmpty())) {
			String option = moves.remove(0);
			char content = ' ';

			if (option.charAt(2) == '-') {
				String[] prenew = option.split("-");
				rboard = new char[cap][cap];
				deepClone(node.state, rboard);
				// printBoard(node.state);

				int r1 = charToint(prenew[0].charAt(0)); // keeping track of the original element being moved
				int c1 = charToint(prenew[0].charAt(1));
				int r2 = charToint(prenew[1].charAt(0)); // keeping track if crowning needed
				int c2 = charToint(prenew[1].charAt(1));

				// System.out.println("print : "+r1+ " "+ c1+ " "+ r2 + " "+ c2+"\n");

				if (rboard[r1][c1] == 'b') {
					content = 'b';
				} else if (rboard[r1][c1] == 'B') {
					content = 'B';
				} else if (rboard[r1][c1] == 'w') {
					content = 'w';
				} else if (rboard[r1][c1] == 'W') {
					content = 'W';
				}

				if (r2 == cap - 1 && content == 'b') {
					content = 'B';
				}

				if (r2 == 0 && content == 'w') {
					content = 'W';
				}

				fillSpace(rboard, prenew[1], content);
				fillSpace(rboard, prenew[0], '-');
				Node child = new Node(turn, rboard);
				// child.utility = utility(child);

				node.children.add(child);

			}
			if (option.charAt(2) == 'x') {
				String[] prenew = option.split("x");
				//System.out.println(" \n \n the length of prenew is: "+ prenew.length+ "\n\n");
				int  r4=9, r5=9,c4=9, c5 =9;
				
				
				int r1 = charToint(prenew[0].charAt(0));
				int c1 = charToint(prenew[0].charAt(1));
				int r2 = charToint(prenew[1].charAt(0));
				int c2 = charToint(prenew[1].charAt(1));
				int r3 = (r1 + r2) / 2;
				int c3 = (c1 + c2) / 2;
				
				if(prenew.length>2) { //multiple captures
					 r4 = charToint(prenew[2].charAt(0));
					 c4 = charToint(prenew[2].charAt(1));
					 r5= (r2+r4)/2;
					 c5= (c2+c4)/2;
				}
				

				rboard = new char[cap][cap];
				deepClone(node.state, rboard);

				if (rboard[r1][c1] == 'b') {
					content = 'b';
				} else if (rboard[r1][c1] == 'B') {
					content = 'B';
				} else if (rboard[r1][c1] == 'w') {
					content = 'w';
				} else if (rboard[r1][c1] == 'W') {
					content = 'W';
				}

				
				if(prenew.length==2){
				if (r2 == gameplay.size-1 && content == 'b') { //check if we need to crown or not
					content = 'B';
				}

				if (r2 == 0 && content == 'w') {
					content = 'W';
				}
				}
				
				if(prenew.length==3){
					if (r4 == gameplay.size-1 && content == 'b') { //check if we need to crown or not
						content = 'B';
					}

					if (r4 == 0 && content == 'w') {
						content = 'W';
					}
				}
				
				fillCell(rboard, r1, c1, '-');
				fillCell(rboard, r3, c3, '-');
				if(prenew.length==2) {
				fillCell(rboard, r2, c2, content);
				}
				else if(prenew.length==3) {
					fillCell(rboard, r4, c4, content);
					fillCell(rboard, r5, c5, '-');
					fillCell(rboard, r2, c2, '-');	
				}
				Node child = new Node(turn, rboard);
				// ≈child.utility = utility(child);
				node.children.add(child);
			}
		}

		return rboard;
	}

	
	public void movelayer2(Node node) {
		gameplay.whoIsplaying = "computer";


		Iterator<Node> it = node.children.iterator();
		while (it.hasNext()) {
			
			Node child = it.next();
			movew(child);

		}
	}

	public void movelayer3(Node node) {

		Iterator<Node> it = node.children.iterator();
		while (it.hasNext()) {
			Node child = it.next();
			Iterator<Node> it2 = child.children.iterator();
			while (it2.hasNext()) {
			
				Node grandchild = it2.next();
				move(grandchild);
			}
		}
	}
	
	public void movelayer4(Node node) {

		Iterator<Node> it = node.children.iterator();
		while (it.hasNext()) {
			Node child = it.next();
			Iterator<Node> it2 = child.children.iterator();
			while (it2.hasNext()) {
				Node grandchild = it2.next();
				Iterator<Node> it3 = grandchild.children.iterator();
				while (it2.hasNext()) {
					Node grandgrandchild = it2.next();
					move(grandgrandchild);
					movew(grandgrandchild);
				}
				
			}
		}
	}
	
	public Boolean validate_turn(String turn, Node node) {

		ArrayList<String> possible_turns = movew(node);
		
		System.out.println("Possible moves: " +possible_turns.toString());
		if(possible_turns.size()==0) {
			System.out.println("Game over!");
			gameplay.gameover=true;
			return false;
		}
		
		if (possible_turns.contains(turn)) {
			System.out.println("it is a valid move");

		} else {
			return false;
		}
		return true;
	}
	
	/*public Boolean any_turn(Node node) {
		ArrayList<String> possible_turns = move(node);
		System.out.println("Possible moves for computer: " +possible_turns.toString());
		if(possible_turns.size()==0) {
			System.out.println("Game over!");
			gameplay.gameover=true;
			return false;		
		}
		return false;
	}	*/
}
	/*public Boolean another_turn(Node node) {
		ArrayList<String> possible_turns = movew(node);
		char[] everychar= possible_turns.toString().toCharArray();
		for(int i=0; i<everychar.length; i++) {
			if (everychar[i]=='x') {
				return true;
			}
		
		}
		return false;
	}
		/*
		 * char content; if (turn.charAt(2) == '-') { // test 1 String[] prenew =
		 * turn.split("-"); int r1 = charToint(prenew[0].charAt(0)); // keeping track of
		 * the original element being moved int c1 = charToint(prenew[0].charAt(1)); int
		 * r2 = charToint(prenew[1].charAt(0)); // keeping track if crowning needed int
		 * c2 = charToint(prenew[1].charAt(1));
		 * 
		 * if (r1 >= 0 && r1 < cap && r2 >= 0 && r2 < cap && c1 >= 0 && c1 < cap && c2
		 * >= 0 && c2 < cap) {
		 * 
		 * } else { return false; }
		 * 
		 * /* if (node.state[r1][c1] == 'b') { content = 'b'; } else if
		 * (node.state[r1][c1] == 'B') { content = 'B';
		 * 
		 * } else
		 * 
		 * if (node.state[r1][c1] == 'w') { // test 2 content = 'w'; } else if
		 * (node.state[r1][c1] == 'W') { content = 'W'; } else { return false; }
		 * 
		 * // then
		 * 
		 * if (node.state[r2][c2] == '-') { // test 3 } else { return false; }
		 * 
		 * // then if (Math.abs(r1 - r2) == 1 && Math.abs(c2 - c1) == 1) {// test 4
		 * 
		 * } else { return false;
		 * 
		 * }
		 * 
		 * } else if (turn.charAt(2) == 'x') {
		 * 
		 * String[] prenew = turn.split("x");
		 * 
		 * int r1 = charToint(prenew[0].charAt(0)); int c1 =
		 * charToint(prenew[0].charAt(1)); int r2 = charToint(prenew[1].charAt(0)); int
		 * c2 = charToint(prenew[1].charAt(1)); int r3 = (r1 + r2) / 2; int c3 = (c1 +
		 * c2) / 2; if (r1 >= 0 && r1 < cap && r2 >= 0 && r2 < cap && c1 >= 0 && c1 <
		 * cap && c2 >= 0 && c2 < cap) {
		 * 
		 * } else { return false; }
		 * 
		 * if (node.state[r1][c1] == 'w') { // test 2 content = 'w'; } else if
		 * (node.state[r1][c1] == 'W') { content = 'W'; } else { return false; }
		 * 
		 * if (node.state[r3][c3] == 'b' || node.state[r3][c3] == 'B') { // test 2 }
		 * else { return false; }
		 * 
		 * if (node.state[r2][c2] == '-') { // test 3 } else { return false; }
		 * 
		 * if (Math.abs(r1 - r2) == 2 && Math.abs(c2 - c1) == 2 && Math.abs(r1 - r3) ==
		 * 1 && Math.abs(c1 - c3) == 1) {// test 4 } else { return false; }
		 * 
		 * 
		 * 
		 * } else { return false; }
		 * 
		 * return true; }
		 */

	
