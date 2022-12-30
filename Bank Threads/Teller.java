import java.util.ArrayList;
import java.util.Random;

public class Teller extends Thread {
	private TransactionPool pool;
	private final int MAX = 101;
	private Random rand = new Random();
	private ArrayList<BankAccount> banks;

	Teller(TransactionPool pool, ArrayList<BankAccount> banks)
	{
		this.pool = pool;
		this.banks = banks;
	}
	public void run() 
	{
		super.run();

		while(true) 
		{
			Transaction transaction = null;
			synchronized(pool) 
			{
				transaction = pool.getTransaction();
				if(transaction == null)
				{
					break;
				}
			}
			int account = transaction.getAccount();

			synchronized(banks) 
			{
				for(int i = 0 ;  i < banks.size() ; i++)
				{
					BankAccount bank = banks.get(i);
					if(bank.getAccountNum() == account)
					{
						bank.transaction(transaction.getTransaction());
						break;
					}
				}
			}

			try 
			{
				sleep(rand.nextInt(MAX));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}


