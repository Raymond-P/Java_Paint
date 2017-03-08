package betterThanPaint;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class NorthMenu extends JPanel implements ActionListener{
	private JLabel title;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu filledIn= new JMenu("Fill in shape");
	private JRadioButtonMenuItem yes = new JRadioButtonMenuItem("YES");
	private JRadioButtonMenuItem no = new JRadioButtonMenuItem("NO",true);
	private JButton panelColor = new JButton("Change me color"); 
	private JPanel top = new JPanel(new GridLayout(1, 2));
	private Color menuColor = Color.WHITE;
	
	
	protected NorthMenu() {
		//set border layout
		this.setLayout(new BorderLayout(5,5));
		
		//menu...
		menuBar.add(filledIn);
		this.add(menuBar, BorderLayout.CENTER);
		
		//set title
		this.title = new JLabel("North Menu displays current color.");
		this.top.add(title, 0);
		
		//add button 
		this.panelColor.addActionListener(this);
		this.top.add(panelColor, 1);
		
		//setting top layout
		this.add(top, BorderLayout.NORTH);
		
		//set menu
		
		
		//set buttons listeners
		this.yes.addActionListener(this);
		
		this.no.addActionListener(this);
		
		
		this.filledIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				filledIn.setEnabled(!isEnabled());
				
			}
		});
		
		//set fill in menu
		this.filledIn.add(yes);
		this.filledIn.add(no);
		
		
		//set background
		this.updateColor(Canvas.getMasterColor());
	}
	
	private void updateColor(Color C){
		this.menuColor=(C);
		this.setBackground(C);
		this.top.setBackground(C);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().trim().equals(panelColor.getActionCommand().trim())){
			Color c = JColorChooser.showDialog(this, "choose thy color", this.menuColor);
			this.updateColor(c);
			Canvas.setMasterColor(c);
			
		}
		else if (e.getSource().equals(this.no)){
			Canvas.setFilledIn(false);
			this.yes.setSelected(false);
		}
		else if (e.getSource().equals(this.yes)){
			Canvas.setFilledIn(true);
			this.no.setSelected(false);
		}
		
	}
	

}
