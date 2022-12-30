

public class Main
{
	public static void main(String[] args)
	{      
		Data data = new Data(0, 0);
		FirstThread [] first = new FirstThread[4];
		SecondThread [] second = new SecondThread[4];
		for(int i = 0 ; i < 4 ; i++)
		{
			first[i] = new FirstThread(data);
			second[i] = new SecondThread(data);
		}
		for(int i = 0 ; i < 4 ; i++)
		{
			first[i].start();
			second[i].start();
		}
	}
}
