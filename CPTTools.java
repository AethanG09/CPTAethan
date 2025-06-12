import arc.*;
import java.awt.image.*;
import java.awt.Font;
import java.awt.Color;

public class CPTTools{

	public static int menu(Console con){
		int intNum;
		//Print Logo
		BufferedImage image = con.loadImage("logo.jpg");
		con.drawImage(image, -30, 70);
		con.repaint();
		//Print Questions
		Font font = con.loadFont("font.ttf", 60);
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(font);
		con.drawString("Enter 1 To Play", 510, 200); 
		con.drawString("Enter 2 To View Leaderboard", 510, 270); 
		con.drawString("Enter 3 To Add Theme", 510, 340); 
		con.drawString("Enter 4 To Quit", 510, 410); 
		con.repaint();
		intNum = con.readInt();
		return intNum;
	}
	
	// View Leader Board
	public static void viewLeaderBoard(Console con){
		//open leaderboard.txt file
		TextInputFile scoresfile = new TextInputFile("leaderboard.txt");
		
		//count how many lines in leaderboard
		int intCount = 0;
		String strSub;
		while(scoresfile.eof() == false){
			strSub = scoresfile.readLine();
			intCount = intCount + 1;
		}
		scoresfile.close();
		
		//Make Again to reset readline from top
		TextInputFile scoresfile2 = new TextInputFile("leaderboard.txt");
		intCount = intCount / 2;
		// loop loading data from leaderboard file into 2D array
		int intCount2;
		String strScores[][];
		strScores = new String[intCount][2];
		
		for(intCount2 = 0; intCount2 < intCount; intCount2++){
			strScores[intCount2][0] = scoresfile2.readLine();
			strScores[intCount2][1] = scoresfile2.readLine();
		}
		scoresfile2.close();
		//add bubble sort
		String strTemp;
		int intCount3;
		int intCount4;
		for(intCount4 = 0; intCount4 < intCount; intCount4++){
			for(intCount3 = 0; intCount3 < intCount-1; intCount3++){
				if(Integer.parseInt(strScores[intCount3][1]) < Integer.parseInt(strScores[intCount3+1][1])){
					//swap words
					strTemp = strScores[intCount3][0];
					strScores[intCount3][0] = strScores[intCount3+1][0];
					strScores[intCount3+1][0] = strTemp;
										
					//swap integers
					strTemp = strScores[intCount3][1];
					strScores[intCount3][1] = strScores[intCount3+1][1];
					strScores[intCount3+1][1] = strTemp;
				}
			}
		}
		
		con.println("LeaderBoard");
		for(intCount2 = 0; intCount2 < intCount; intCount2++){
			con.println(strScores[intCount2][0] + " - " + strScores[intCount2][1]);
		}
		con.println("click enter to go back to main menu");
		con.readLine();
	}
	


	public static int pregame(Console con){

		String strPlayerName;
		String strTheme;
		int intCount0;
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		con.println("Let's Play HangMan!");
		con.println("");
		con.println("What is your name?");
		strPlayerName = con.readLine();
		con.println("");
		TextOutputFile playernameholder = new TextOutputFile("PlayerNameHolder.txt");
		playernameholder.println(strPlayerName);
		playernameholder.close();
		
		//display available themes from master theme file
		String strThemes;
		con.println("Available Themes: ");
		TextInputFile masterthemefile = new TextInputFile("themes.txt");
		while(masterthemefile.eof()==false){
			strThemes = masterthemefile.readLine();
			con.println("  - " + strThemes);
		}
		masterthemefile.close();
		
		
		//ask user to select the theme they want to play
		con.println("Which of these themes do you want to play?");
		strTheme = con.readLine();
		
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
	public static void gameplay(Console con, int intWordCount){

		int intRead = 0;
		TextInputFile file = new TextInputFile("WordTemp.txt");

		int intWinCount = 0;
		boolean blnGame = true;
		int intCount0;	
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
				con.drawImage(image, -50, 60);
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
					
					intRand = (int)(Math.random()*100+1);
					strLetters[intCount2][1] = intRand + "";
					
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

							strTemp = strLetters[intCount4][1];
							strLetters[intCount4][1] = strLetters[intCount4+1][1];
							strLetters[intCount4+1][1] = strTemp;

								
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
						String strGuess;
						Font font1 = con.loadFont("font.ttf", 100);
						con.setDrawColor(Color.WHITE);
						con.setDrawFont(font1);
						con.drawString("Guess a Word", 400, 100); 
						strGuess = con.readLine();
						
						if(strGuess.equalsIgnoreCase(strWord)){
							con.drawString("You Won!", 400, 450); 
							blnGame = false;
							blnWin = true;
							intWinCount = intWinCount + 1;
							Font font2 = con.loadFont("font.ttf",100);
							con.setDrawFont(font2);
							con.drawString(strWord, 400, 560);
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
								con.fillOval(180,250,150,150);
							}else if(intCount5 == 1){
								con.drawLine(255,400,255,550);
							}else if(intCount5 == 2){
								con.drawLine(255,410,170,450);
							}else if(intCount5 == 3){
								con.drawLine(255,410,340,450);
							}else if(intCount5 == 4){
								con.drawLine(255,550,155,650);
							}else if(intCount5 == 5){
								con.drawLine(255,550,355,650);
							}
							if(intCount5 == 5){
								Font font3 = con.loadFont("font.ttf", 80);
								con.setDrawFont(font3);
								con.drawString("You Lost", 400, 450);
								con.drawString("The word was " + strWord, 300,500);
								blnGame = false;
								blnWin = false;		
							}else{
								con.println("Guess Again");	
								intCount5 = intCount5 + 1;	
							}
						}		
				con.clear();
				}

			}
			if(file.eof()==false){
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
		}
		
		//Save Player Name + Score
		TextInputFile playernameholder = new TextInputFile("PlayerNameHolder.txt");
		String strPlayerName;
		strPlayerName = playernameholder.readLine();
		
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
		}
		newtheme.close();
		TextOutputFile addtheme = new TextOutputFile("themes.txt", true);
		addtheme.println(strNewTheme);
		addtheme.close();
		con.println("theme successfully added");
		con.sleep(400);
	}
	public static void secretmenu(Console con){
		con.clear();
		con.setDrawColor(Color.BLUE);
		con.fillRect(0,0,1280,720);
		BufferedImage image = con.loadImage("homer.png");
		con.drawImage(image, 100, 100);
		con.repaint();
		Font font = con.loadFont("font.ttf", 40);
		con.setDrawColor(Color.WHITE);
		con.setDrawFont(font);
		con.drawString("Why did Homer Simpson bring a ladder to the donut shop?", 300, 200);
		con.drawString("Because he heard the donuts were on the house!", 300, 300);
		con.drawString("Press 'enter' to exit", 300, 400);
		con.readLine();
	}
	
	
	public static void animation(Console con){
        String[] frames = {
            "   (•_•)   ",
            "  ( •_•)>⌐■-■ ",
            "  (⌐■_■)     ",
            "  ( •_•)>⌐■-■ ",
            "   (•_•)     "
        };

        for (int i = 0; i < 2; i++) {
            for (String frame : frames) {
				con.println("byeee");
                con.println("\r" + frame); // Overwrites the previous line
                con.sleep(300); // Delay between frames
                con.clear();
            }
        }
	}
}



    
    


	

		
		 




