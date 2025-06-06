import arc.*;
import java.awt.image.*;

public class CPTTools{
	public static void leadcode(Console con){
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		String strLB;
		while(leaderboard.eof() == false){
			strLB = leaderboard.readLine();
			con.println(strLB);
		}
	}
	public static String pregame(Console con){

		String strName;
		String strTheme;
		
		con.println("entering game...");
		//~ con.sleep(2000);
		con.println("what is your name?");
		strName = con.readLine();
		con.println("which theme do you want?");
		strTheme = con.readLine();
		
		//~ con.println(strTheme);
		
		TextInputFile themesFile = new TextInputFile(strTheme + ".txt");
		
		// Find # of words in theme file
		String strWrdTst;
		int wordCount = 0;
		while(themesFile.eof() == false){
			strWrdTst = themesFile.readLine();
			wordCount = wordCount + 1;
		}
		
		// Close file
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
		con.println("finished");
		
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
		String strFinWrd;
		strFinWrd = strWords[wordCount-1][0];
		return strFinWrd;
		
		
	}
	public static void gameplay(Console con, String strWord){
		
		int intLength;	
		int intCount;
		BufferedImage image = con.loadImage("hangman.jpg");
		con.drawImage(image, -50, 10);
		con.repaint();
		
		int intPlace;
		intPlace = 350;
		intLength = strWord.length();
		for(intCount = 0; intCount < intLength; intCount++){		
			con.drawLine(intPlace,400, intPlace + 50,400);
			intPlace = intPlace + 60;
		}
		
		String strGuess;
		con.println("guess a word");
		strGuess = con.readLine();
		if(strGuess.equalsIgnoreCase(strWord)){
			con.println("you won! also your a dipshit");
		}else{
			con.println("you fucking suck");
			String strLetters[][];
			strLetters = new String[intLength][2];
			int intCount2;
			for(intCount2 = 0; intCount2 < intLength; intCount2++);

			
			
		}
	}	
}
		
		




