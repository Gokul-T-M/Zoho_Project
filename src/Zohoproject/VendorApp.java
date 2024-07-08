package Zohoproject;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VendorApp {

	private static VendorManager VMobj=new VendorManager();
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	private static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException
	{
		Boolean a=true;
		
		while(a)
		{
			System.out.println();
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Bill to Vendor");
            System.out.println("3. pay balances to Vendors");
            System.out.println("4. Print All Bills");
            System.out.println("5. Print Payments by Month");
            System.out.println("6. Print Top Paid Vendor");
            System.out.println("7. Print Balances");
            System.out.println("8. Print Bills in Date Range");
            System.out.println("9. Exit");
            System.out.println();
            System.out.print("Enter choice: ");
            
     
            int val=0;
            
            try{
            	val=sc.nextInt();
            }
            catch(Exception e)
            {
            	System.out.println("please enter a integer");
            }
            
            sc.nextLine();
            
            
            switch(val)
            {
            case 1:
            	
            	System.out.print("enter vendor name: ");
            	String s=sc.nextLine();
				VMobj.addVendor(s);
				System.out.println("Vendor "+s+" added succesfully");
				
				break;
				
            case 2:
            	
            	System.out.println("Enter Vendor name: ");
            	String str=sc.nextLine();
            	System.out.println("Enter amount: ");
            	double amt=sc.nextDouble();
            	System.out.println("Enter amount paid: ");
            	double amtpaid=sc.nextDouble();
            	
            	sc.nextLine(); 
            	
            	System.out.println("Enter date(dd-MM-yyyy)");
            	try{
            		Date date=sdf.parse(sc.nextLine());
            		VMobj.addBillsToVendors(str,amt,amtpaid,date);
            	}
            	catch(Exception e)
            	{
            		System.out.println(" invalid date format! ");
            	}
            	
            	
            	break;
            	
            case 3:
            	
            	System.out.println("Enter Vendor to pay: ");
            	String st=sc.nextLine();
            	System.out.println("Enter bill no: ");
            	int no=sc.nextInt();
            	System.out.println("Enter the amount to pay: ");
            	double bal=sc.nextDouble();
            	VMobj.payBalance(st,no,bal);
            	break;
            	
            	
            case 4: 	
            	
            	VMobj.printAllBills();
            	break;
            	
            	
            	
            case 5:
            	
            	System.out.println("enter month(MM): ");
            	int month=sc.nextInt();
            	System.out.println("enter year(YYYY): ");
            	int year=sc.nextInt();
            	
            	VMobj.printPaymentsByMonth(month,year);
            	break;
            	
            case 6:
            	

                for(Vendor v:VMobj.getTopPaidVendor())
                {
                	System.out.print(v.getName()+"   "+v.getTotalPayments());
                	System.out.println();
                	
                }
                
            	break;
            	
            case 7:
            	
            	VMobj.printBalances();
            	break;
            	
            case 8:
            	
            	System.out.println("enter the starting date(dd-MM-yyyy): ");
            	try{
            		Date startDate=sdf.parse(sc.nextLine());
            	    System.out.println("enter the ending date(dd-MM-yyyy): ");
            	    Date endDate=sdf.parse(sc.nextLine());
            	
            	    VMobj.printBillsInRange(startDate,endDate);
            	}
            	catch(Exception e)
            	{
            		System.out.println("invalid date format");
            	}
            	
            	break;
            	
            	
            case 9:
            	a=false;
            	System.out.println("thank you!");
            	break;
            	
            default:
            	System.out.println("Invalid choice.Please enter a correct choice.");
            		
            }
		}
	}
	
}
