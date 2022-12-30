
import java.util.concurrent.Semaphore;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private int accountNum;
	private int balance;
	private final Semaphore negativeSem = new Semaphore(1);
	private final ReentrantLock lock = new ReentrantLock();
	private int negative = 0;
	public BankAccount(int accountNum, int balance) 
	{
		this.accountNum = accountNum;
		this.balance = balance;
	}
	public void transaction(int cash)     
	{

		if(cash > 0 || balance + cash > 0)
		{
			this.deposit(cash);
			if(balance + negative > 0 && negative < 0)
			{
				balance+=negative;
				negative = 0;
				negativeSem.release();
			}
		}
		else
		{
			this.withdraw(cash);
		}

	}

	public synchronized  int getBalance() 
	{      
		return balance + negative;       
	}

	private void deposit(int amount) {
		lock.lock();
		try 
		{
			balance += amount;
		}
		finally 
		{
			lock.unlock();
		}
	}
	private synchronized void withdraw(int amount) 
	{

		if(negativeSem.availablePermits() == 0)
		{
			try 
			{
				this.wait(50); //to prevent infinite loop give promotion to negative balance
				negativeSem.release();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		try 
		{
			negativeSem.acquire();
			negative = balance + amount;
			balance = 0;
		} catch (InterruptedException e)
		{

			e.printStackTrace();
		}
	}
	public int getNeg()
	{
		return negative;
	}
	public int  getAccountNum() 
	{
		return accountNum;
	}
}
