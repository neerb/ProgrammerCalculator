package programmer.Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	
	int width = 400;
	int height = 600;
	
	
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
		equationBuffer = new JLabel("buffer");
		equationBuffer.setBorder(new EmptyBorder(0, 0, 0, 10));
		equationBuffer.setHorizontalAlignment(SwingConstants.RIGHT);
		currentInput = new JLabel("current");
		currentInput.setBorder(new EmptyBorder(0, 0, 0, 10));
		currentInput.setHorizontalAlignment(SwingConstants.RIGHT);
		
		hexButton = new JButton("HEX   0");
		hexButton.setHorizontalAlignment(SwingConstants.LEFT);
		decimalButton = new JButton("DEC   0");
		decimalButton.setHorizontalAlignment(SwingConstants.LEFT);
		octButton = new JButton("OCT   0");
		octButton.setHorizontalAlignment(SwingConstants.LEFT);
		binaryButton = new JButton("BIN   0");
		binaryButton.setHorizontalAlignment(SwingConstants.LEFT);
				
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
		deleteRecent = new JButton("Bksp");
		
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
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Numeric buttons
		if(e.getSource().equals(zero))
		{
			
		}
		else if(e.getSource().equals(one))
		{
			
		}
		else if(e.getSource().equals(two))
		{
			
		}
		else if(e.getSource().equals(three))
		{
			
		}
		else if(e.getSource().equals(four))
		{
			
		}
		else if(e.getSource().equals(five))
		{
			
		}
		else if(e.getSource().equals(six))
		{
			
		}
		else if(e.getSource().equals(seven))
		{
			
		}
		else if(e.getSource().equals(eight))
		{
			
		}
		else if(e.getSource().equals(nine))
		{
			
		}
		else if(e.getSource().equals(hexA))
		{
			
		}
		else if(e.getSource().equals(hexB))
		{
			
		}
		else if(e.getSource().equals(hexC))
		{
			
		}
		else if(e.getSource().equals(hexD))
		{
			
		}
		else if(e.getSource().equals(hexE))
		{
			
		}
		else if(e.getSource().equals(hexF))
		{
			
		}
		// End numerics
		// Select Base Type Buttons
		else if(e.getSource().equals(hexButton))
		{
			modifyButtonsEnabled(0, 15, true);
		}
		else if(e.getSource().equals(decimalButton))
		{
			modifyButtonsEnabled(10, 15, false);
			modifyButtonsEnabled(0, 9, true);
		}
		else if(e.getSource().equals(octButton))
		{
			modifyButtonsEnabled(8, 15, false);
			modifyButtonsEnabled(0, 7, true);
		}
		else if(e.getSource().equals(binaryButton))
		{
			modifyButtonsEnabled(2, 15, false);
			modifyButtonsEnabled(0, 1, true);
		}
		// End base selection buttons
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
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}
		else if(e.getSource().equals(mod))
		{
			
		}
		
	}
}
