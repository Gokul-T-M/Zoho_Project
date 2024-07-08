package Zohoproject;


import java.util.ArrayList;

public class Vendor {
	
	private static int accountCounter = 1000;
	private int bankAccountNo;
	private String name;
	private ArrayList<Bill>bills;
	
	
	public Vendor(String name)
	{
		this.bankAccountNo=accountCounter++;
		this.name=name;
		this.bills=new ArrayList<Bill>();
	}
	
	public int getBankAccountNumber() {
        return bankAccountNo;
    }
	
	public String getName() {
		return name;
	}
	
	public void addBills(Bill bill)
	{
		bills.add(bill);
	}
	
	public ArrayList<Bill> getBills()
	{
		return bills;
	}
	
	public double getTotalPayments()
	{
		double sum=0;
		
		for(Bill b:bills)
		{
			sum+=b.getamtPaid();
		}
		
		return sum;
	}
	
	public double getBalance()
	{
		double Balance=0;
		
		for(Bill b:bills)
		{
			Balance=(b.getBalance());
		}
		return Balance;
	}
	
}
