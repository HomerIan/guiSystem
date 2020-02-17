package guiysystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Algorithm extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Algorithm frame = new Algorithm();
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
	public Algorithm() {

		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/guisystem?useSSL=false";

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 175, 537);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Selection Sor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection(url, userName, password);
					String sqlSelectionSort = "SELECT SUM(p.price*sd.quantity) AS 'Total Amount'"
							+ "	FROM salesdetail sd" + "	RIGHT JOIN product p" + "	ON sd.prodcode = p.prodcode"
							+ "	RIGHT JOIN customer_VIEW cv" + "	ON sd.transNo = cv.transNo"
							+ "	WHERE sd.transNo = cv.transNo" + "	GROUP BY sd.transNo;";

					PreparedStatement stmt = conn.prepareStatement(sqlSelectionSort);
					ResultSet rs = stmt.executeQuery(sqlSelectionSort);
					ArrayList<Integer> temptotalAmount = new ArrayList<Integer>();
					while (rs.next()) {
						temptotalAmount.add(rs.getInt("Total Amount"));// FETCHING DATA TO ARRAYLIST
					}
					Integer[] arr = new Integer[temptotalAmount.size()];// CONVERT ARRAYLIST TO ARRAY
					temptotalAmount.toArray(arr);
					SelectionSort.selectionSort(arr);// Selection Sort METHOD
					// selectionsorttable.setModel(DbUtils.resultSetToTableModel(rs));

					// closing database connection
					stmt.close();
					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setBounds(42, 64, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("String Matching");
		btnNewButton_1.setBounds(42, 123, 89, 23);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Knapsack");
		btnNewButton_2.setBounds(42, 198, 89, 23);
		panel_1.add(btnNewButton_2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(207, 51, 337, 382);
		panel.add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Total Amount of Purchase of a customer");
		lblNewLabel.setBounds(25, 11, 203, 27);
		panel_2.add(lblNewLabel);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 66, 297, 97);
		panel_2.add(textArea1);

		JButton btnSort = new JButton("SORT");
		btnSort.setBounds(116, 191, 89, 23);
		panel_2.add(btnSort);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(50, 64, 48, 14);
		panel_3.add(lblNewLabel_1);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pattern = textField.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection(url, userName, password);
					String sqlStringMatch = "SELECT custname FROM customer;";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sqlStringMatch);
					ArrayList<String> names = new ArrayList<String>();
					while (rs.next()) {
						names.add(rs.getString("custname"));// FETCHING DATA TO ARRAYLIST
					}
					String[] findname = new String[names.size()];// CONVERT ARRAYLIST TO ARRAY
					int pos = StringMatching.StringMatching(findname, pattern);

					
					if (pos != -1) {
						JOptionPane.showMessageDialog(null, "FOUND");
					} else {
						JOptionPane.showMessageDialog(null, "NOT FOUND");
					}
					// closing database connection
					stmt.close();
					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		textField.setBounds(108, 61, 96, 20);
		panel_3.add(textField);
		textField.setColumns(10);

		JLabel lblStringresult = new JLabel("stringresult");
		lblStringresult.setBounds(134, 159, 48, 14);
		panel_3.add(lblStringresult);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
	}
}
