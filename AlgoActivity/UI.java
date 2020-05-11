package AlgoActivity;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Container;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

public class UI extends JFrame {

	private static int matrixSize;
	private static int lastMatrixSize;
	private static int myMatrix[][];
	private static int result;
	private static int iterateCount = 0;
	private static int page = 0;
	private static int row;
	private static int col;
	static final int INF = 99999;
	private static String collection[][][];

	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel lblMatrixSize;
	private JLabel lblpageCount;
	private JLabel lbliterationCount;
	private JLabel lbliteration;
	private JButton btnShowMatrix;
	private JButton btnFloydWarshall;
	private JButton btnNext;
	private JButton btnBack;
	private JTextField MatrixSizetextField;
	private JTextField inputField[][];
	private JLayeredPane layeredPane;
	private JTable tableInput;
	private JScrollPane scrollPane;
	private JButton btnHome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchPanels(JPanel panel) {

		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));

		panel1 = new JPanel();
		panel1.setBackground(new Color(255, 204, 153));
		layeredPane.add(panel1, "name_153593036284900");
		panel1.setLayout(null);

		lblMatrixSize = new JLabel("MATRIX SIZE");
		lblMatrixSize.setFont(new Font("Goudy Old Style", Font.PLAIN, 13));
		lblMatrixSize.setBounds(105, 118, 93, 14);
		panel1.add(lblMatrixSize);

		MatrixSizetextField = new JTextField();
		MatrixSizetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					matrixSize = Integer.parseInt(MatrixSizetextField.getText().trim());
					if (matrixSize < 3 || matrixSize > 5) {
						JOptionPane.showMessageDialog(null, "MINIMUM (3) AND MAXIMUM (5)", null,
								JOptionPane.OK_CANCEL_OPTION);
					} else {
						lastMatrixSize = matrixSize;
						row = lastMatrixSize;
						col = lastMatrixSize;
						iterateCount = lastMatrixSize;
						myMatrix = new int[lastMatrixSize][lastMatrixSize];
						setElements(myMatrix, "Fill the  Matrix");

					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "IT SHOULD BE POSITIVE INTEGERS", null,
							JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});
		MatrixSizetextField.setBounds(188, 115, 96, 20);
		panel1.add(MatrixSizetextField);
		MatrixSizetextField.setColumns(10);

		JLabel lblFloydwarshallAlgorithm = new JLabel("FLOYD-WARSHALL ALGORITHM");
		lblFloydwarshallAlgorithm.setFont(new Font("Papyrus", Font.BOLD, 13));
		lblFloydwarshallAlgorithm.setBounds(72, 41, 307, 32);
		panel1.add(lblFloydwarshallAlgorithm);

		panel2 = new JPanel();
		panel2.setBackground(new Color(255, 204, 153));
		layeredPane.add(panel2, "name_153593069475500");
		panel2.setLayout(null);

		btnShowMatrix = new JButton("SHOW MATRIX");
		btnShowMatrix.setFont(new Font("Papyrus", Font.BOLD, 14));
		btnShowMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showMatrix(myMatrix, "Your Matrix");
			}
		});
		btnShowMatrix.setBounds(63, 51, 303, 61);
		panel2.add(btnShowMatrix);

		btnFloydWarshall = new JButton("START MST");
		btnFloydWarshall.setFont(new Font("Papyrus", Font.BOLD, 14));
		btnFloydWarshall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel3);
				floydWarshall(myMatrix, iterateCount);
			}
		});
		btnFloydWarshall.setBounds(63, 144, 303, 61);
		panel2.add(btnFloydWarshall);

		panel3 = new JPanel();
		panel3.setBackground(new Color(255, 204, 153));
		layeredPane.add(panel3, "name_154577204251200");
		panel3.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 282, 105);
		panel3.add(scrollPane);

		tableInput = new JTable();
		scrollPane.setViewportView(tableInput);

		btnNext = new JButton("NEXT");
		btnNext.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (page >= iterateCount - 1) {
						page = 0;
						lblpageCount.setText("Page: " + page + " / " + (iterateCount - 1));
						displayData(collection, page);
					} else {
						page++;
						lblpageCount.setText("Page: " + page + " / " + (iterateCount - 1));
						displayData(collection, page);

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNext.setBounds(160, 166, 89, 23);
		panel3.add(btnNext);

		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				page--;
				if (page < 0) {
					page = iterateCount - 1;
					lblpageCount.setText("Page: " + page + " / " + (iterateCount - 1));
					displayData(collection, page);

				} else {
					lblpageCount.setText("Page: " + page + " / " + (iterateCount - 1));
					displayData(collection, page);
				}
			}
		});
		btnBack.setBounds(61, 166, 89, 23);
		panel3.add(btnBack);

		lbliteration = new JLabel("Number of Iterations");
		lbliteration.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lbliteration.setBounds(302, 88, 113, 51);
		panel3.add(lbliteration);

		lbliterationCount = new JLabel("");
		lbliterationCount.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lbliterationCount.setBounds(354, 126, 48, 40);
		lbliterationCount.setText("" + Integer.toString(iterateCount));
		panel3.add(lbliterationCount);

		lblpageCount = new JLabel("New label");
		lblpageCount.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblpageCount.setBounds(112, 200, 89, 40);
		lblpageCount.setText("Iteration: " + page + " / " + (iterateCount));
		panel3.add(lblpageCount);

		btnHome = new JButton("HOME");
		btnHome.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel1);
				MatrixSizetextField.setText("");
			}
		});
		btnHome.setBounds(325, 11, 89, 23);
		panel3.add(btnHome);
	}

	protected void showMatrix(int[][] matrix, String title) {

		JPanel choosePanel[] = new JPanel[matrix.length + 1];
		choosePanel[0] = new JPanel();
		choosePanel[0].add(new JLabel(title));

		for (int i = 1; i <= matrix.length; i++) {
			choosePanel[i] = new JPanel();

			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i - 1][j] == 99999) {
					choosePanel[i].add(new JLabel("INF"));
				} else {
					choosePanel[i].add(new JLabel(Integer.toString(matrix[i - 1][j])));

				}

				if (j < matrix[0].length - 1) {
					choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
				}

			} // end col loop

		} // end row loop
		if (matrixSize == 0) {
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		} else {
			JOptionPane.showMessageDialog(null, choosePanel, null, JOptionPane.PLAIN_MESSAGE, null);
		}
	}

	protected void setElements(int[][] matrix, String title) {

		String temp;

		JPanel choosePanel[] = new JPanel[matrix.length + 2];
		choosePanel[0] = new JPanel();
		choosePanel[0].add(new Label(title));
		choosePanel[choosePanel.length - 1] = new JPanel();
		choosePanel[choosePanel.length - 1].add(new Label("consider space field as zeros & INF = 999999"));
		inputField = new JTextField[matrix.length][matrix[0].length];

		for (int i = 1; i <= matrix.length; i++) {
			choosePanel[i] = new JPanel();

			for (int j = 0; j < matrix[0].length; j++) {
				inputField[i - 1][j] = new JTextField(3);
				choosePanel[i].add(inputField[i - 1][j]);

				if (j < matrix[0].length - 1) {
					choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
				}

			}

		}

		result = JOptionPane.showConfirmDialog(null, choosePanel, null, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == 0) {

			checkTextField(inputField);

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {

					temp = inputField[i][j].getText().trim();
					temp = temp.toLowerCase().trim();
					if (temp.equals("inf") || temp.equals("99999")) {
						myMatrix[i][j] = INF;
					} else {
						myMatrix[i][j] = Integer.parseInt(inputField[i][j].getText());
					}
				}
			}
			FloydWarshall.floydwarshall(myMatrix);
			switchPanels(panel2);
		} // if state
	}

	private void checkTextField(JTextField[][] inputField2) {

		for (int i = 0; i < inputField.length; i++) {
			for (int j = 0; j < inputField[0].length; j++) {
				if (i == j || inputField[i][j].equals(null)) {
					inputField[i][j].setText("0");
				} // end loop
			}
		}
	}

	public void floydWarshall(int[][] graph, int iterateCount) {

		collection = new String[graph.length][graph.length][graph.length];

		int[][] temp = new int[graph.length][graph.length];

		int i, j, k;

		for (i = 0; i < graph.length; i++) {
			for (j = 0; j < graph[i].length; j++) {
				temp[i][j] = graph[i][j];

			}
		} // end loop

		for (k = 0; k < temp.length; k++) {
			for (i = 0; i < temp.length; i++) {
				for (j = 0; j < temp.length; j++) {

					if (temp[i][j] > temp[i][k] + temp[k][j]) {
						temp[i][j] = temp[i][k] + temp[k][j];
					} // end if state.

					if (temp[i][j] == INF) {
						collection[k][i][j] = "INF";
					} else {
						collection[k][i][j] = String.valueOf(temp[i][j]);
					}
				}

			}
		} // end loop

		// Displays Data to JTable
		displayData(collection, page);
	}

	public void displayData(String[][][] data, int page) {
		DefaultTableModel dataOutput = new DefaultTableModel();
		dataOutput.setRowCount(row);
		dataOutput.setColumnCount(col);

		for (int a = 0; a < col; a++) {
			for (int b = 0; b < row; b++) {
				dataOutput.setValueAt(data[page][a][b], a, b);
			}
		}
		tableInput.setModel(dataOutput);
		tableInput.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		lbliterationCount.setText("" + Integer.toString(iterateCount));
		lblpageCount.setText("Page: " + page + " / " + (iterateCount - 1));
	}
}
