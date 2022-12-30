import java.util.Random;

public class FirstThread extends Thread
{
	private final int[] randArr;
	private final int SIZE = 20;
	private final Data data; 

	public FirstThread(Data data)
	{
		this.data = data;
		randArr = new int[SIZE];
		Random rand = new Random();
		for (int i = 0; i < SIZE; i++)
		{
			randArr[i] = rand.nextInt(SIZE*SIZE);
		}

	}
	public void run()
	{
		for (int i = 0; i < SIZE; i += 2)
		{
			data.update(randArr[i], randArr[i + 1]);

			try
			{

				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
