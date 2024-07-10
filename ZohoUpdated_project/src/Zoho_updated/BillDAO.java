package Zoho_updated;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BillDAO {

	
	public void add_bills(int v_id,double amt,double amtpaid,String date) throws SQLException
	{
		
		Connection con = DBconnect.getConnection();
        Statement st=con.createStatement();
	    
        String s = "insert into bills(vendor_id,amt,amtpaid,bill_date) values ("+v_id+","+amt+","+amtpaid+", str_to_date('"+date+"', '%d-%m-%Y'))";

        st.execute(s);

        System.out.println("Bill added successfully!");
	    con.close();
		
	}
	
	public void print_all_bills() throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="SELECT v.vendor_name,v.bank_acct_no,b.bill_no,b.vendor_id,b.amt,b.amtpaid,b.bill_date"
				+" FROM vendors v LEFT JOIN bills b ON v.vendor_id=b.vendor_id";
		
		ResultSet rs=st.executeQuery(s);
		
		
		while(rs.next()) {
			
			String name=rs.getString("vendor_name");
			String bank_acc=rs.getString("bank_acct_no");
			int b_no=rs.getInt("bill_no");
			int v_id=rs.getInt("vendor_id");
			double amt=rs.getDouble("amt");
			double amtpaid=rs.getDouble("amtpaid");
			Date date=rs.getDate("bill_date");
			
			System.out.println(name+"   acc.no: "+bank_acc+"   bill_no: "+b_no+"   vendor_id: "+v_id+"   amount: "+amt+"   amount_paid: "+amtpaid+"   "+date);
		}
		
		System.out.println("-------------------------------------------------");
	}
	
	public void pay_balance_to_vendors(int bill_no,double balance) throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="UPDATE bills SET amtpaid=amtpaid+"+balance+" WHERE bill_no="+bill_no+" AND amtpaid+"+balance+"<=amt";
		st.execute(s);
		
		System.out.println("amount paid successfully");
		
	}
	
	public void print_bills_in_range(String start,String end) throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="SELECT v.vendor_name,v.bank_acct_no,b.bill_no,b.vendor_id,b.amt,b.amtpaid,b.bill_date"
		+"FROM vendors v"
		+" LEFT JOIN bills b ON v.vendor_id=b.vendor_id"
		+" WHERE b.bill_date BETWEEN str_to_date('"+start+"','%d-%m-%Y') AND str_to_date('"+end+"','%d-%m-%Y');";
		
		ResultSet rs=st.executeQuery(s);
		
		while(rs.next())
		{
			String name=rs.getString("vendor_name");
			String bank_acc=rs.getString("bank_acct_no");
			int b_no=rs.getInt("bill_no");
			int v_id=rs.getInt("vendor_id");
			double amt=rs.getDouble("amt");
			double amtpaid=rs.getDouble("amtpaid");
			Date date=rs.getDate("bill_date");
			
			System.out.println(name+"   acc.no: "+bank_acc+"   bill_no: "+b_no+"   vendor_id: "+v_id+"   amount: "+amt+"   amount_paid: "+amtpaid+"   "+date);
		}
		
		System.out.println("bills within a range printed successfully");
		
	}
	
	
	public void print_payments_by_month(int month,int year) throws SQLException
	{
		
   	    Connection con = DBconnect.getConnection();		        
        String sql = "SELECT v.vendor_name,v.vendor_id,b.bill_no, b.amt, b.amtpaid, b.bill_date " +
		                     "FROM vendors v " +
		                     "JOIN bills b ON v.vendor_id = b.vendor_id " +
		                     "WHERE MONTH(b.bill_date) = ? AND YEAR(b.bill_date) = ?;";
		        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, month);
        pstmt.setInt(2, year);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
        	String v_name = rs.getString("vendor_name");
        	int v_id=rs.getInt("vendor_id");
	        int b_no = rs.getInt("bill_no");
	        double amt = rs.getDouble("amt");
	        double amtpaid = rs.getDouble("amtpaid");
	        Date b_date = rs.getDate("bill_date");
	        
	        System.out.println(v_name + "   vendor_id: "+v_id+"   bill_no: "+b_no+"   amount: "+amt+"   amount_paid: "+amtpaid+"   "+b_date);
	        }

        System.out.println("Payments for the specified month and year are printed.");

       
		
	}
	
	public void delete_paid_bills() throws SQLException
	{
		Connection con=DBconnect.getConnection();
		Statement st=con.createStatement();
		
		String s="DELETE FROM bills WHERE amtpaid=amt";
		
		st.execute(s);
		
	
		System.out.println("bills paid are deleted! ");
		
	}		

}
	
