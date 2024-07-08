package Zohoproject;


import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class VendorManager {

	private ArrayList<Vendor>vendorlist;
	
	public VendorManager()
	{
		vendorlist=new ArrayList<Vendor>();
	}
	
	public void addVendor(String name)
	{
		vendorlist.add(new Vendor(name));
	}
	
	public Vendor findVendorByName(String s)
	{
	      for(Vendor v:vendorlist)
	      {
	    	  if(v.getName().equalsIgnoreCase(s))
	    	  {
	    		  return v;
	    	  }
	      }
	      return null;
	}
	
	public void addBillsToVendors(String s,double amt,double amtPaid,Date date)
	{
		Vendor vendor=findVendorByName(s);
		
		if(amtPaid>amt)
		{
			System.out.println("Please enter lesser amount");
			return;
		}
		
		if(vendor!=null)
		{
			vendor.addBills(new Bill(amt,amtPaid,date));
			System.out.println("bill added to vendor");
		}
		else {
			System.out.println("Vendor not found!");
		}
		
	}
	
	public void printAllBills()
	{
		for(Vendor v:vendorlist)
		{
			System.out.println("  Vendor name: "+v.getName());
			System.out.println();
			
			for(Bill b:v.getBills())
			{
				System.out.println("  Bill Date: "+b.getDate());
				System.out.println("  Bill No: "+b.getBillNo());
				System.out.println("  Bill amount: "+b.getAmt());
				System.out.println("  Amount paid: "+b.getamtPaid());
				System.out.println("  Balance amount: "+b.getBalance());
				System.out.println();
			}
			
		}
	}

	public void printPaymentsByMonth(int month,int year)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("MM-yyyy");
		
		for(Vendor v:vendorlist)
		{
			System.out.println(v.getName());
			for(Bill b:v.getBills())
			{
				if(sdf.format(b.getDate()).equalsIgnoreCase(String.format("%02d-%d",month,year)))
				{
					System.out.println("Bill Date: "+b.getDate());
					System.out.println("Bill No: "+b.getBillNo());
					System.out.println("Bill amount: "+b.getAmt());
					System.out.println("amount paid: "+b.getamtPaid());
					System.out.println("Balance amount: "+b.getBalance());
				}
			}
			System.out.println();
		}
		
	}

	public ArrayList<Vendor> getTopPaidVendor()
	{
		double max=0;
		ArrayList<Vendor>topPaid=new ArrayList<Vendor>();
		
		for(Vendor v:vendorlist)
		{
			if(v.getTotalPayments()>max)
			{
				max=v.getTotalPayments();
				topPaid.removeAll(topPaid);
				topPaid.add(v);
			}
			else if(v.getTotalPayments()==max)
			{
				topPaid.add(v);
			}
		}
		
		return topPaid;
	}

	public void printBalances()
	{
		for(Vendor v:vendorlist)
		{
			
			System.out.print("vendor name: "+v.getName());
			System.out.println();
			
			for(Bill b:v.getBills())
			{
				System.out.println("Bill No: "+b.getBillNo());
				System.out.println("balance amount: "+(b.getBalance()));
				System.out.println();
			}
			System.out.println("Total balance to "+v.getName()+" :" +v.getBalance());
			System.out.println();
		}
	}
	
	public void printBillsInRange(Date startDate,Date endDate)
	{
		for(Vendor v:vendorlist)
		{
			for(Bill b:v.getBills())
			{
				if(b.getDate().before(startDate)==false && b.getDate().after(endDate)==false)
				{
					System.out.println("Vendor name: "+v.getName());
					System.out.println("Bill No: "+b.getBillNo());
					System.out.println(" Bill Date: "+b.getDate());
					System.out.println(" Bill amount: "+b.getAmt());
					System.out.println(" Amount paid: "+b.getamtPaid());
				}
			}
			System.out.println();
		}
	}
	
	public void payBalance(String vendorname,int billNo,double amount)
	{
		for(Vendor v:vendorlist)
		{
			if(v.getName().equalsIgnoreCase(vendorname))
			{
				for(Bill b:v.getBills())
				{
					if(b.getBillNo()==billNo)
					{
						b.updateBill(amount);
					}
				}
			}
		}
	}
	
	
}
