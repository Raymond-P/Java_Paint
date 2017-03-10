package betterThanPaint;

import java.awt.BorderLayout;
import java.net.NoRouteToHostException;

import javax.swing.JFrame;

public class GUI extends JFrame {
	
	private Canvas canvas;
	private NorthMenu topMenu;
	private EastMenu eastMenu;
	private WestMenu westMenu;
	private SouthMenu southMenu;
	
	private GUI(String title){
		//Set title
		this.setTitle(title);
		
		//Set the location of the GUI
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		//make it non resize able
		this.setResizable(false);
		
		//Set the close on exit
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Create JPanels
		canvas = new Canvas();
		topMenu = new NorthMenu();
		eastMenu = new EastMenu();
		westMenu = new WestMenu();
		southMenu = new SouthMenu();
		
		//Add JPanels
		this.add(canvas,BorderLayout.CENTER);
		this.add(eastMenu, BorderLayout.EAST);
		this.add(topMenu,BorderLayout.NORTH);
		this.add(westMenu, BorderLayout.WEST);
		this.add(southMenu,BorderLayout.SOUTH);
		
		
		//Make it visible
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		GUI main_GUI = new GUI("Betta' Dan' Pain't");
	}

}
