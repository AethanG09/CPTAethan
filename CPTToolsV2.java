import arc.*;
import java.awt.image.*;
import java.awt.Font;
import java.awt.Color;

public class CPTToolsV2{

	public static int menu(Console con){
		int intNum;
		//Print Logo
		BufferedImage image = con.loadImage("logo.jpg");
		con.drawImage(image, 100, 100);
		con.repaint();
		//Print Questions
		Font font = con.loadFont("font.ttf", 60);
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(font);
		con.drawString("Enter 1 To Play", 600, 200); 
		con.drawString("Enter 2 To View Leadboard", 600, 270); 
		con.drawString("Enter 3 To Add Theme", 600, 340); 
		con.drawString("Enter 4 To Quit", 600, 410); 
		con.repaint();
		intNum = con.readInt();
		return intNum;
	}
	
	//~ // View Leader Board
	//~ public static void viewLeaderBoard(Console con){
		
		//~ // (aethan) open leaderboard.txt file
		//~ TextInputFile leaderboardfile = new TextInputFile("leaderboard.txt");
		
		//~ // (aethan) loop loading data from leaderboard file into 2D array
		//~ String strLB;
		//~ while(leaderboard.eof() == false){
			//~ strLB = leaderboard.readLine();
			//~ con.println(strLB);
		//~ }
				
		//~ // (aethan) bubble sort from highest to lowest (show top score)
        //~ scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));
		
		//~ // (aethan) loop displaying leaderboard
        //~ System.out.println("\n--- Leaderboard ---");
        //~ for (String[] entry : scores) {
        //~ System.out.println(entry[0] + " - " + entry[1]);
        //~ }	
		
	//~ }
	


	public static int pregame(Console con, String strPlayerName){

		
		String strTheme;
		
		con.println("entering game...");
		con.println("what is your name?");
		strPlayerName = con.readLine();
		con.println(strPlayerName);
		// (aethan) display available themes from master theme file
		String strThemes;
		con.println("Available Themes: ");
		TextInputFile masterthemefile = new TextInputFile("themes.txt");
		while(masterthemefile.eof()==false){
			strThemes = masterthemefile.readLine();
			con.println(strThemes);
		}
		masterthemefile.close();
		
		
		// (aethan) ask user to select the theme they want to play
		con.println("which of these themes do you want to play?");
		strTheme = con.readLine();
		
		
		// (aethan) check if the theme selected is a valid one  IDK
		//if (!themeFile.exists()) {
		//	con.println("Theme not found");
        //    return;
        //}
				
		// Find # of words in theme file
		TextInputFile themesFile = new TextInputFile(strTheme + ".txt");
		String strWrdTst;
		int wordCount = 0;
		while(themesFile.eof() == false){
			strWrdTst = themesFile.readLine();
			wordCount = wordCount + 1;
		}
		themesFile.close();

		
		// Make Array
		String strWords[][];
		strWords = new String[wordCount][2];
		
		TextInputFile themesFile2 = new TextInputFile(strTheme + ".txt");
		
		int intRand;
		int intCount = 0;
		while(themesFile2.eof() == false){
			strWords[intCount][0] = themesFile2.readLine();
			
			intRand = (int)(Math.random()*100+1);
			strWords[intCount][1] = intRand + "";
			intCount = intCount + 1;
		}
		themesFile2.close();

		
		//Sort
		String strThemeTemp;
		String strNumTemp;
		int intCount2;
		int intCount3;
		for(intCount3 = 0; intCount3 < wordCount; intCount3++){
			for(intCount2 = 0; intCount2 < wordCount-1; intCount2++){
				if(Integer.parseInt(strWords[intCount2][1]) < Integer.parseInt(strWords[intCount2+1][1])){
					//swap words
					strThemeTemp = strWords[intCount2][0];
					strWords[intCount2][0] = strWords[intCount2+1][0];
					strWords[intCount2+1][0] = strThemeTemp;
										
					//swap integers
					strNumTemp = strWords[intCount2][1];
					strWords[intCount2][1] = strWords[intCount2+1][1];
					strWords[intCount2+1][1] = strNumTemp;
					
				}

			}
		}
		//Output the randomized array to temporary file
		String strFinWrd;
		int intCount4;
		strFinWrd = strWords[wordCount-1][0];
		TextOutputFile file = new TextOutputFile("WordTemp.txt");
			for(intCount4 = 0; intCount4 < wordCount; intCount4++){
				file.println(strWords[intCount4][0]);
			}
		// Close file
		file.close();
		return wordCount;
	}
	
	
	//Main Method to Play Game
	public static void gameplay(Console con, int intWordCount, String strPlayerName){

		int intRead = 0;
		TextInputFile file = new TextInputFile("WordTemp.txt");

		int intWinCount = 0;
		boolean blnGame = true;
		int intCount0;
		//~ for(intCount0 = 0; intCount0 < intWordCount; intCount0++){
		while(blnGame == true && file.eof()== false){
			String strWord;
			if(file.eof()==true){
				con.println("no more words");
				break;
			}

			strWord = file.readLine();
			
			if(blnGame == true){
				con.clear();
				con.setDrawColor(Color.BLACK);
				con.fillRect(0,0,1280,720);
				con.repaint();
				con.setDrawColor(Color.WHITE);
				int intLength;	
				int intCount;
				BufferedImage image = con.loadImage("hangman.jpg");
				con.drawImage(image, -50, 10);
				con.repaint();
				
				int intPlace;
				intPlace = 400;
				intLength = strWord.length();
				for(intCount = 0; intCount < intLength; intCount++){		
					con.drawLine(intPlace,400, intPlace + 50,400);
					intPlace = intPlace + 60;
				}
				
				
				String strLetters[][];
				strLetters = new String[intLength][3];
					
				//Fill Array + Random Number
				int intCount2;
				int intSub = 0;
				int intRand;
				int intSubString = 0;
				for(intCount2 = 0; intCount2 < intLength; intCount2++){
					strLetters[intCount2][0] = strWord.substring(intSub,intSub+1);
					intSub = intSub + 1;
					//~ con.println(strLetters[intCount2][0]);
					intRand = (int)(Math.random()*100+1);
					strLetters[intCount2][1] = intRand + "";
					//~ con.println(strLetters[intCount2][1]);
					strLetters[intCount2][2] = intSubString + "";	
					intSubString = intSubString + 1;
				}

				//Bubble Sort
				int intCount3;
				int intCount4;
				String strTemp;
				for(intCount3 = 0; intCount3 < intLength; intCount3++){
					for(intCount4 = 0; intCount4 < intLength - 1; intCount4++){	
						if(Integer.parseInt(strLetters[intCount4][1]) <  Integer.parseInt(strLetters[intCount4+1][1])){
							strTemp = strLetters[intCount4][0];
							strLetters[intCount4][0] = strLetters[intCount4+1][0];
							strLetters[intCount4+1][0] = strTemp;
							//~ con.println(strLetters[intCount4][0]);
							//~ con.println(strLetters[intCount4+1][0]);
						
							strTemp = strLetters[intCount4][1];
							strLetters[intCount4][1] = strLetters[intCount4+1][1];
							strLetters[intCount4+1][1] = strTemp;
							//~ con.println(strLetters[intCount4][1]);
							//~ con.println(strLetters[intCount4+1][1]);
								
							strTemp = strLetters[intCount4][2];
							strLetters[intCount4][2] = strLetters[intCount4+1][2];
							strLetters[intCount4+1][2] = strTemp;
						}
					}
				}
				boolean blnWin;
				int intCount5 = 0;
				int intPlace1;
				while(blnGame == true){
					//~ for(intCount5 = intNum1; intCount5 < intLength; intCount5++){
						String strGuess;
						con.println("guess a word");
						strGuess = con.readLine();
						
						if(strGuess.equalsIgnoreCase(strWord)){
							con.println("you won!");
							blnGame = false;
							blnWin = true;
							intWinCount = intWinCount + 1;
						}else{
							intPlace1 = Integer.parseInt(strLetters[intCount5][2]); // 1
							//Print out random letter
							Font font = con.loadFont("font.ttf", 120);
							con.setDrawFont(font);
							con.drawString(strLetters[intCount5][0], 400 + (intPlace1 * 60), 250); // p
							con.repaint();
							strLetters[intCount5][2] = "";
								
							//Hangman Figure
							if(intCount5 == 0){
								con.fillOval(180,200,150,150);
							}else if(intCount5 == 1){
								con.drawLine(255,360,340,400);
							}else if(intCount5 == 2){
								con.drawLine(255,360,170,400);
							}else if(intCount5 == 3){
								con.drawLine(255,350,255,500);
							}else if(intCount5 == 4){
								con.drawLine(255,500,155,600);
							}else if(intCount5 == 5){
								con.drawLine(255,500,355,600);
							}
							if(intCount5 == 5){
								con.println("you lost");
								blnGame = false;
								blnWin = false;		
							}else{
								con.println("guess again");	
								intCount5 = intCount5 + 1;	
							}
						}		
				con.sleep(200);
				con.clear();
				}

			}
			String strAgain;
			con.println("would you like to play again? (y/n)");
			strAgain = con.readLine();
			if(strAgain.equalsIgnoreCase("y")){
				blnGame = true;
				con.println(blnGame + "");
			}else{
				blnGame = false;
			}
		}

	// (aethan) save player's score by writing (playerName, wins) to leaderboard.txt
       //~ PrintWriter pw = new PrintWriter(new FileWriter(LEADERBOARD_FILE, true));
       //~ pw.println(name + "," + score);
	TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
		leaderboard.println(strPlayerName);
		leaderboard.println(intWinCount);
		leaderboard.close();
	}
	
	
	
	
	public static void addTheme(Console con) {
		String strNewTheme;
		String strWord;
		int intWordLength;
	
		
		//ask users for new theme's file name
		con.println("Enter new theme file name (e.g. Animals):");
		strNewTheme = con.readLine();
		
		//open new file with that name and loop
		TextOutputFile newtheme = new TextOutputFile(strNewTheme + ".txt");
		while(true){
			con.println("Enter a word for theme, or type 'stop' if done");
			strWord = con.readLine();
			intWordLength = strWord.length();
			if(strWord.equalsIgnoreCase("stop")){
				break;
			}else if(intWordLength < 7){
				con.println("Word must be at least 7 letters long");
			}else{
				newtheme.println(strWord);
			}
		newtheme.close();
		TextOutputFile addtheme = new TextOutputFile("themes.txt", true);
		addtheme.println(strNewTheme);
		addtheme.close();
	}
		// read user's new theme file name
		//~ String newTheme = scanner.nextLine();
		
		//~ // loop reading the words for the new theme into newWords
		//~ List<String> newWords = new ArrayList<>();
		//~ while (true) {
            //~ System.out.print("Enter word (or type 'stop'): ");
            //~ String word = scanner.nextLine();
            //~ if (word.equalsIgnoreCase("stop")) break;

            //~ if (word.length() >= 7) {
                //~ newWords.add(word);
            //~ } else {
                //~ System.out.println("Word must be at least 7 letters.");
            //~ }
            
        //~ }
		
		//~ // loop writing the new theme and its words into a file
		//~ try (PrintWriter pw = new PrintWriter(new FileWriter(newTheme))) {
            //~ for (String word : newWords) {
                //~ pw.println(word);
            //~ }
        //~ } catch (IOException e) {
            //~ System.out.println("Error creating new theme.");
            //~ return;
        //~ }
		
		//~ // add the new theme at the end of the master theme file
        //~ try (PrintWriter pw = new PrintWriter(new FileWriter(THEMES_FILE, true))) {
            //~ pw.println(newTheme);
        //~ } catch (IOException e) {
            //~ System.out.println("Error updating themes list.");
        //~ }

		//~ // tell user new theme added successfully
        //~ System.out.println("Theme added successfully.");		
	




	//~ // bubble
    //~ public static void bubble (Console con) {
        //~ // Sample 2D array with [String, Integer] elements
        //~ Object[][] data = new Object[][] {
            //~ {"Apple", 42},
            //~ {"Banana", 17},
            //~ {"Cherry", 85},
            //~ {"Date", 63},
            //~ {"Elderberry", 91},
            //~ {"Fig", 28}
        //~ };
 
        //~ // Print original array4
        
        //~ System.out.println("Original Array:");
        //~ printArray(data);
 
        //~ // Perform bubble sort based on the integer values (second column)
        //~ bubbleSortByIntegerColumn(data, 1);
 
        //~ // Print sorted array
        //~ System.out.println("\nSorted Array (by integer values, highest to lowest):");
        //~ printArray(data);
    //~ }
 
    //~ /**
     //~ * Bubble sort algorithm for a 2D array, sorting based on integer values in the specified column
     //~ * @param arr The 2D array to sort
     //~ * @param column The column index containing integer values to sort by
     //~ */
    //~ public static void bubbleSortByIntegerColumn(Object[][] arr, int column) {
        //~ int n = arr.length;
        //~ boolean swapped;
 
        //~ for (int i = 0; i < n - 1; i++) {
            //~ swapped = false;
 
            //~ for (int j = 0; j < n - i - 1; j++) {
                //~ // Cast the values to Integer for comparison
                //~ int current = ((Integer) arr[j][column]).intValue();
                //~ int next = ((Integer) arr[j + 1][column]).intValue();
 
                //~ // Compare in reverse order for highest to lowest sorting
                //~ if (current < next) {
                    //~ // Swap the entire rows
                    //~ Object[] temp = arr[j];
                    //~ arr[j] = arr[j + 1];
                    //~ arr[j + 1] = temp;
                    //~ swapped = true;
                //~ }
            //~ }
 
            //~ // If no swapping occurred in this pass, array is sorted
            //~ if (!swapped) {
                //~ break;
            //~ }
        //~ }
    //~ }
 
    //~ /**
     //~ * Utility method to print the 2D array
     //~ * @param arr The array to print
     //~ */
    //~ public static void printArray(Object[][] arr) {
        //~ for (int i = 0; i < arr.length; i++) {
            //~ System.out.println(arr[i][0] + ": " + arr[i][1]);
        //~ }
    }













}
	

    
    


	

		
		 




