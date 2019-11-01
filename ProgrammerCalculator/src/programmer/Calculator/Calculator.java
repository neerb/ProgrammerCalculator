package programmer.Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Calculator extends JFrame implements ActionListener
{	
	final JPanel upperPanel;
	final GridLayout upperLayout;
	
	final JPanel lowerPanel;
	final GridLayout lowerLayout;
	
	// Upper panel attributes
	final JLabel equationBuffer;
	final JLabel currentInput;
	
	final Font equationFont;
	final Font inputFont;
	
	// Base Conversion Buttons
	final LineBorder baseBorder;
	final EmptyBorder normalBaseBorder;
	final JButton hexButton;
	final JButton decimalButton;
	final JButton octButton;
	final JButton binaryButton;
	
	// Numeric button declared
	JButton[] numericButtonArray;
	final JButton zero;
	final JButton one;
	final JButton two;
	final JButton three;
	final JButton four;
	final JButton five;
	final JButton six;
	final JButton seven;
	final JButton eight;
	final JButton nine;
	final JButton hexA;
	final JButton hexB;
	final JButton hexC;
	final JButton hexD;
	final JButton hexE;
	final JButton hexF;
	
	// Operator buttons declared
	final JButton divide;
	final JButton multiply;
	final JButton subtract;
	final JButton add;
	final JButton equals;
	final JButton openParen;
	final JButton closeParen;
	
	// Special
	final JButton second;
	final JButton mod;
	final JButton plusMinus;
	
	// Clear buttons
	final JButton buttonCE;
	final JButton buttonC;
	final JButton deleteRecent;

	// unused buttons
	final JButton lsh;
	final JButton rsh;
	final JButton or;
	final JButton xor;
	final JButton not;
	final JButton and;
	final JButton decimal;
	
	private boolean operatorPushedLast = false;
	
	int width = 400;
	int height = 600;
	
	int totalParens = 0;
	
	
	public Calculator()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultLookAndFeelDecorated(true);
		this.setMinimumSize(new Dimension(width, height));
		
		this.setTitle("Programmer Calculator");
		
		//Define upper layout components
		upperPanel = new JPanel(); 
		upperLayout = new GridLayout(2, 1);
		JPanel tempUpperPanel1 = new JPanel();
		JPanel tempUpperPanel2 = new JPanel();
				
		upperPanel.setLayout(upperLayout);
		
		GridLayout tempUpperLayout1 = new GridLayout(2, 1);
		tempUpperLayout1.setHgap(10);
		tempUpperPanel1.setLayout(tempUpperLayout1);
		
		GridLayout tempUpperLayout2 = new GridLayout(4, 1);
		tempUpperLayout2.setVgap(5);
		tempUpperPanel2.setLayout(tempUpperLayout2);

		// Initialize upper panel components
		equationFont = new Font("Monospaced", Font.BOLD, 16);
		inputFont = new Font("Monospaced", Font.BOLD, 36);
		
		equationBuffer = new JLabel();
		equationBuffer.setFont(equationFont);
		equationBuffer.setBorder(new EmptyBorder(0, 0, 0, 10));
		equationBuffer.setHorizontalAlignment(SwingConstants.RIGHT);
		currentInput = new JLabel("0");
		currentInput.setFont(inputFont);
		currentInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		currentInput.setHorizontalAlignment(SwingConstants.RIGHT);
		
		baseBorder = new LineBorder(Color.BLUE, 3);
		normalBaseBorder = new EmptyBorder(0, 10, 0, 0);
		
		hexButton = new JButton("HEX   0");
		hexButton.setFont(equationFont);
		hexButton.setHorizontalAlignment(SwingConstants.LEFT);
		decimalButton = new JButton("DEC   0");
		decimalButton.setFont(equationFont);
		decimalButton.setHorizontalAlignment(SwingConstants.LEFT);
		octButton = new JButton("OCT   0");
		octButton.setFont(equationFont);
		octButton.setHorizontalAlignment(SwingConstants.LEFT);
		binaryButton = new JButton("BIN   0");
		binaryButton.setFont(equationFont);
		binaryButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		hexButton.setBorder(normalBaseBorder);
		decimalButton.setBorder(normalBaseBorder);
		octButton.setBorder(normalBaseBorder);
		binaryButton.setBorder(normalBaseBorder);

		// Add components to upper panel
		//upperPanel.add(new JLabel("Programmer"));
		tempUpperPanel1.add(equationBuffer);
		tempUpperPanel1.add(currentInput);
		
		tempUpperPanel2.add(hexButton);
		tempUpperPanel2.add(decimalButton);
		tempUpperPanel2.add(octButton);
		tempUpperPanel2.add(binaryButton);

		
		upperPanel.add(tempUpperPanel1);
		upperPanel.add(tempUpperPanel2);
		
		
		
		// Define lower layout components
		lowerPanel = new JPanel();
		lowerLayout = new GridLayout(6, 6);
		lowerLayout.setHgap(3);
		lowerLayout.setVgap(3);
		lowerPanel.setLayout(lowerLayout);
		
		// Main use buttons - numbers
		zero = new JButton("0");
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		hexA = new JButton("A");
		hexB = new JButton("B");
		hexC = new JButton("C");
		hexD = new JButton("D");
		hexE = new JButton("E");
		hexF = new JButton("F");
		
		//operator buttons
		divide = new JButton("÷");
		multiply = new JButton("X");
		subtract = new JButton("-");
		add = new JButton("+");
		equals = new JButton("=");
		openParen = new JButton("(");
		closeParen = new JButton(")");
		
		// Specials
		second = new JButton("2nd");
		mod = new JButton("Mod");
		plusMinus = new JButton("±");

		// Clear buttons
		buttonCE = new JButton("CE");
		buttonC = new JButton("C");
		deleteRecent = new JButton("\u232b");
		
		//Unused buttons initialization
		lsh= new JButton("Lsh");
		rsh = new JButton("Rsh");
		or = new JButton("Or");
		xor = new JButton("Xor");
		not = new JButton("Not");
		and = new JButton("And");
		decimal = new JButton(".");
		
		// Add components to lower panel
		lowerPanel.add(lsh);
		lowerPanel.add(rsh);
		lowerPanel.add(or);
		lowerPanel.add(xor);
		lowerPanel.add(not);
		lowerPanel.add(and);
		lowerPanel.add(second);
		lowerPanel.add(mod);
		lowerPanel.add(buttonCE);
		lowerPanel.add(buttonC);
		lowerPanel.add(deleteRecent);
		lowerPanel.add(divide);
		lowerPanel.add(hexA);
		lowerPanel.add(hexB);
		lowerPanel.add(seven);
		lowerPanel.add(eight);
		lowerPanel.add(nine);
		lowerPanel.add(multiply);
		lowerPanel.add(hexC);
		lowerPanel.add(hexD);
		lowerPanel.add(four);
		lowerPanel.add(five);
		lowerPanel.add(six);
		lowerPanel.add(subtract);
		lowerPanel.add(hexE);
		lowerPanel.add(hexF);
		lowerPanel.add(one);
		lowerPanel.add(two);
		lowerPanel.add(three);
		lowerPanel.add(add);
		lowerPanel.add(openParen);
		lowerPanel.add(closeParen);
		lowerPanel.add(plusMinus);
		lowerPanel.add(zero);
		lowerPanel.add(decimal);
		lowerPanel.add(equals);
		
		// Add Action listeners to buttons
		hexButton.addActionListener(this);
		decimalButton.addActionListener(this);
		octButton.addActionListener(this);
		binaryButton.addActionListener(this);
		
		second.addActionListener(this);
		mod.addActionListener(this);
		buttonCE.addActionListener(this);
		buttonC.addActionListener(this);
		deleteRecent.addActionListener(this);
		divide.addActionListener(this);
		hexA.addActionListener(this);
		hexB.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		multiply.addActionListener(this);
		hexC.addActionListener(this);
		hexD.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		subtract.addActionListener(this);
		hexE.addActionListener(this);
		hexF.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		add.addActionListener(this);
		openParen.addActionListener(this);
		closeParen.addActionListener(this);
		/* Not implemented
		plusMinus.addActionListener(this);
		*/
		zero.addActionListener(this);
		decimal.addActionListener(this);
		equals.addActionListener(this);

		
		// Unused buttons disabled
		plusMinus.setEnabled(false);
		decimal.setEnabled(false);
		
		
		// Add buttons to numeric array
		numericButtonArray = new JButton[16];
		numericButtonArray[0] = zero;
		numericButtonArray[1] = one;
		numericButtonArray[2] = two;
		numericButtonArray[3] = three;
		numericButtonArray[4] = four;
		numericButtonArray[5] = five;
		numericButtonArray[6] = six;
		numericButtonArray[7] = seven;
		numericButtonArray[8] = eight;
		numericButtonArray[9] = nine;
		numericButtonArray[10] = hexA;
		numericButtonArray[11] = hexB;
		numericButtonArray[12] = hexC;
		numericButtonArray[13] = hexD;
		numericButtonArray[14] = hexE;
		numericButtonArray[15] = hexF;

		
		this.setLayout(new GridLayout(2, 1));
		
		this.add(upperPanel, BorderLayout.NORTH);
		this.add(lowerPanel, BorderLayout.SOUTH);
		
		
		
		//setSize(width, height);		
		setVisible(true);
		
		// By default, start on decimal
		modifyButtonsEnabled(10, 15, false);
		modifyButtonsEnabled(0, 9, true);
		decimalButton.setBorder(baseBorder);
	}
	
	void modifyButtonsEnabled(int low, int high, boolean modifier)
	{
		if(high <= 16 && low >= 0)
		{
			for(int i = low; i <= high; i++)
			{
				numericButtonArray[i].setEnabled(modifier);
			}
		}
	}
	
	
	String numToHexString(long number)
	{		
		return Long.toHexString(number);
	}
	
	String numToDecimalString(long number)
	{		
		return Long.toString(number);
	}
	
	String numToOctalString(long number)
	{
		return Long.toOctalString(number);
	}
	
	String numToBinaryString(long number)
	{
		return Long.toBinaryString(number);
	}
	
	boolean containsHexValues(String str) 
	{
		return str.contains("A") || str.contains("B") ||
				str.contains("C") || str.contains("D") ||
				str.contains("E") || str.contains("F");
	}
	
	String convertStringHexToDec(String hexStr)
	{
		String digits = "0123456789ABCDEF";
		
		int val = 0;
		 
		for (int i = 0; i < hexStr.length(); i++)  
		{  
		    char c = hexStr.charAt(i);  
		    int d = digits.indexOf(c);  
		    val = 16 * val + d;  
		}  
		
		return val + "";  
	}
	
	String convertStringOctToDec(String octStr)
	{
		String digits = "012345678";
		
		int val = 0;
		 
		for (int i = 0; i < octStr.length(); i++)  
		{  
		    char c = octStr.charAt(i);  
		    int d = digits.indexOf(c);  
		    val = 8 * val + d;  
		}  
		
		return val + "";  
	}
	
	String convertStringBinaryToDec(String binStr)
	{
		String digits = "012345678";
		
		int val = 0;
		 
		for (int i = 0; i < binStr.length(); i++)  
		{  
		    char c = binStr.charAt(i);  
		    int d = digits.indexOf(c);  
		    val = 2 * val + d;  
		}  
		
		return val + "";  
	}
	
	
	void updateBaseValues()
	{
		String numString = currentInput.getText();
		
		if(containsHexValues(numString))
			numString = convertStringHexToDec(numString);
		
		long number = Long.parseLong(numString);
				
		hexButton.setText("HEX   " + numToHexString(number));
		decimalButton.setText("DEC   " + numToDecimalString(number));
		octButton.setText("OCT   " + numToOctalString(number));
		binaryButton.setText("BIN   " + numToBinaryString(number));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Check if it's a numeric button
		for(int i = 0; i < numericButtonArray.length; i++)
		{
			if(e.getSource().equals(numericButtonArray[i]))
			{
				//equationBuffer.setText(equationBuffer.getText() + numericButtonArray[i].getText());
				
				if(currentInput.getText().equals("0"))
				{
					currentInput.setText("");
				}
				
				if(operatorPushedLast)
				{
					currentInput.setText("");
					operatorPushedLast = false;
				}
				
				currentInput.setText(currentInput.getText() + numericButtonArray[i].getText());
			}
		}
		
		if(!currentInput.getText().equals("0"))	
		{
			// Operator buttons
			if(e.getSource().equals(add))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				
				equationBuffer.setText(equationBuffer.getText() + "+");
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(subtract))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				
					
				equationBuffer.setText(equationBuffer.getText() + "-");
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(multiply))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				
				equationBuffer.setText(equationBuffer.getText() + "*");
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(divide))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				
				equationBuffer.setText(equationBuffer.getText() + "/");
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(mod))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
				}
				
				equationBuffer.setText(equationBuffer.getText() + "%");
				operatorPushedLast = true;
			}
		}
		
		if(e.getSource().equals(openParen))
		{
			equationBuffer.setText(equationBuffer.getText() + "(");
			openParen.setText("( [" + (++totalParens) + "]");
		}		
		else if(e.getSource().equals(closeParen) && totalParens > 0)
		{
			if(currentInput.getText().equals("0"))
				equationBuffer.setText(equationBuffer.getText() + ")");
			else
				equationBuffer.setText(equationBuffer.getText() + currentInput.getText() + ")");
			
			totalParens--;
			if(totalParens > 0)
				openParen.setText("( [" + totalParens + "]");
			else
				openParen.setText("(");
			// Solve whatever is in parenthesis
		}
		else if(e.getSource().equals(equals))
		{
			// Implement equation stack logic here
			EquationSolver solver = new EquationSolver();
			
			String expression = equationBuffer.getText();
			
			if(expression.charAt(expression.length() - 1) != ')')
			{
				expression += currentInput.getText();
			}
			
			long solved = solver.SolveExpression(expression);
			
			equationBuffer.setText("");
			currentInput.setText(solved + "");
		}
		// End operators
		// Select Base Type Buttons
		if(e.getSource().equals(hexButton))
		{
			modifyButtonsEnabled(0, 15, true);
			
			hexButton.setBorder(baseBorder);
			decimalButton.setBorder(normalBaseBorder);
			octButton.setBorder(normalBaseBorder);
			binaryButton.setBorder(normalBaseBorder);
		}
		else if(e.getSource().equals(decimalButton))
		{
			modifyButtonsEnabled(10, 15, false);
			modifyButtonsEnabled(0, 9, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(baseBorder);
			octButton.setBorder(normalBaseBorder);
			binaryButton.setBorder(normalBaseBorder);
		}
		else if(e.getSource().equals(octButton))
		{
			modifyButtonsEnabled(8, 15, false);
			modifyButtonsEnabled(0, 7, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(normalBaseBorder);
			octButton.setBorder(baseBorder);
			binaryButton.setBorder(normalBaseBorder);
		}
		else if(e.getSource().equals(binaryButton))
		{
			modifyButtonsEnabled(2, 15, false);
			modifyButtonsEnabled(0, 1, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(normalBaseBorder);
			octButton.setBorder(normalBaseBorder);
			binaryButton.setBorder(baseBorder);
		}
		// End base selection buttons
		// Clear and delete buttons
		else if(e.getSource().equals(buttonCE))
		{
			currentInput.setText("0");			
		}
		else if(e.getSource().equals(buttonC))
		{
			currentInput.setText("0");
			equationBuffer.setText("");
			totalParens = 0;
			openParen.setText("(");
		}
		else if(e.getSource().equals(deleteRecent))
		{
			if(currentInput.getText().length() >= 1)
				currentInput.setText(currentInput.getText().substring(0, currentInput.getText().length() - 1));
			
			if(currentInput.getText().length() == 0)
				currentInput.setText("0");
		}
		// End clear and delete buttons
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}

		// Update Hex, Dec, Oct, and Bin values
		updateBaseValues();
	}
}
