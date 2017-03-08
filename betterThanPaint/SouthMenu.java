package betterThanPaint;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* @author Raymond Perez
**/
public class SouthMenu extends JPanel {
	JLabel title;
	
	protected SouthMenu(){
		//Setting Title
		this.title = new JLabel("South Menu");
		this.add(title);
		
		//Setting color
		this.setBackground(Color.PINK);
	}
}
