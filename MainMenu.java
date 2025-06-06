import arc.*;
import java.awt.image.*;                       

public class MainMenu{
	public static void main(String[] args){
		Console con = new Console(1280,720);	
		int intChoice;
		int intMS;
		
		

		con.println("Type '1' to play game");
		con.println("Type '2' to see leaderboard");
		con.println("Type '3' to add theme");
		con.println("Type '4' to quit");
		intChoice = con.readInt();
	
	if(intChoice == 1){
		String strWord;
		strWord = CPTTools.pregame(con);
		con.clear();
		con.println(strWord);
		CPTTools.gameplay(con, strWord);
		
		
		
		
		
	
	}else if(intChoice == 2){
		
		CPTTools.leadcode(con);
		

	
	
	}else if(intChoice == 3){
		con.println("hello3");
	
	}else if(intChoice == 4){
		con.println("hello4");
	
	}else{
		con.println("what are you doing man...");
	}
}
}
