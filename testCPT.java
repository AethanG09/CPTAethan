import arc.*;
import java.awt.image.*;
	

public class testCPT{
	public static void main(String[] args){
		Console con = new Console(1280,720);
		BufferedImage image = con.loadImage("hangman.jpg");
		con.drawImage(image, 0, 10);
		con.repaint();
		
		int intCount;
		int intPlace;

		intPlace = 300;
		for (intCount = 0; intCount < 10; intCount++){
			con.drawLine(intPlace,400, intPlace + 50,400);
			intPlace = intPlace + 60;
		}
		
	}
}
