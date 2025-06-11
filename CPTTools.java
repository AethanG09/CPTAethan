import arc.*;
import java.awt.image.*;
import java.awt.Font;
import java.awt.Color;

public class CPTTools{
	public static int menu(Console con){
		int intNum;
		//Print Logo
		BufferedImage image = con.loadImage("logo.jpg");
		con.drawImage(image, 390, 100);
		con.repaint();
		//Print Questions
		Font font = con.loadFont("font.ttf", 40);
		con.setDrawFont(font);
		con.drawString("Enter 1 To Play", 200, 100); 
		con.drawString("Enter 2 To Play", 200, 150); 
		con.drawString("Enter 3 To Play", 200, 200); 
		con.drawString("Enter 4 To Play", 200, 250); 
		con.repaint();
		intNum = con.readInt();
		return intNum;
	}
	public static void leadcode(Console con){
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		String strLB;
		while(leaderboard.eof() == false){
			strLB = leaderboard.readLine();
			con.println(strLB);
		}
	}
	public static int pregame(Console con){

		String strName;
		String strTheme;
		
		con.println("entering game...");
		con.println("what is your name?");
		strName = con.readLine();
		con.println("which theme do you want?");
		strTheme = con.readLine();
		
		
		TextInputFile themesFile = new TextInputFile(strTheme + ".txt");
		
		// Find # of words in theme file
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
				if(Integer.parseInt(strWords[intCount2][1]) > Integer.parseInt(strWords[intCount2+1][1])){
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
	//Main Method
	public static void gameplay(Console con, int intWordCount){

		int intRead = 0;
		TextInputFile file = new TextInputFile("WordTemp.txt");


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
						if(Integer.parseInt(strLetters[intCount4][1]) < Integer.parseInt(strLetters[intCount4+1][1])){
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

}
}

	

		
		 




