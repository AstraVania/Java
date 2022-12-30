
public class SecondThread extends Thread
{
	private final Data data;


	public SecondThread(Data data)
	{
		this.data = data;
	}
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("cycle the difference is :" + data.getDiff());
			try
			{              
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
