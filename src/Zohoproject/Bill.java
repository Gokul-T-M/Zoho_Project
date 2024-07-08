package Zohoproject;

import java.util.Date;

public class Bill {

	private static int billcounter=0;
	private int billNo;
	private double amt;
	private double amtPaid;
	private double balance=0;
	private Date date;
	
	
	public Bill(double amt,double amtPaid,Date date)
	{
		this.billNo=++billcounter;
		this.amt=amt;
		this.amtPaid=amtPaid;
		this.date=date;
		this.balance=balance+(amt-amtPaid);
	}
	
	public void updateBill(double amount)
	{
		if(amount>balance)
		{
			System.out.println("please enter lesser amount");
			return;
		}
		this.amtPaid+=amount;
		this.balance-=amount;
	}
	
	public int getBillNo()
	{
		return billNo;
	}
	
	public double getAmt()
	{
		return amt;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public double getamtPaid()
	{
		return amtPaid;
	}
	
	
	public Date getDate()
	{
		return date;
	}
	
}
