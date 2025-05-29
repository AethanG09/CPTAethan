import arc.*;

public class MainMenu{
	public static void main(String[] args){
		Console con = new Console();
		int intChoice;

		con.println("Type '1' to play game");
		con.println("Type '2' to see leaderboard");
		con.println("Type '3' to add theme");
		con.println("Type '4' to quit");
		intChoice = con.readInt();
	
	if(intChoice == 1){
		con.println("hello1");
		
	
	}else if(intChoice == 2){
		con.println("hello2");
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
			while(leaderboard.eof()==false){
				con.println(leaderboard);
			}
	
	
	}else if(intChoice == 3){
		con.println("hello3");
	
	}else if(intChoice == 4){
		con.println("hello4");
	
	}else{
		con.println("what are you doing man...");
	}
}
}
