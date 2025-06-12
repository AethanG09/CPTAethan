import arc.*;
import java.awt.image.*;
import java.awt.Font;
import java.awt.Color; 

public class MainMenu{
	public static void main(String[] args){
		Console con = new Console("HangMan", 1280,720);	
		int intNum;


	while(true){
		con.clear();
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		intNum = CPTTools.menu(con);
		if(intNum == 1){
			int intWordCount;
			int intCount;
			con.clear();
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1280,720);
			intWordCount = CPTTools.pregame(con);
			con.clear();
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1280,720);
			CPTTools.gameplay(con, intWordCount);
		}else if(intNum == 2){
			con.clear();
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1280,720);
			CPTTools.viewLeaderBoard(con);
		}else if(intNum == 3){
			con.clear();
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1280,720);
			CPTTools.addTheme(con);
		}else if(intNum == 4){
			con.clear();
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1280,720);
			CPTTools.animation(con);
			con.closeConsole();
			con.closeWindow();
			break;
		}else if(intNum == 5){
			CPTTools.secretmenu(con);
			con.println("what are you doing man...");
		}
	}
	}	
}
