package AlgoActivity;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class floydwarshallAlgorithmActivityUI extends JFrame {
	
	private static int matrixSize;
	private static int lastMatrixSize;
	
	private JPanel contentPane;
	private static int result;
	static final int INF = 99999;
	private JTextField MatrixSizetextField;
	private static JTextField inputField [][]; 
	private static int myMatrix [][];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					floydwarshallAlgorithmActivityUI frame = new floydwarshallAlgorithmActivityUI();
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
	public floydwarshallAlgorithmActivityUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		contentPane.add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMatrixSize = new JLabel("matrix size");
		lblMatrixSize.setBounds(111, 119, 53, 14);
		contentPane.add(lblMatrixSize);
		
		MatrixSizetextField = new JTextField(10);
		MatrixSizetextField.setBounds(174, 116, 96, 20);
		contentPane.add(MatrixSizetextField);
		MatrixSizetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {					
					matrixSize = Integer.parseInt(MatrixSizetextField.getText().trim());
					if(matrixSize < 3 || matrixSize > 5) {
						JOptionPane.showMessageDialog(null,"MINIMUM (3) AND MAXIMUM (5)"
								,null, JOptionPane.OK_CANCEL_OPTION);
					}else {
						lastMatrixSize = matrixSize;
						
						myMatrix = new int[lastMatrixSize][lastMatrixSize];
						setElements(myMatrix,"Fill the  Matrix");
						
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"IT SHOULD BE POSITIVE INTEGERS"
							,null, JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});
		MatrixSizetextField.setColumns(10);
	}

	protected void setElements(int[][] matrix, String title) {
		     
	     String temp;
		
		 JPanel choosePanel[] = new JPanel[matrix.length+2];
	     choosePanel[0] = new JPanel();
	     choosePanel[0].add(new Label(title));
	     choosePanel[choosePanel.length-1] = new JPanel();
	     choosePanel[choosePanel.length-1].add(new Label("consider space field as zeros & INF = 999999"));
	     inputField  = new JTextField [matrix.length][matrix[0].length];
	     
	     for(int i = 1; i <= matrix.length; i++)
	       {
	           choosePanel[i] = new JPanel();
	           
	           
	           for(int j = 0; j < matrix[0].length; j++)
	           {
	        	   inputField [i-1][j] = new JTextField(3);
	               choosePanel[i].add(inputField [i-1][j]);
	               
	               if(j < matrix[0].length -1)
	               {
	               choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
	               }
	               
	           }
	           
	       }
	     
	     result = JOptionPane.showConfirmDialog(null, choosePanel, 
	               null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
	     
	    if(result == 0) {
	    	
	    	checkTextField(inputField);
	    	
	    	for(int i = 0; i < matrix.length; i++) {
	    		for(int j =  0; j < matrix[0].length; j++) {
	    			
	    			 temp = inputField[i][j].getText();
	    			 temp = temp.toLowerCase();
	    			 if(temp.equals("inf") || temp.equals("99999")) {
	    				 myMatrix[i][j] = INF;
	    			 }else {
	    				 myMatrix[i][j] = Integer.parseInt(inputField[i][j].getText());
	    			 }
	    		}
	    	}  	
	    	FloydWarshall.floydwarshall(myMatrix);
	    }//if state
	}
	private void printMatrix(int[][] matrix) {
		
		for(int i = 0; i < matrix.length; i++) {
    		for(int j =  0; j < matrix[0].length; j++) {
    				
    				if(myMatrix[i][j]>= 99999) {
    					System.out.print("INF ");
    				}else {
        				System.out.print(myMatrix[i][j]+ " ");
    				}
    			}
    		System.out.println();
    		}
	}

	private void checkTextField(JTextField[][] inputField) {
		
		for(int i = 0; i < inputField.length;i++) {
			for(int j = 0; j < inputField[0].length;j++) {	
				if(i==j) {
					inputField[i][j].setText("0");
				}//end loop
			}
		}
		
	}
}
