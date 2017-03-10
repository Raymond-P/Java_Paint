/**
 * 
 */
package betterThanPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Raymond Perez
 *
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	private static boolean drawShape  = true;
	private static boolean usePen = false;
	private static boolean useText = false;
	private static ArrayList<Boolean> a;
	private static int masterSize  = 10;//master size of what's drawn 
	private static Color masterColor = Color.BLACK;
	protected int lastX = 0;
	protected int lastY = 0;

	protected enum Shapes{Circle, Square, None};

	private static Shapes currentShape = Shapes.Square;
	private static boolean filledIn = false;
	private Font font;
	private FontMetrics metrics;
	
	public Canvas() {
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		
		this.addKeyListener(this);
		this.setBackground(Color.WHITE);
	
	}
	
	/**
	 * @return the useText
	 */
	public static boolean isUseText() {
		return useText;
	}

	/**
	 * @param useText the useText to set
	 */
	public static void setUseText(boolean useText) {
		Canvas.useText = useText;
	}

	/**
	 * @return the usePen
	 */
	public static boolean isUsePen() {
		return usePen;
	}

	/**
	 * @param usePen the usePen to set
	 */
	public static void setUsePen(boolean usePen) {
		Canvas.usePen = usePen;
	}

	/**
	 * @return the filledIn
	 */
	public static boolean isFilledIn() {
		return filledIn;
	}

	/**
	 * @param filledIn the filledIn to set
	 */
	public static void setFilledIn(boolean filledIn) {
		Canvas.filledIn = filledIn;
	}

	/**
	 * @return the drawShape
	 */
	public static boolean isDrawShape() {
		return drawShape;
	}

	/**
	 * @param drawShape the drawShape to set
	 */
	public static void setDrawShape(boolean drawShape) {
		Canvas.drawShape = drawShape;
	}

	/**
	 * @return the masterSize
	 */
	public static int getMasterSize() {
		return masterSize;
	}

	/**
	 * @param masterSize the masterSize to set
	 */
	public static void setMasterSize(int masterSize) {
		Canvas.masterSize = masterSize;
	}

	/**
	 * @return the masterColor
	 */
	public static Color getMasterColor() {
		return masterColor;
	}

	/**
	 * @param masterColor the masterColor to set
	 */
	public static void setMasterColor(Color masterColor) {
		Canvas.masterColor = masterColor;
	}

	/**
	 * @return the currentShape
	 */
	public static Shapes getCurrentShape() {
		return Canvas.currentShape;
	}

	/**
	 * @param currentShape the currentShape to set
	 */
	public static void setCurrentShape(Shapes currentShape) {
		Canvas.currentShape = currentShape;
	}

	/**
	 * Records the location of the x and y coordinates of the mouse event
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 */
	private void record(MouseEvent e) {
		this.lastX = e.getX();
		this.lastY = e.getY();

	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		this.requestFocus();
		this.record(e);
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.record(e);
		if (Canvas.drawShape){
			Graphics g = this.getGraphics();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(masterColor);
			
			//g2d.drawOval(lastX - (masterSize/2), lastY - (masterSize/2), masterSize, masterSize);
			if(!filledIn ){	
				switch (Canvas.currentShape) {
				case Circle:
					g2d.drawOval(lastX - (masterSize/2), lastY - (masterSize/2), masterSize, masterSize);
					break;
					
				case Square:
					g2d.drawRect(lastX - (masterSize/2), lastY -(masterSize/2), masterSize, masterSize);
		
				default:
					break;
				}
			}
			else{
				switch (Canvas.currentShape) {
				case Circle:
					g2d.fillOval(lastX - (masterSize/2), lastY - (masterSize/2), masterSize, masterSize);
					break;
					
				case Square:
					g2d.fillRect(lastX - (masterSize/2), lastY -(masterSize/2), masterSize, masterSize);
		
				default:
					break;
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Canvas.usePen){
			Graphics g = this.getGraphics();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Canvas.masterColor);
			g2d.setStroke(new BasicStroke(Canvas.masterSize));
			g2d.drawLine(lastX, lastY, arg0.getX(), arg0.getY());
			this.record(arg0);
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void record(int x, int y){
		this.lastX = x;
		this.lastY = y;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (Canvas.useText){
			String in = String.valueOf(e.getKeyChar());
			Graphics g = this.getGraphics();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Canvas.masterColor);
			this.font = new Font("Comic Sans MS", Font.BOLD, Canvas.masterSize);//set size to master size?
			
			g2d.setFont(font);
			
			this.metrics = getFontMetrics(font);
			
			g2d.drawString(in, lastX, lastY);
			this.record(this.lastX+ this.metrics.stringWidth(in)+2, this.lastY);
		}
	}
	
	

}
