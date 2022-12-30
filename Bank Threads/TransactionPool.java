import java.util.ArrayList;
import java.util.Arrays;

public class TransactionPool
{

	private ArrayList<Transaction>  pool;
	public TransactionPool( Transaction toDoTransactrion[])
	{
		pool = new ArrayList<Transaction>(Arrays.asList(toDoTransactrion));

	}
	public synchronized Transaction getTransaction()
	{

		try
		{
			Transaction temp =  pool.get(0);
			pool.remove(0);
			return temp;
		}
		catch (Exception e) 
		{
			return null;
		}
	}
}
