
public abstract class ITicTacGame 
{
	/**
	 * Abstract class for functions of the game
	 */
	public abstract void clearAll();
	public abstract void clear(int i);
	public abstract void clear(int x, int y);
	public abstract void putX(int i);
	public abstract void putX(int x, int y);
	public abstract void putO(int i);
	public abstract void putO(int x, int y);
	public abstract char get(int i);
	public abstract char get(int x, int y);
	public abstract int checkWin();
}
