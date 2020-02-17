package guiSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;


public class OnlineBookstore extends JFrame {

	private JPanel contentPane;
	private JTextField userNametextField;
	private JButton loginBtn; 
	private JPasswordField passwWordField;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					OnlineBookstore frame = new OnlineBookstore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OnlineBookstore() {
		
		String userName = "root";
		String password = "YOUR_ROOT_PASSWORD";
		String url = "jdbc:mysql://localhost:3306/guisystem?useSSL=false";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TITLE");
		lblNewLabel.setBounds(174, 62, 48, 14);
		panel.add(lblNewLabel);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = passwWordField.getText();
				String tempUserName = userNametextField.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url, userName, password);
					String sql ="SELECT * FROM account WHERE userName ='"+userNametextField.getText()+"'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
				while(rs.next()) {
					if(pass.equals(rs.getString("passWord")) && userNametextField.getText().equals(rs.getString("userName"))) {
						CustomerReceipt cr = new CustomerReceipt();
						cr.setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "INCORRECT USER/PASSWORD");
					}
					
				}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		loginBtn.setBounds(157, 180, 89, 23);
		panel.add(loginBtn);
		
		passwWordField = new JPasswordField();
		passwWordField.setBounds(157, 146, 96, 23);
		panel.add(passwWordField);
		
		userNametextField = new JTextField();
		userNametextField.setBounds(157, 114, 96, 21);
		panel.add(userNametextField);
		userNametextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(54, 117, 48, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(54, 163, 48, 14);
		panel.add(lblNewLabel_2);
		
		
	}
}
