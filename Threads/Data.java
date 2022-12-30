import java.util.concurrent.Semaphore;

public class Data {
	private final Semaphore semDiff = new Semaphore(0);
	private int x;
	private int y;
	private int cycle = 0;

	public Data(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	public int getDiff() 
	{
		try 
		{
			semDiff.acquire();
			return Math.abs(x - y);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			semDiff.release();
		}
		return 0;
	}

	public synchronized void update(int dx, int dy) 
	{
		x += dx;
		y += dy;
		System.out.println("in cycle " + cycle + " the numbers are (" + x + " , " + y + ")");
		semDiff.release();
		cycle++;
	}
}