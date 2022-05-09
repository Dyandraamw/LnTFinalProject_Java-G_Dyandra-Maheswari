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

public class Delete extends JFrame implements ActionListener {
	dbConnection data = new dbConnection();
	JLabel header,kode,nama,harga,stok;
	JPanel north,center,south;
	JTextField kfield;
	JButton delbutton,back;
	ResultSet rset;
	
	public Delete() {
		//header
				header = new JLabel("Delete Menu");
				header.setFont(new Font("SansSerif", Font.BOLD, 35));
				header.setForeground(Color.WHITE);
						
				//north panel
				north = new JPanel();
				north.add(header);
				north.setBackground(Color.DARK_GRAY);
				
				
				//labels
				kode = new JLabel("Kode [PD-XXX]");
				kode.setForeground(Color.WHITE);

				//textfields
				kfield = new JTextField();

				//center panel
				center = new JPanel();
				center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
				center.setBorder(new EmptyBorder(new Insets(60, 300, 190, 300)));
				center.setBackground(Color.DARK_GRAY);

				center.add(kode);		
				center.add(kfield);		

				//south panel
				south = new JPanel();
				south.setLayout(new BoxLayout(south,BoxLayout.Y_AXIS));
				south.setBackground(Color.DARK_GRAY);
				
				south.setBorder(new EmptyBorder(new Insets(10, 420, 50, 420)));
				delbutton = new JButton("Delete Menu");
				back = new JButton("Back to home");
				
				delbutton.addActionListener(this);
				back.addActionListener(this);
				
				south.add(delbutton);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delbutton) {
			
			String kode = kfield.getText();
			
			if(kode.startsWith("PD-")) {
				
				rset = data.findKode(kode);
				
				try {
					if(!(rset.next())) {
						JOptionPane.showMessageDialog(center, "Kode tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						
						int ans=JOptionPane.showConfirmDialog(center,"Apakah anda yakin ingin menghapus menu dengan kode "+kode+"?", "Option", JOptionPane.YES_NO_OPTION);
						
						if (ans==0) {
							data.delete(kode);
							JOptionPane.showMessageDialog(center, "Menu berhasil dihapus", "Success", JOptionPane.PLAIN_MESSAGE);
						}

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
			
		}
		
		if(e.getSource()==back) {
			dispose();
			Home hwindow = new Home();
					
		}
		
	}

}
