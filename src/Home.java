import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JFrame implements ActionListener{

	JPanel north,center;
	JButton ibutton,ubutton,dbutton,vbutton;
	JLabel header;
	
	public Home() {
		//header
		header = new JLabel("Home Page");
		header.setFont(new Font("SansSerif", Font.BOLD, 35));
		header.setForeground(Color.WHITE);
		
		//north panel
		north = new JPanel();
		north.add(header);
		north.setBackground(Color.DARK_GRAY);
		
		//buttons
		ibutton = new JButton("Insert");
		vbutton = new JButton("View");
		ubutton = new JButton("Update");
		dbutton = new JButton("Delete");
		
		ibutton.setPreferredSize(new Dimension(80,30));
		vbutton.setPreferredSize(new Dimension(80,30));
		ubutton.setPreferredSize(new Dimension(80,30));
		dbutton.setPreferredSize(new Dimension(80,30));
		
		ibutton.addActionListener(this);
		vbutton.addActionListener(this);
		ubutton.addActionListener(this);
		dbutton.addActionListener(this);
		
		//center panel
		center = new JPanel(new FlowLayout(FlowLayout.CENTER,50,150));
		center.add(ibutton);
		center.add(vbutton);
		center.add(ubutton);
		center.add(dbutton);
		center.setBackground(Color.DARK_GRAY);
		
		add(north,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		//getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setVisible(true);
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("PT Pudding");
		
		  
	}
	
	public static void main(String[] args) {
		new Home();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ibutton) {
			dispose();
			Insert iwindow = new Insert();
		}
		
		if (e.getSource() == vbutton) {
			dispose();
			View vwindow = new View();
		}
		
		if (e.getSource() == ubutton) {
			dispose();
			Update uwindow = new Update();
		}
		
		if (e.getSource() == dbutton) {
			dispose();
			Delete dwindow = new Delete();
		}
	}

}
