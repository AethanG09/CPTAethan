import arc.*;

public class CPTTools{
	public static void leadcode(Console con){
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		String strLB;
		while(leaderboard.eof() == false){
			strLB = leaderboard.readLine();
			con.println(strLB);
		}
	}
	public static void gamecode(Console con){
		String strName;
		String strTheme;
		
		con.println("entering game...");
		con.sleep(2000);
		con.println("what is your name?");
		strName = con.readLine();
		con.println("which theme do you want?");
		strTheme = con.readLine();
		
		TextInputFile themes = new TextInputFile(strTheme + ".txt");
		
		String strWrdCnt;
		int intCount = 0;
		while(themes.eof() ==false){
			strWrdCnt = con.readLine();
			intCount = intCount + 1;
		}
		//Assigns Random Number
		int intLength;

		intLength = intCount;
		String strWords[][];
		strWords = new String[intCount][2];
		int intCount2 = 0;
		int intRand;
		while(themes.eof() == false){
			strWords[intCount][0] = themes.readLine();
			intRand = (int)(Math.random()*100+1);
			strWords[intCount][1] = intRand + "";
			intCount = intCount + 1;
		}
		//Sort
		String strThemeTemp;
		for(intCount2 = 0; intCount2 < intLength; intCount2++){		
	}
}
}


