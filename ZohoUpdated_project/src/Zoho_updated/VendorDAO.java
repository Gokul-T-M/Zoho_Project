package Zoho_updated;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VendorDAO {

	public void displayVendorInfo() throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement stmt=con.createStatement();
		
		String s="SELECT * FROM vendors";
		
		ResultSet rs=stmt.executeQuery(s);
		
		while(rs.next())
		{
			int v_id=rs.getInt("vendor_id");
			String v_name=rs.getString("vendor_name");
			String bank_accno=rs.getString("bank_acct_no");
			
			System.out.println(v_id+"   "+v_name+"   "+bank_accno);
		}
		
		System.out.println("--------------------------------------------------");
		
	}
	
	public void add_vendor(String str,String bank_acc) throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement stmt=con.createStatement();
		
		String s="INSERT INTO VENDORS(vendor_name,bank_acct_no) VALUES('"+str+"','"+bank_acc+"')";
		
		stmt.execute(s);
		
	}
	
	public void print_balances_to_vendors() throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement stmt=con.createStatement();
		
		String s="SELECT v.vendor_id,v.vendor_name,v.bank_acct_no,COALESCE(SUM(b.amt)) as total_amt,COALESCE(SUM(b.amtpaid)) as total_amt_paid,COALESCE(SUM(amt-amt_paid)) as total_balance"
		+"FROM vendors v LEFT JOIN bills b ON v.vendor_id=b.vendor_id"
		+ " GROUP BY v.vendor_id"
		+"ORDER BY balance DESC";
		
	    
	    ResultSet rs=stmt.executeQuery(s);
	    
	    while(rs.next())
	    {
	    	int v_id=rs.getInt("vendor_id");
	    	String v_name=rs.getString("vendor_name");
	    	String bank_acc=rs.getString("bank_acct_no");
	    	Double tot_amt=rs.getDouble("total_amt");
	    	Double tot_paid_amt=rs.getDouble("total_amt_paid");
	    	Double tot_balance=rs.getDouble("total_balance");
	    	
	    	System.out.println(v_name+"  vendor_id: "+v_id+"  bank_acc: "+bank_acc+"  tot_amount: "+tot_amt+"  tot_amt_paid: "+tot_paid_amt+"  tot_balance: "+tot_balance);
	    }

	    
	    System.out.println("balance printed");
		
	}
	
	public void print_bills_of_a_vendor(int v_id) throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="SELECT v.vendor_name,v.vendor_id,b.bill_no,b.amt,b.amtpaid,b.bill_date,b.amt-b.amtpaid as balance"
				+ " FROM vendors v LEFT JOIN bills b ON v.vendor_id=b.vendor_id "
				+ "WHERE v.vendor_id="+v_id+";";
		
		
		ResultSet rs=st.executeQuery(s);
		
		while(rs.next())
		{
			String v_name=rs.getString("vendor_name");
			int ven_id=rs.getInt("v_id");
			int b_no=rs.getInt("bill_no");
			double amt=rs.getDouble("amt");
			double amtpaid=rs.getDouble("amtpaid");
			double bal=rs.getDouble("balance");
			Date b_date=rs.getDate("bill_date");
			
			System.out.println(v_name+"  vedor_id: "+ven_id+"  bill_no: "+b_no+"  amt: "+amt+"  amt_paid: "+amtpaid+"  balance: "+bal+"  "+b_date);
			
		}
		
		System.out.println("bills of a vendor are printed");
	}
	
	public void top_paid_vendor() throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="SELECT v.vendor_id,v.vendor_name,COALESCE(SUM(b.amtpaid)) AS total_amt_paid FROM vendors v"
				+ " LEFT JOIN bills b ON v.vendor_id=b.vendor_id "
				+ "GROUP BY v.vendor_id "
				+ "ORDER BY total_amt_paid DESC limit 1;";
		
		ResultSet rs=st.executeQuery(s);
		
		if(rs.next())
		{
			int v_id=rs.getInt("vendor_id");
			String v_name=rs.getString("vendor_name");
			double amtpaid=rs.getDouble("total_amt_paid");
			
			System.out.println(v_name+"   vendor_id: "+v_id+"   amt_paid: "+amtpaid);
		}
		else {
			System.out.println("No top paid vendors found");
		}
		
	}
	
	public void delete_a_vendor(int v_id) throws SQLException//deleting a vendor accompanies with deleting its bills because vendors associated records due to vendor_id being foreign key are present in bills table. 
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
	    
		String deleteBills = "DELETE FROM bills WHERE vendor_id = "+v_id+";";
        st.execute(deleteBills);
	        
	    
	     String deleteVendor = "DELETE FROM vendors WHERE vendor_id = "+v_id+";";
         int id=st.executeUpdate(deleteVendor);
         

         if(id==1)
         {
        	 System.out.println("vendor deleted successfully!"); 
       	 }
         else {
        	 System.out.println("vendor with specified id not found");
         }
		
		
	}
	
}
