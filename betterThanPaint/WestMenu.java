package betterThanPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import betterThanPaint.Canvas.Shapes;

public class WestMenu extends JPanel implements ActionListener, ChangeListener {
	private JLabel title;
	private Color menuColor = Color.GRAY;
	private JPanel inerPanel = new JPanel(new GridLayout(7,1,10,10));
	private JRadioButton useShape  = new JRadioButton("Use shape");
	private JRadioButton usePen = new JRadioButton("Use Pen");
	private Canvas.Shapes[] a= {Shapes.Circle,Shapes.Square};
	private JComboBox dropDownMenu = new JComboBox<>(a);
	private JRadioButton useText = new JRadioButton("Use Text",false );
	private JSlider sizeSlider = new JSlider(SwingConstants.HORIZONTAL, 10, 100, 10);
	
	
	protected WestMenu(){
	
		this.setLayout(new BorderLayout());
		
		title= new JLabel("West Menu");
		this.add(title, BorderLayout.NORTH);
		//set up slider
		this.sizeSlider.addChangeListener(this);
		this.sizeSlider.setMajorTickSpacing(10);
		this.sizeSlider.setMinorTickSpacing(5);
		this.sizeSlider.setPaintTicks(true);
		this.sizeSlider.setPaintLabels(true);
		this.sizeSlider.setValue(Canvas.getMasterSize());
		this.sizeSlider.setName("Master size");
		this.sizeSlider.setToolTipText("Changes the size of what's being drawn");
		
		//set up radio buttons
		this.useShape.addActionListener(this);
		this.usePen.addActionListener(this);
		this.useText.addActionListener(this);
		this.dropDownMenu.addActionListener(this);
		
		this.dropDownMenu.setSelectedIndex(1);
		
		//set up iner panel
		this.inerPanel.add(this.usePen);
		this.inerPanel.add(this.useShape);
		this.inerPanel.add(this.dropDownMenu);
		this.inerPanel.add(useText);
		this.inerPanel.add(sizeSlider);
		
		
		//adding the iner panel
		this.add(inerPanel, BorderLayout.CENTER);
		
		this.updateColor(menuColor); 
	}
	/**
	 * Updates the color of the Westmenu panel along with its internal panels
	 * @param C : the color to be updated to
	 */
	private void updateColor(Color C){
		this.setBackground(C);
		this.inerPanel.setBackground(C);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().trim().equals(useShape.getActionCommand().trim())){
			Canvas.setDrawShape(true);
			Canvas.setUsePen(false);
			Canvas.setUseText(false);
			this.usePen.setSelected(false);
			this.useText.setSelected(false);
		}
		else if (e.getSource().equals(usePen)){
			Canvas.setUsePen(true);
			Canvas.setDrawShape(false);
			Canvas.setUseText(false);
			this.useShape.setSelected(false);
			this.useText.setSelected(false);
		}
		else if (e.getSource().equals(this.dropDownMenu)){
			Canvas.Shapes selected = (Shapes) this.dropDownMenu.getSelectedItem();
			Canvas.setCurrentShape(selected);
			Canvas.setDrawShape(true);
			Canvas.setUsePen(false);
			Canvas.setUseText(false);
			this.useShape.setSelected(true);
			this.usePen.setSelected(false);
			this.useText.setSelected(false);
		}
		else if (e.getSource().equals(this.useText)){
			Canvas.setUseText(true);
			Canvas.setUsePen(false);
			Canvas.setDrawShape(false);
			this.useShape.setSelected(false);
			this.usePen.setSelected(false);
		
		}
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(this.sizeSlider)){
			Canvas.setMasterSize(this.sizeSlider.getValue());
		}
	}

}
