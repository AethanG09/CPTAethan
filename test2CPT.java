import arc.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;

public class test2CPT {
	public static void main(String[] args){
		Console con = new Console();
		
		BufferedImage image = con.loadImage("hangman.jpg");
		con.drawImage(image, 300, 300);
		con.repaint();
		
		con.fillOval(50,50,100,100);
		con.repaint();
	}		
}

