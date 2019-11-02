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
	JButton[] baseButtonArray;
	final JButton hexButton;
	final JButton decimalButton;
	final JButton octButton;
	final JButton binaryButton;
	
	private boolean isHex = false;
	private boolean isDec = true;
	private boolean isOct = false;
	private boolean isBin = false;
	
	// Numeric Size button
	final JButton bitSizeButton;
	
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
	
	private String decimalEquation;
	private int cycleBitSize = 1;
	
	private final long BYTE_UPPER_BOUND = 127;
	private final long BYTE_LOWER_BOUND = -128;
	
	private final long WORD_UPPER_BOUND = 32767;
	private final long WORD_LOWER_BOUND = -32768;
	
	private final long DWORD_UPPER_BOUND = 2147483647;
	private final long DWORD_LOWER_BOUND = -2147483648;
	
	private final long QWORD_UPPER_BOUND = 9223372036854775807l;
	private final long QWORD_LOWER_BOUND = -9223372036854775808l;

	
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
		inputFont = new Font("Monospaced", Font.BOLD, 28);
		
		equationBuffer = new JLabel();
		equationBuffer.setFont(equationFont);
		equationBuffer.setBorder(new EmptyBorder(0, 0, 0, 10));
		equationBuffer.setHorizontalAlignment(SwingConstants.RIGHT);
		currentInput = new JLabel("0");
		currentInput.setFont(inputFont);
		currentInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		currentInput.setHorizontalAlignment(SwingConstants.RIGHT);
		
		baseBorder = new LineBorder(Color.BLUE, 2);
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
		
		// Add buttons to base button array
		baseButtonArray = new JButton[4];
		baseButtonArray[0] = hexButton;
		baseButtonArray[1] = decimalButton;
		baseButtonArray[2] = octButton;
		baseButtonArray[3] = binaryButton;

		// Add temporary upper panels to upperPanel, splits upper half into two halves
		upperPanel.add(tempUpperPanel1);
		upperPanel.add(tempUpperPanel2);
		
		
		// Define lower layout components
		lowerPanel = new JPanel();
		lowerLayout = new GridLayout(6, 6);
		lowerLayout.setHgap(3);
		lowerLayout.setVgap(3);
		lowerPanel.setLayout(lowerLayout);
		
		// Bit size buttons
		bitSizeButton = new JButton("WORD");
		bitSizeButton.setAlignmentX(CENTER_ALIGNMENT);
		
		
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
		
		// Operator buttons
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
		
		// Unused buttons initialization
		lsh= new JButton("Lsh");
		rsh = new JButton("Rsh");
		or = new JButton("Or");
		xor = new JButton("Xor");
		not = new JButton("Not");
		and = new JButton("And");
		decimal = new JButton(".");
		
		JPanel tempPanelGrid = new JPanel();
		tempPanelGrid.setLayout(lowerLayout);
						
		// Add components to lower panel
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
		lowerPanel.add(bitSizeButton);		
		
		tempPanelGrid.add(lsh);
		tempPanelGrid.add(rsh);
		tempPanelGrid.add(or);
		tempPanelGrid.add(xor);
		tempPanelGrid.add(not);
		tempPanelGrid.add(and);
		tempPanelGrid.add(second);
		tempPanelGrid.add(mod);
		tempPanelGrid.add(buttonCE);
		tempPanelGrid.add(buttonC);
		tempPanelGrid.add(deleteRecent);
		tempPanelGrid.add(divide);
		tempPanelGrid.add(hexA);
		tempPanelGrid.add(hexB);
		tempPanelGrid.add(seven);
		tempPanelGrid.add(eight);
		tempPanelGrid.add(nine);
		tempPanelGrid.add(multiply);
		tempPanelGrid.add(hexC);
		tempPanelGrid.add(hexD);
		tempPanelGrid.add(four);
		tempPanelGrid.add(five);
		tempPanelGrid.add(six);
		tempPanelGrid.add(subtract);
		tempPanelGrid.add(hexE);
		tempPanelGrid.add(hexF);
		tempPanelGrid.add(one);
		tempPanelGrid.add(two);
		tempPanelGrid.add(three);
		tempPanelGrid.add(add);
		tempPanelGrid.add(openParen);
		tempPanelGrid.add(closeParen);
		tempPanelGrid.add(plusMinus);
		tempPanelGrid.add(zero);
		tempPanelGrid.add(decimal);
		tempPanelGrid.add(equals);
		
		lowerPanel.add(tempPanelGrid);

		// Add Action listeners to buttons
		hexButton.addActionListener(this);
		decimalButton.addActionListener(this);
		octButton.addActionListener(this);
		binaryButton.addActionListener(this);
		
		bitSizeButton.addActionListener(this);
		
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

		for(int i = 0; i < baseButtonArray.length; i++)
		{
			baseButtonArray[i].setBorderPainted(true);
			baseButtonArray[i].setFocusPainted(true);
			baseButtonArray[i].setContentAreaFilled(false);
		}
		
		this.setLayout(new GridLayout(2, 1));
		
		this.add(upperPanel, BorderLayout.NORTH);
		this.add(lowerPanel, BorderLayout.SOUTH);
		
		
		// By default, start on decimal
		modifyButtonsEnabled(10, 15, false);
		modifyButtonsEnabled(0, 9, true);
		decimalButton.setBorder(baseBorder);
		
		
		//setSize(width, height);		
		setVisible(true);
		
		decimalEquation = "";
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
	
	
	String numToHexidecimalString(long number)
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
		String digits = "01";
		
		int val = 0;
		 
		for (int i = 0; i < binStr.length(); i++)  
		{  
		    char c = binStr.charAt(i);  
		    int d = digits.indexOf(c);  
		    val = 2 * val + d;  
		}  
		
		return val + "";  
	}
	
	String addSpaces(String str, int interval)
	{
		String addedSpaces = "0";
		

		return str;
	}
	
	
	void updateBaseValues()
	{
		String numString = currentInput.getText();

		long number = 0;
		
		if(isHex)
		{
			number = Long.parseLong(numString.toLowerCase(), 16);
		}
		else if(isDec)
		{
			number = Long.parseLong(numString.toLowerCase());
		}
		else if(isOct)
		{
			number = Long.parseLong(numString.toLowerCase(), 8);
		}
		else if(isBin)
		{
			number = Long.parseLong(numString.toLowerCase(), 2);
		}
				
		hexButton.setText("HEX   " + addSpaces(numToHexidecimalString(number).toUpperCase(), 4));
		decimalButton.setText("DEC   " + numToDecimalString(number));
		octButton.setText("OCT   " + addSpaces(numToOctalString(number), 3));
		binaryButton.setText("BIN   " + addSpaces(numToBinaryString(number), 4));
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
				
				
				long tempNum = 0;
				boolean inRange = true;
				String currentBitSize = bitSizeButton.getText();
				
				if(isHex)
				{
					tempNum = Long.parseLong((currentInput.getText() + numericButtonArray[i].getText()).toLowerCase(), 16);
				}
				else if(isDec)
				{
					tempNum = Long.parseLong((currentInput.getText() + numericButtonArray[i].getText()).toLowerCase(), 10);
				}
				else if(isOct)
				{
					tempNum = Long.parseLong((currentInput.getText() + numericButtonArray[i].getText()).toLowerCase(), 8);
				}
				else if(isBin)
				{
					tempNum = Long.parseLong((currentInput.getText() + numericButtonArray[i].getText()).toLowerCase(), 2);
				}
				
				switch(currentBitSize)
				{
				case "BYTE":
					if(tempNum < BYTE_LOWER_BOUND || tempNum > BYTE_UPPER_BOUND)
						inRange = false;
					break;
				case "WORD":
					if(tempNum < WORD_LOWER_BOUND || tempNum > WORD_UPPER_BOUND)
						inRange = false;
					break;
				case "DWORD":
					if(tempNum < DWORD_LOWER_BOUND || tempNum > DWORD_UPPER_BOUND)
						inRange = false;
					break;
				case "QWORD":
					if(tempNum < QWORD_LOWER_BOUND || tempNum > QWORD_UPPER_BOUND)
						inRange = false;
					break;
				}
				
				if(inRange)
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
					{
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
						
						if(isHex)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
						}
						else if(isDec)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
						}
						else if(isOct)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
						}
						else if(isBin)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
						}
					}
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
					
					if(isHex)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
					}
					else if(isDec)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
					}
					else if(isOct)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
					}
					else if(isBin)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
					}
				}
				
				equationBuffer.setText(equationBuffer.getText() + "+");
				decimalEquation = decimalEquation + "+";
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(subtract))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
					{
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
						
						if(isHex)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
						}
						else if(isDec)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
						}
						else if(isOct)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
						}
						else if(isBin)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
						}
					}
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
					
					if(isHex)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
					}
					else if(isDec)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
					}
					else if(isOct)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
					}
					else if(isBin)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
					}
				}
				
				equationBuffer.setText(equationBuffer.getText() + "-");
				decimalEquation = decimalEquation + "-";
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(multiply))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
					{
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
						
						if(isHex)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
						}
						else if(isDec)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
						}
						else if(isOct)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
						}
						else if(isBin)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
						}
					}
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
					
					if(isHex)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
					}
					else if(isDec)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
					}
					else if(isOct)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
					}
					else if(isBin)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
					}
				}
				
				equationBuffer.setText(equationBuffer.getText() + "*");
				decimalEquation = decimalEquation + "*";
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(divide))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
					{
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
						
						if(isHex)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
						}
						else if(isDec)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
						}
						else if(isOct)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
						}
						else if(isBin)
						{
							decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
						}
					}
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
					
					if(isHex)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16);
					}
					else if(isDec)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10);
					}
					else if(isOct)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8);
					}
					else if(isBin)
					{
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2);
					}
				}
				
				equationBuffer.setText(equationBuffer.getText() + "/");
				decimalEquation = decimalEquation + "/";
				operatorPushedLast = true;
			}
			else if(e.getSource().equals(mod))
			{
				if(equationBuffer.getText().length() > 0)
				{
					if(equationBuffer.getText().charAt(equationBuffer.getText().length() - 1) != ')')
					{
						equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
						decimalEquation = decimalEquation + Long.parseLong(currentInput.getText(), 16);
					}
				}
				else
				{
					equationBuffer.setText(equationBuffer.getText() + currentInput.getText());
					decimalEquation = decimalEquation + Long.parseLong(currentInput.getText(), 16);
				}
				
				equationBuffer.setText(equationBuffer.getText() + "%");
				decimalEquation = decimalEquation + "%";
				operatorPushedLast = true;
			}
		}
		
		if(e.getSource().equals(openParen))
		{
			equationBuffer.setText(equationBuffer.getText() + "(");
			decimalEquation = decimalEquation + "(";
			openParen.setText("( [" + (++totalParens) + "]");
		}		
		else if(e.getSource().equals(closeParen) && totalParens > 0)
		{
			if(currentInput.getText().equals("0"))
			{
				equationBuffer.setText(equationBuffer.getText() + ")");
				decimalEquation = decimalEquation + ")";
			}
			else
			{
				equationBuffer.setText(equationBuffer.getText() + currentInput.getText() + ")");
				
				if(isHex)
				{
					decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 16) + ")";
				}
				else if(isDec)
				{
					decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 10) + ")";
				}
				else if(isOct)
				{
					decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 8) + ")";
				}
				else if(isBin)
				{
					decimalEquation = decimalEquation + Long.parseLong(currentInput.getText().toLowerCase(), 2) + ")";
				}
			}
			
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
			
			String expression = decimalEquation;//equationBuffer.getText();
						
			if(expression.charAt(expression.length() - 1) != ')')
			{
				if(isHex)
				{
					expression += Long.parseLong(currentInput.getText().toLowerCase(), 16);
				}
				else if(isDec)
				{
					expression += Long.parseLong(currentInput.getText().toLowerCase());
				}
				else if(isOct)
				{
					expression += Long.parseLong(currentInput.getText().toLowerCase(), 8);
				}
				else if(isBin)
				{
					expression += Long.parseLong(currentInput.getText().toLowerCase(), 2);
				}
			}
			
			System.out.println(decimalEquation);
			
			if(expression.charAt(0) == '-')
			{
				expression = "0" + expression;
			}
			
			long solved = solver.SolveExpression(expression);
			String convertedValue = "";
			String currentBitSize = bitSizeButton.getText();
			
			Long longObject = new Long(solved);
			
			switch(currentBitSize)
			{
			case "BYTE":
				solved = longObject.byteValue();
				break;
			case "WORD":
				solved = longObject.shortValue();
				break;
			case "DWORD":
				solved = longObject.intValue();
				break;
			case "QWORD":
				solved = longObject.longValue();
				break;
			}
			
			if(isHex)
			{
				convertedValue = numToHexidecimalString(solved);
			}
			else if(isDec)
			{
				convertedValue = solved + "";
			}
			else if(isOct)
			{
				convertedValue = numToOctalString(solved);
			}
			else if(isBin)
			{
				convertedValue = numToBinaryString(solved);
			}
			
			operatorPushedLast = true;
			equationBuffer.setText("");
			decimalEquation = "";
			currentInput.setText(convertedValue.toUpperCase());
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
			
			isHex = true;
			isDec = false;
			isOct = false;
			isBin = false;
			
			String getNumerics = "";
			String hexString = hexButton.getText();
			
			for(int i = 6; i < hexString.length(); i++)
				getNumerics += hexString.charAt(i);
			
			currentInput.setText(getNumerics.toUpperCase());
		}
		else if(e.getSource().equals(decimalButton))
		{
			modifyButtonsEnabled(10, 15, false);
			modifyButtonsEnabled(0, 9, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(baseBorder);
			octButton.setBorder(normalBaseBorder);
			binaryButton.setBorder(normalBaseBorder);
			
			isHex = false;
			isDec = true;
			isOct = false;
			isBin = false;
			
			String getNumerics = "";
			String decString = decimalButton.getText();
			
			for(int i = 6; i < decString.length(); i++)
				getNumerics += decString.charAt(i);
			
			currentInput.setText(getNumerics);
		}
		else if(e.getSource().equals(octButton))
		{
			modifyButtonsEnabled(8, 15, false);
			modifyButtonsEnabled(0, 7, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(normalBaseBorder);
			octButton.setBorder(baseBorder);
			binaryButton.setBorder(normalBaseBorder);
			
			isHex = false;
			isDec = false;
			isOct = true;
			isBin = false;
			
			String getNumerics = "";
			String octString = octButton.getText();
			
			for(int i = 6; i < octString.length(); i++)
				getNumerics += octString.charAt(i);
			
			currentInput.setText(getNumerics);
		}
		else if(e.getSource().equals(binaryButton))
		{
			modifyButtonsEnabled(2, 15, false);
			modifyButtonsEnabled(0, 1, true);
			
			hexButton.setBorder(normalBaseBorder);
			decimalButton.setBorder(normalBaseBorder);
			octButton.setBorder(normalBaseBorder);
			binaryButton.setBorder(baseBorder);
			
			isHex = false;
			isDec = false;
			isOct = false;
			isBin = true;
			
			String getNumerics = "";
			String binString = binaryButton.getText();
			
			for(int i = 6; i < binString.length(); i++)
				getNumerics += binString.charAt(i);
			
			currentInput.setText(getNumerics);
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
			decimalEquation = "";
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
		else if(e.getSource().equals(bitSizeButton))
		{
			if(cycleBitSize >= 3)
				cycleBitSize = 0;
			else
				cycleBitSize++;
			
			switch(cycleBitSize)
			{
			case 0:
				bitSizeButton.setText("BYTE");
				break;
			case 1:
				bitSizeButton.setText("WORD");
				break;
			case 2:
				bitSizeButton.setText("DWORD");
				break;
			case 3:
				bitSizeButton.setText("QWORD");
				break;
			}
		}

		// Update Hex, Dec, Oct, and Bin values
		updateBaseValues();
	}
}
