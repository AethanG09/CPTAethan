import arc.*;
import java.awt.image.*;

public class test2CPT {
	public static void main(String[] args){
		Console con = new Console();
		
		BufferedImage image = con.loadImage("hangman.jpg");
		con.drawImage(image, 300, 300);
		con.repaint();
	}		
}

