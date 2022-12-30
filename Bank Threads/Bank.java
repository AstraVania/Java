import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Bank {


	public static void main(String[] args) 
	{
		final int MAX_RANGE = 2001;
		final int MIN_RANGE = 1000;
		final int MAX_ACCOUNTS = 5;
		final int MAX_TELLERS = 10;
		Random rand = new Random();
		BankAccount Acc [] = new BankAccount[MAX_ACCOUNTS];
		for(int i = 0 ; i < MAX_ACCOUNTS ; i++)
		{
			Acc[i] = new BankAccount(0 + i , 0);
		}
		ArrayList<BankAccount> banks = new ArrayList<BankAccount>(Arrays.asList(Acc));

		Transaction toDoTransaction [] = new Transaction[MIN_RANGE];

		for(int i = 0 ; i < MIN_RANGE ; i++)
		{			
			int num =rand.nextInt(MAX_RANGE) - MIN_RANGE;


			toDoTransaction[i] = new Transaction(i%MAX_ACCOUNTS , num);			
		}

		TransactionPool pool = new TransactionPool(toDoTransaction);
		Teller[] tellers = new Teller[MAX_TELLERS];
		for(int i = 0 ; i < MAX_TELLERS ; i++)
		{  
			tellers[i]  = new Teller(pool , banks);
		}
		for(int i = 0 ; i < MAX_TELLERS ; i++)
		{
			tellers[i].start();
		}
		try
		{
			for(int i = 0 ; i < MAX_TELLERS ; i++)
			{
				tellers[i].join();
			}
		}
		catch (Exception e) {
			System.err.println("Thread Interrupted");
		}
		for(int i = 0 ; i < MAX_ACCOUNTS ; i++)
		{
			System.out.println("The Balance In Account " + (i+1) + " is " + Acc[i].getBalance());
		}

	}
}
