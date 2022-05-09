import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {

	Connection connect;
	//Statement state;
	PreparedStatement pstate;
	ResultSet rset;
	
	public dbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptpudding","root","");
			//state = connect.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String kode, String nama, int harga, int stok) {
		
		try {
			pstate = connect.prepareStatement("insert into menu values (?,?,?,?);");
			pstate.setString(1, kode);
			pstate.setString(2, nama);
			pstate.setInt(3, harga);
			pstate.setInt(4, stok);
			pstate.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return
	 */
	public ResultSet viewall() {
		try {
			pstate = connect.prepareStatement("select * from menu");
			rset = pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rset;
	}
	
	public ResultSet findKode(String kode) {
		try {
			pstate = connect.prepareStatement("select * from menu where kode = ?");
			pstate.setString(1, kode);
			rset = pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rset;
	}
	
	public void update(String kode, String nama, int harga, int stok) {
		try {
			pstate = connect.prepareStatement("update menu set nama = ?, harga = ?, stok = ? where kode = ?");
			pstate.setString(1, nama);
			pstate.setInt(2, harga);
			pstate.setInt(3, stok);
			pstate.setString(4, kode);
			pstate.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delete(String kode) {
		try {
			pstate = connect.prepareStatement("delete from menu where kode = ?");
			pstate.setString(1, kode);
			pstate.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
