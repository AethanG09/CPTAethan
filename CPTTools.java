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
}

