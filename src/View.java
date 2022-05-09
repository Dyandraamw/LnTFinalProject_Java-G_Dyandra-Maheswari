import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame implements ActionListener{
	dbConnection data = new dbConnection();
	ResultSet rset;
	Vector<Vector<Object>> masterlist = new Vector<>();
	JLabel header;
	JPanel north,south;
	JScrollPane center;
	JButton back;
	JTable table;
	
	public View() {
		//header
		header = new JLabel("View Menu");
		header.setFont(new Font("SansSerif", Font.BOLD, 35));
		header.setForeground(Color.WHITE);
						
		//north panel
		north = new JPanel();
		north.add(header);
		north.setBackground(Color.DARK_GRAY);
		
		rset = data.viewall();
		
		
		//data to vector
		try {
			while(rset.next()) {
				Vector<Object> datlist = new Vector<Object>();
				datlist.add(rset.getObject(1));
				datlist.add(rset.getObject(2));
				datlist.add("Rp "+rset.getObject(3));
				datlist.add(rset.getObject(4));
				masterlist.add(datlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//table title
		Vector<String> title = new Vector<String>();
		title.add("Kode");
		title.add("Nama");
		title.add("Harga");
		title.add("Stok");
		
		//table
		DefaultTableModel tmodel = new DefaultTableModel(masterlist,title);
		table = new JTable(tmodel);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		
		//center panel
		center = new JScrollPane(table);
		center.setBorder(new EmptyBorder(new Insets(10, 30, 50, 30)));
		center.setBackground(Color.DARK_GRAY);
		
		//south panel
		south = new JPanel();
		south.setBackground(Color.DARK_GRAY);
				
		//south.setBorder(new EmptyBorder(new Insets(10, 420, 50, 420)));
		
		back = new JButton("Back to home");
		back.addActionListener(this);
		south.add(back);
				
				add(south,BorderLayout.SOUTH);
		
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		
		setResizable(false);
		setVisible(true);
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("PT Pudding");
	}

	public static void main(String[] args) {
		new View();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			dispose();
			Home hwindow = new Home();
					
		}
		
	}
}
