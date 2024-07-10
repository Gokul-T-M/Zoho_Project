package Zoho_updated;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class VendorApp {

	private static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException	{
		
		VendorDAO vdao=new VendorDAO();
		BillDAO bdao=new BillDAO();
		
		
		
		Boolean a=true;
		
		while(a)
		{
			System.out.println();
            System.out.println("1. Add Vendor");
            System.out.println("2.delete a vendor");
            System.out.println("3.display vendor list");
            System.out.println("4. Add Bill to Vendor");
            System.out.println("5.print bills from a vendor");
            System.out.println("6. Print All Bills");
            System.out.println("7. pay balances to Vendors");
            System.out.println("8. Print Payments by Month");
            System.out.println("9. Print Top Paid Vendor");
            System.out.println("10. Print Balances to vendors");
            System.out.println("11. Print Bills in Date Range");
            System.out.println("12.delete paid bills");
            System.out.println("13. Exit");
          
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
            	System.out.println("enter bank account number: ");
            	String bank_acct=sc.nextLine();
            	
            	try {
					vdao.add_vendor(s,bank_acct);
				}
            	catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Vendor "+s+" added succesfully");
				
				break;
				
           case 2:
            	
            	System.out.println("enter the vendor_id to be deleted: ");
            	int vendor_id=sc.nextInt();
            	try {
					vdao.delete_a_vendor(vendor_id);
				} 
            	catch (SQLException e) {
					e.printStackTrace();
				}
            	break;
				
           case 3:
             	
             	try {
         			vdao.displayVendorInfo();
         		} 
             	catch (SQLException e) {
         			e.printStackTrace();
         		}
             	break;
            	
           case 4:
            	
            	System.out.println("Enter Vendor ID: ");
            	int v_id=sc.nextInt();
            	System.out.println("Enter amount: ");
            	double amt=sc.nextDouble();
            	System.out.println("Enter amount paid: ");
            	double amtpaid=sc.nextDouble();
            	
            	sc.nextLine();  
            	
            	System.out.println("Enter date(dd-MM-yyyy)");
                String date=sc.nextLine();
            	
				try {
					bdao.add_bills(v_id,amt,amtpaid,date);
				}
				catch (SQLException e) {
					System.out.println("please enter date in valid format");
				}
            	
            	break;
            	
          case 5:
            	
            	try {
            	System.out.println("enter the vendor id: ");
            	int id=sc.nextInt();
				vdao.print_bills_of_a_vendor(id);
				}
            	catch (SQLException e) {
					e.printStackTrace();
				}
            	break;
            	
          case 6:
            
        	    try {
					bdao.print_all_bills();
				} 
                catch (SQLException e) {
					e.printStackTrace();
				}
            	break;
              
           case 7:
            	
        	    System.out.println("Enter bill no: ");
            	int no=sc.nextInt();
            	System.out.println("Enter the amount to pay: ");
            	double bal=sc.nextDouble();
           	    try {
					bdao.pay_balance_to_vendors(no,bal);
				}
           	    catch (SQLException e) {
					e.printStackTrace();
				}
           	    break;
            	            	
           case 8:
            	
        	    System.out.println("enter month(MM): ");
            	int month=sc.nextInt();
            	System.out.println("enter year(YYYY): ");
           	    int year=sc.nextInt();
           	
            	try {
			  		bdao.print_payments_by_month(month,year);
				 } 
           	    catch (SQLException e) {
			  		e.printStackTrace();
				}
           	
            	break;
            	
           case 9: 	
            	
        	   try {
					vdao.top_paid_vendor();
				} 
               catch (SQLException e) {
					e.printStackTrace();
				}
           	break;
           	
            	
           case 10:
            	
        	   try {
					vdao.print_balances_to_vendors();
				}
            	catch (SQLException e) {
					e.printStackTrace();
				}
            	break;

            	
           case 11:
            	
        	   System.out.println("enter the starting date(dd-MM-yyyy): ");
             	try{
           	    	String startDate=sc.nextLine();
           	        System.out.println("enter the ending date(dd-MM-yyyy): ");
           	        String endDate=sc.nextLine();
           	
           	        bdao.print_bills_in_range(startDate,endDate);
           	    }
             	catch(Exception e)
             	{
           	    	System.out.println("invalid date format");
           	    }
           	
             	break;
            	
            	
           case 12:
            	
            	try {
					bdao.delete_paid_bills();
				} 
            	catch (SQLException e) {
					e.printStackTrace();
				}
            	break;
            	
            case 13:
            	a=false;
            	System.out.println("thank you!");
            	break;
            	
            default:
            	System.out.println("Invalid choice.Please enter a correct choice.");
            		
            }
		}
	}
}	

