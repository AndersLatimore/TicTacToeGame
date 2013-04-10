import java.awt.Graphics;

public interface ITicTacGraphics<T>
{
	/**
	 * Interface to handle the basic graphics
	 */
	public void paint(Graphics g);
	public void drawX(Graphics g,int x,int y,int w,int h);
	public void drawO(Graphics g,int x,int y,int w,int h);
}
