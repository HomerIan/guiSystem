package guiysystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class CustomerReceipt extends JFrame {

	private JPanel contentPane;
	private JTextField searchCustNametextField;
	private JTable table;
	private JLabel custNameResultLabel;
	private JLabel totalAmountLabel;
	private JLabel salesDateLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReceipt frame = new CustomerReceipt();
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
	public CustomerReceipt() {
		
		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/guisystem?useSSL=false";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel searchCustNameLabel = new JLabel("Search Name:");
		searchCustNameLabel.setBounds(28, 23, 48, 14);
		panel.add(searchCustNameLabel);
		
		searchCustNametextField = new JTextField();
		searchCustNametextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullName = searchCustNametextField.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url, userName, password);
					String sqlgetCustname ="SELECT * FROM customer WHERE custname = '"+searchCustNametextField.getText()+"'";

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sqlgetCustname);
					//TODO IF user input mismatch put JoptionPane and clear the table list
					while(rs.next()) {
						if(fullName.toUpperCase().equals(rs.getString("custname").toUpperCase())) {
							custNameResultLabel.setText(rs.getString("custname"));
							JOptionPane.showMessageDialog(null, "FOUND SUCCESSFULLY");

							String sqlgetOrder = 
									"SELECT cv.transNo,p.description, p.price,sd.quantity, SUM(p.price*sd.quantity) AS 'amount'\r\n" + 
									"	FROM customer_VIEW cv\r\n" + 
									"	RIGHT JOIN salesdetail sd\r\n" + 
									"	ON cv.transNo = sd.transNo\r\n" + 
									"	RIGHT JOIN product p\r\n" + 
									"	ON sd.prodcode = p.prodcode\r\n" + 
									"	WHERE cv.custName = '"+rs.getString("custname")+"'\n" + 
									"	GROUP BY cv.transNo,p.description, p.price,sd.quantity;";
							
							PreparedStatement pst = conn.prepareStatement(sqlgetOrder);
							ResultSet rs1 = pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs1));
							
							String sqlgetTotalAmount = "SELECT sd.transNo,SUM(p.price*sd.quantity) AS 'Total Amount'\r\n" + 
									"	FROM salesdetail sd\r\n" + 
									"	RIGHT JOIN product p\r\n" + 
									"	ON sd.prodcode = p.prodcode\r\n" + 
									"	RIGHT JOIN customer_VIEW cv\r\n" + 
									"	ON sd.transNo = cv.transNo\r\n" + 
									"	WHERE cv.custName = '"+rs.getString("custname")+"'\n" + 
									"	GROUP BY sd.transNo;\r\n";
							
							Statement stmt1 = conn.createStatement();
							ResultSet rs2 = stmt1.executeQuery(sqlgetTotalAmount);
							
							while(rs2.next()) {
								totalAmountLabel.setText(rs2.getString("Total Amount"));
							}		
							String sqlgetsalesDate ="SELECT c.custNo, s.salesDate \r\n" + 
									"	FROM sales s" + 
									"	RIGHT JOIN customer c" + 
									"	ON s.custNo = c.custNo" + 
									"	WHERE custName = '"+fullName.toLowerCase()+"'";
							
							PreparedStatement pst1 = conn.prepareStatement(sqlgetsalesDate);
							ResultSet rs3 = pst1.executeQuery();
							while(rs3.next()) {
								salesDateLabel.setText(rs3.getString("salesDate"));
							}
							
						}else if(!fullName.toUpperCase().equals(rs.getString("custname").toUpperCase())){
							table.removeAll();
							JOptionPane.showMessageDialog(null, "NOT FOUND");
							
						}
					}
					stmt.close();
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		searchCustNametextField.setBounds(86, 20, 112, 20);
		panel.add(searchCustNametextField);
		
		
		JLabel custNameLabel = new JLabel("Customer Name");
		custNameLabel.setBounds(28, 70, 89, 14);
		panel.add(custNameLabel);
		//FILL customer NAME
		custNameResultLabel = new JLabel("New label");
		custNameResultLabel.setBounds(115, 70, 67, 14);
		panel.add(custNameResultLabel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items", "Description", "Price", "Amount"
			}
		));
		table.setBounds(10, 119, 404, 266);
		panel.add(table);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Algorithm algo = new Algorithm();
				algo.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(156, 425, 89, 23);
		panel.add(btnNewButton);
		
		
		JButton Logoutbtn = new JButton("LOGOUT");
		Logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OnlineBookStore obk = new OnlineBookStore();
				obk.setVisible(true);
				dispose();
			}
		});
		
		Logoutbtn.setBounds(308, 19, 89, 23);
		panel.add(Logoutbtn);
		
		JLabel lblNewLabel = new JLabel("total Amount");
		lblNewLabel.setBounds(271, 397, 48, 14);
		panel.add(lblNewLabel);
		
		totalAmountLabel = new JLabel("New label");
		totalAmountLabel .setBounds(329, 397, 48, 14);
		panel.add(totalAmountLabel );
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(241, 69, 56, 16);
		panel.add(lblDate);
		
		salesDateLabel = new JLabel("New label");
		salesDateLabel.setBounds(329, 69, 56, 16);
		panel.add(salesDateLabel);

	}
}
