import java.util.Scanner;
public class Main{
	
	//Class level boolean to be used by methods
	public static boolean isVertical = true;
	public static void main(String[] args) {
		
		//2d array of char to make a 50 by 50 board for the puzzle
		char[][] puzzle = new char[50][50];
		Scanner sc = new Scanner(System.in);
		
		//Fill board with spaces
		String[] words = new String[5];
		for(int i = 0; i<puzzle.length; i++) {
			for(int j = 0; j<puzzle[i].length; j++) {
				puzzle[i][j] = ' ';
			}
		}
		
		//User input 
		for(int i = 0; i< 5; i++) {
			System.out.print("Enter word #" + (i+1) + " : ");
			String a = sc.nextLine();
			words[i] = a;

		}
		
		/*ignore these system.out.println, they are here to make the output look nicer
		 * 
		 */
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		sortDescending(words);
		String longest = words[0];
		int column = 20;
		
		//place longest word in the middle of the board
		for(int i = 0;i<longest.length(); i++) {
			puzzle[25][column] = longest.charAt(i);
			column++;
		}
			
		for(int i = 1; i<words.length; i++){
				place(puzzle,words[i]);
		}
		
		
		printArray(puzzle);

	}
	
	
	
	//Find the longest word the user inputed
	public static String longest(String[] a) {
		String longest = "";
		for(String x: a) {
			if(x.length() > longest.length()) {
				longest = x;
			}
		}
		return longest;
	}
	
	
	
	//Method that places the words correctly onto the board
	public static void place(char[][] puzzle, String b) {
		int count = 0;
	outer:	 for(int h = 0; h<b.length(); h++) {
		inner:	for(int i = 0; i<puzzle.length; i++) {
				for(int j = 0; j<puzzle[0].length; j++) {
					if(b.charAt(h) == puzzle[i][j]) {
						//Here we check if the word can be placed vertically, and check if it can be placed horizontally otherwise
						if(isVertical) {
							if(verticalCheck(puzzle, i, j, b, h)) {
								int charat = 0;
								for(int x = i-h; x<(i-h)+b.length(); x++) {
									puzzle[x][j] = b.charAt(charat);
									charat++;
								}
								isVertical = false;
								count++;
								break outer;
							}
							else if(horizontalCheck(puzzle, i, j, b, h)) {
								int charat = 0;
								for(int x = j-h; x<(j-h) + b.length(); x++) {
									puzzle[i][x] = b.charAt(charat);
									charat++;
								}
								isVertical = !isVertical;
								count++;
								break outer;
							}
							//break inner loop if neither horizontally or vertically works, so that we can check for a different letter 
							else {
								break inner;
							}
						}
						if(!isVertical) {
							if(horizontalCheck(puzzle, i, j, b, h)) {
								int charat = 0;
								for(int x = j-h; x<(j-h) + b.length(); x++) {
									puzzle[i][x] = b.charAt(charat);
									charat++;
								}
								isVertical = true;
								count++;
								break outer;
							}
							else if(verticalCheck(puzzle, i, j, b, h)) {
								int charat = 0;
								for(int x = i-h; x<(i-h)+b.length(); x++) {
									puzzle[x][j] = b.charAt(charat);
									charat++;
								}
								isVertical = !isVertical;
								count++;
								break outer;
							}
							else {
								break inner;
							}
						}
					}
				}
			}
		}
	if(count == 0) {
		System.out.println("\"" + b + "\" couldn't be placed");
	}
}
	//Checks if a word can be placed vertically
	public static boolean verticalCheck(char[][] puzzle, int i, int j, String b, int z) {
		int count = 0;
		for(int h = i-z-1; h<(i-z) + b.length() + 1; h++) {
			if(puzzle[h][j-1] != ' ' && h != i) {
				count++;
			}
			if(puzzle[h][j+1] != ' ' && h != i) {
				count++;
			}
			if(puzzle[h][j] != ' ' && h!= i) {
				count++;
			}
		}
		if(count > 0) {
			return false;
		}
		return true;
	}
	
	//Checks if a word can be placed horizontally
	public static boolean horizontalCheck(char[][] puzzle, int i, int j, String b, int z) {
		int count = 0;
		for(int h = j-z-1; h<(j-z) + b.length() + 1; h++) {
			if(puzzle[i-1][h] != ' ' && h != j) {
				count++;
			}
			if(puzzle[i+1][h] != ' ' && h != j) {
				count++;
			}
			if(puzzle[i][h] != ' ' && h != j) {
				count++;
			}
		}
		if(count > 0) {
			return false;
		}
		return true;
	}
	
	//Sorts the user input of Strings by length descending
	public static void sortDescending(String[] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = i+1; j<arr.length; j++) {
				if(arr[i].length() < arr[j].length()) {
					String temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	//prints the 2d array board onto the console
	public static void printArray(char[][] a) {
		for(int i = 0; i<a.length; i++) {
			for(int j = 0; j<a[i].length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}
