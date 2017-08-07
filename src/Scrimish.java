
// David Sternheim
// 110245274
// CSE 114
// Scrimish

import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class Scrimish {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList[] userPile = initializeUserPile();
	private static ArrayList[] aiPile = initializeAIPile();
	public static boolean userTurn = true;
	public static int userIndex = 0;
	public static int aiIndex = 0;

	public static ArrayList<String> generateCards() {
		ArrayList<String> originalCards = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			originalCards.add("1");
			originalCards.add("2");
		}

		for (int i = 0; i < 3; i++) {
			originalCards.add("3");
			originalCards.add("4");
		}

		for (int i = 0; i < 2; i++) {
			originalCards.add("5");
			originalCards.add("6");
			originalCards.add("A");
			originalCards.add("S");
		}
		// originalCards.add("C");
		return originalCards;
	}

	public static ArrayList<String>[] initializeUserPile() throws IndexOutOfBoundsException {
		ArrayList[] userPile = new ArrayList[5];
		int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, countA = 0, countS = 0, countC = 0,
				illegalCount = 0;

		for (int i = 0; i < 5; i++)
			userPile[i] = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {
			System.out.println("Enter user pile number " + (i + 1) + ": ");
			for (int j = 0; j < 5; j++) {
				userPile[i].add(input.next());
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (userPile[i].get(j).equals("1"))
					count1++;
				else if (userPile[i].get(j).equals("2"))
					count2++;
				else if (userPile[i].get(j).equals("3"))
					count3++;
				else if (userPile[i].get(j).equals("4"))
					count4++;
				else if (userPile[i].get(j).equals("5"))
					count5++;
				else if (userPile[i].get(j).equals("6"))
					count6++;
				else if (userPile[i].get(j).equals("A"))
					countA++;
				else if (userPile[i].get(j).equals("S"))
					countS++;
				else if (userPile[i].get(j).equals("C"))
					countC++;
				else
					illegalCount++;
			}
		}

		if (count1 > 5) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (count2 > 5) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (count3 > 3) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (count4 > 3) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (count5 > 2) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (count6 > 2) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (countA > 2) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (countS > 2) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (countC > 1) {
			System.out.println("Incorrect card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		} else if (illegalCount > 0) {
			System.out.println("There is an invalid card entry. Please re-enter piles.");
			userPile = initializeUserPile();
		}

		return userPile;
	}

	public static ArrayList<String>[] initializeAIPile() {
		ArrayList[] aiPile = new ArrayList[5];
		ArrayList<String> originalCards = generateCards();
		for (int i = 0; i < 5; i++)
			aiPile[i] = new ArrayList<String>();

		int crownPile = (int) (Math.random() * 5);
		Collections.shuffle(originalCards);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0 && i == crownPile) {
					aiPile[i].add("C");
				} else {
					int randomElementNumber = (int) (Math.random() * originalCards.size());
					aiPile[i].add(originalCards.get(randomElementNumber));
					originalCards.remove(randomElementNumber);
				}
			}
		}

		return aiPile;
	}

	public static String userPileToString(ArrayList[] userPile) {
		String userPiles = "";
			for (int i = 0; i < 5; i++) {
				userPiles += " Pile " + (i + 1) + ": ";
				// for(int j=0; j<5;j++)
				userPiles += userPile[i] + " ";
				userPiles=userPiles.replace("[", "");
				userPiles=userPiles.replace("]", "");
				userPiles=userPiles.replaceAll(",","");
				userPiles += "\n";
		}
		return userPiles;
	}

	public static String aiPileToString(ArrayList[] aiPile) {
		String aiPiles = "";
		for (int i = 0; i < 5; i++) {
			aiPiles += " Pile " + (i + 1) + ": ";
			// for(int j=0; j<5;j++)
			aiPiles += aiPile[i] + " ";
			aiPiles=aiPiles.replace("[", "");
			aiPiles=aiPiles.replace("]", "");
			aiPiles=aiPiles.replaceAll(",", "");
			aiPiles += "\n";
		}
		return aiPiles;
	}

	public static String stateToString() {
		String s = "User Piles: \n";
		s += userPileToString(userPile);
		String s1 = "Computer Piles: \n";
		s1 += aiPileToString(aiPile);
		return s + s1 ;
	}

	public static void battle() {
		String userCard = (String) userPile[userIndex].get(userPile[userIndex].size() - 1);
		String aiCard = (String) aiPile[aiIndex].get(aiPile[aiIndex].size() - 1);
		String card1 = "";
		String card2 = "";
		ArrayList player1 = userPile[userIndex];
		ArrayList player2 = aiPile[aiIndex];

		if (userTurn == true) {
			card1 = userCard;
			card2 = aiCard;
			player1 = userPile[userIndex];
			player2 = aiPile[aiIndex];
		} else {
			card1 = aiCard;
			card2 = userCard;
			player1 = aiPile[aiIndex];
			player2 = userPile[userIndex];
		}

		int winner = 1;

		if (card1.equals("C") && card2.equals("C"))
			winner = 9;
		else if(card1.equals("C") && card2.equals("S"))
			winner = 5;
		else if (card2.equals("C") && !card1.equals("C"))
			winner = 8;
		else if (card1.equals("C") || card2.equals("C"))
			winner = 7;
		else {
			if ((card2.equals("A") && card1.equals("S")))
				winner = 0;
			else if (card2.equals("S"))
				winner = 10;
			else if (card1.equals("A") || card2.equals("A"))
				winner = 4;
			else {
				if (card1.equals("S") || card2.equals("S"))
					winner = 2;
				else {
					if (card1.compareTo(card2) > 0)
						winner = 3;
					else if (card1.compareTo(card2) == 0)
						winner = 2;
					else {
						winner = 1;
						card1.equals(null);
					}
				}
			}
		}
		
		if(winner==10){
			System.out.println("Shield card cannot be used to attack");
			userTurn=true;
		} else if (winner == 9) {
			if (userTurn == true) {
				System.out.println("Game Over. You Lose.");
				System.exit(0);
			} else {
				System.out.println("Game Over. You Win!");
				System.exit(0);
			}
		} else if (winner == 8) {
			if (userTurn != true) {
				System.out.println("Game Over. You Lose.");
				System.exit(0);
			} else {
				System.out.println("Game Over. You Win!");
				System.exit(0);
			}

		} else if (winner == 7) {
			if (userTurn != true) {
				System.out.println("Game Over. You Win!");
				System.exit(0);
			} else {
				System.out.println("Game Over. You Lose.");
				System.exit(0);
			}
		}

		else if (winner == 5) {
			player2.remove(card2);
		} else if (winner == 4) {
			player1.remove(card1);
		} else if (winner == 3) {
			player2.remove(card2);
		} else if (winner == 2) {
			player1.remove(card1);
			player2.remove(card2);
		} else if (winner == 1) {
			player1.remove(card1);
		} else {
		}
	}

	
	public static void doComputerTurn() {
		do{
			aiIndex = (int) (Math.random() * 5);
		} while(aiPile[aiIndex].isEmpty());
		do{
			userIndex = (int) (Math.random() * 5);
		} while(aiPile[aiIndex].isEmpty());
	}

	public static void removeCard() {
		userPile[userIndex - 1].remove(userPile[userIndex - 1].size() - 1);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(stateToString());
		String discardOption = "";
		try {
			for (int i = 0; i < 50; i++) {
				if (userTurn) {
					System.out.println("Would you like to discard a card or enter battle phase?");
					System.out.print(
							"If you would like to discard, please enter Y. Any other input will proceed to battle phase: ");
					discardOption = input.next();
					System.out.println();
				}
				if (discardOption.equals("Y")) {
					System.out.print("Select the user pile you wish to discard from: ");
					int pileSelection = input.nextInt();
					System.out.println();
					if (!(userPile[pileSelection - 1].get(userPile[pileSelection - 1].size() - 1).equals("C")))
						userPile[pileSelection - 1].remove(userPile[pileSelection - 1].size() - 1);
					else
						System.out.println("You canot discard your own Crown Card!");
					
					System.out.println(stateToString());
					
					do{
						aiIndex = (int) (Math.random() * 5);
					} while(aiPile[aiIndex].isEmpty());
					
					do{
						userIndex=(int)(Math.random()*5);
					} while(userPile[userIndex].isEmpty());
					System.out.println("---------------------\n" + "Computer turn: computer attacks user pile: "
							+ (userIndex + 1) + " with computer pile: " + (aiIndex + 1));
					discardOption = "";
				} else {
					if (userTurn == true) {
							do{	
							System.out.print(
									"----------------------" + "\n" + "User turn \nEnter user Pile index(1-5): ");
							userIndex = input.nextInt() - 1;
							} while(userPile[userIndex].isEmpty());
							do{
							System.out.print("Enter computer pile index(1-5): ");
							aiIndex = input.nextInt() - 1;
							} while(aiPile[aiIndex].isEmpty());
							userTurn = false;
					} else {
						do{
						aiIndex = (int) (Math.random() * 5);
						} while(aiPile[aiIndex].isEmpty());
						do{	
						userIndex = (int) (Math.random() * 5);
						} while(userPile[userIndex].isEmpty());
						
						System.out.println("---------------------\n" + "Computer turn: computer attacks user pile: "
								+ (userIndex + 1) + " with computer pile: " + (aiIndex + 1));
						userTurn = true;
					}
				}
				battle();
				System.out.println(stateToString());
			}
		} catch (Exception e) {
			System.out.println("Invalid Pile Selection.");
		}
	}

}
