import arc.*; 
import java.awt.image.*;
import java.awt.Font;
    

	

public class testCPT{
	public static void main(String[] args){
		//Print Logo
		Console con = new Console(1280,720);
		BufferedImage image = con.loadImage("hangman.jpg");
		con.drawImage(image, 0, 10);
		con.repaint();
		//Print Questions
		Font font = con.loadFont("font.ttf", 120);
		con.setDrawFont(font);
		con.drawString("Type 1 To Play", 400, 250); 
		con.repaint();
	
		
		int intCount;
		int intPlace;
		int intNum = 0;
		String strWord = "penis";
		

		intPlace = 400;
		for (intCount = 0; intCount < 10; intCount++){
			con.drawLine(intPlace,400, intPlace + 50,400);
			intPlace = intPlace + 60;
		}
		Font font = con.loadFont("font.ttf", 120);
		con.setDrawFont(font);
		con.drawString(strWord.substring(intNum, intNum + 1), 400, 250);
		con.drawString(strWord.substring(intNum + 1, intNum + 2),460, 250);
		con.drawString(strWord.substring(2,3), 520, 250);
		con.drawString(strWord.substring(3,4), 580, 250);
		con.drawString(strWord.substring(4,5), 640, 250);
		con.repaint();
		
		con.fillOval(210,200,150,150);
		con.drawLine(285,360,370,400);
		con.drawLine(285,360,200,400);
		con.drawLine(285,350,285,500);
		con.drawLine(285,500,185,600);
		con.drawLine(285,500,385,600);
		con.repaint();
		
		int intBody[][];
		intBody = new int[
		
	}
}
