import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Update extends JFrame implements ActionListener{
	
	dbConnection data = new dbConnection();
	JLabel header,kode,nama,harga,stok;
	JPanel north,center,south;
	JTextField kfield,nfield,hfield,sfield;
	JButton upbutton,back;
	ResultSet rset;

	public Update() {
		//header
				header = new JLabel("Update Menu");
				header.setFont(new Font("SansSerif", Font.BOLD, 35));
				header.setForeground(Color.WHITE);
						
				//north panel
				north = new JPanel();
				north.add(header);
				north.setBackground(Color.DARK_GRAY);
				
				
				//labels
				kode = new JLabel("Input Kode Menu [PD-XXX]");
				nama = new JLabel("Update Nama");
				harga = new JLabel("Update Harga ");
				stok = new JLabel("Update Stok");
				kode.setForeground(Color.WHITE);
				nama.setForeground(Color.WHITE);
				harga.setForeground(Color.WHITE);
				stok.setForeground(Color.WHITE);
				
				//textfields
				kfield = new JTextField();
				nfield = new JTextField();
				hfield = new JTextField();
				sfield = new JTextField();

				//center panel
				center = new JPanel();
				center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
				center.setBorder(new EmptyBorder(new Insets(60, 300, 190, 300)));
				center.setBackground(Color.DARK_GRAY);

				center.add(kode);		
				center.add(kfield);
				center.add(Box.createRigidArea(new Dimension(0,30)));
				center.add(nama);		
				center.add(nfield);
				center.add(harga);		
				center.add(hfield);
				center.add(stok);		
				center.add(sfield);
				
				//south panel
				south = new JPanel();
				south.setLayout(new BoxLayout(south,BoxLayout.Y_AXIS));
				south.setBackground(Color.DARK_GRAY);
				
				south.setBorder(new EmptyBorder(new Insets(10, 420, 50, 420)));
				upbutton = new JButton("Update Menu");
				back = new JButton("Back to home");
				
				upbutton.addActionListener(this);
				back.addActionListener(this);
				
				south.add(upbutton);
				south.add(Box.createRigidArea(new Dimension(0,10)));
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
		if(e.getSource()==upbutton) {
			
			String kode = kfield.getText();
			String nama = nfield.getText();
			int harga = Integer.parseInt(hfield.getText());
			int stok = Integer.parseInt(sfield.getText());
			
			
			if(kode.startsWith("PD-")) {
				data.update(kode, nama, harga, stok);
				rset = data.findKode(kode);
				
				try {
					if(!(rset.next())) {
						JOptionPane.showMessageDialog(center, "Kode tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(center, "Update menu sukses dilakukan", "Success", JOptionPane.PLAIN_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}else {
				JOptionPane.showMessageDialog(center, "Kode harus PD-XXX", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			//clear fields
			
			kfield.setText("");
			nfield.setText("");
			hfield.setText("");
			sfield.setText("");
			
		}
		
		if(e.getSource()==back) {
			dispose();
			Home hwindow = new Home();
					
		}
	}
}
