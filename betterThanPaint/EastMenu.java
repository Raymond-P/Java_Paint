package betterThanPaint;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EastMenu extends JPanel {
	JLabel title;

	protected EastMenu() {
		// Setting Title
		this.title = new JLabel("East Menu");
		this.add(title);

		// Setting color
		this.setBackground(Color.MAGENTA);
	}

}
