import java.util.*;

public class Main {

		/*The Below two ArrayList contains the list of Positions Played by the Player and CPU respectively.
		* Making it static and declaring it global  makes sure that all methods can have access to it.
		 */
	    static ArrayList<Integer> playerPositions=new ArrayList<>();
	    static ArrayList<Integer> cpuPositions=new ArrayList<>();

		public static void main(String[] args) {
			System.out.println("Welcome to Tic Tac Toe!!");

			char[][] gameBoard={{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '}
							};
			printGameBoard(gameBoard);

			Scanner scan=new Scanner(System.in);
			while (true) {
				//Player's Snippet
				System.out.println("Enter the Position b/w(1-9)");
				int playerPosition=scan.nextInt();
				while (playerPositions.contains(playerPosition)|| cpuPositions.contains(playerPosition)){
					System.out.println("Position Taken!!, Enter a different Position");
					playerPosition=scan.nextInt();
				}
				placePiece(gameBoard,playerPosition,"Player");
				printGameBoard(gameBoard);

				String result=checkWinner();
				if (result.length()>0){
					System.out.println(result);
					break;
				}

				//CPU's Snippet!
				Random rand=new Random();
				int cpuPosition= rand.nextInt(9)+1;
				while (playerPositions.contains(cpuPosition)|| cpuPositions.contains(cpuPosition)){
					cpuPosition= rand.nextInt(9)+1;
				}
				placePiece(gameBoard,cpuPosition,"CPU");
				printGameBoard(gameBoard);

				result=checkWinner();
				if (result.length()>0){
					System.out.println(result);
					break;
				}
			}
		}

		public static String checkWinner(){

			/*
			* Contains the list of all Winning Outcomes.
			* And checks if the Arraylist (Players/CPU) contains any of these.
			* And returns the Winner!
			* */
			List<Integer> topRow= Arrays.asList(1,2,3);
			List<Integer> midRow= Arrays.asList(4,5,6);
			List<Integer> botRow= Arrays.asList(7,8,9);
			List<Integer> leftCol= Arrays.asList(1,4,7);
			List<Integer> midCol= Arrays.asList(2,5,8);
			List<Integer> rightCol= Arrays.asList(3,6,9);
			List<Integer> cross1= Arrays.asList(1,5,9);
			List<Integer> cross2= Arrays.asList(3,5,7);

			//PreProcessing reduces our Time Complexity issues.
			List<List> winning=new ArrayList<List>();
			winning.add(topRow);
			winning.add(botRow);
			winning.add(midRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(cross1);
			winning.add(cross2);

			for (List l:winning){
				if (playerPositions.containsAll(l)){
					return "Congratulations you Won!!";
				}else if(cpuPositions.containsAll(l)){
					return "CPU Wins!!  :( !";
				}else if(playerPositions.size()+cpuPositions.size()==9){
					return "Hey,it's 'TIE' ";
				}
			}
			return "";
		}
		public static void placePiece(char[][] gameBoard,int position,String user){
			//Places the Symbol('X'/'O') in the desired Position.
			char symbol='X';
			if (user.equals("Player")){
				symbol='X';
				playerPositions.add(position);
			}else if (user.equals("CPU")){
				symbol='O';
				cpuPositions.add(position);
			}

			switch (position){
				case 1:gameBoard[0][0]=symbol;
					break;
				case 2:gameBoard[0][2]=symbol;
					break;
				case 3:gameBoard[0][4]=symbol;
					break;
				case 4:gameBoard[2][0]=symbol;
					break;
				case 5:gameBoard[2][2]=symbol;
					break;
				case 6:gameBoard[2][4]=symbol;
					break;
				case 7:gameBoard[4][0]=symbol;
					break;
				case 8:gameBoard[4][2]=symbol;
					break;
				case 9:gameBoard[4][4]=symbol;
					break;
				default:
					break;
			}
		}
		public static void printGameBoard(char [][] gameBoard){
			//This Method print's out the GameBoard.
			for(char[] row:gameBoard){
				for(char c:row){
				System.out.print(c+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

