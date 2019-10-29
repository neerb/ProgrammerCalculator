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
	final BoxLayout upperLayout;
	
	final JPanel lowerPanel;
	final GridLayout lowerLayout;
	
	// Numeric button declared
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
		upperLayout = new BoxLayout(upperPanel, BoxLayout.Y_AXIS);
		upperPanel.setLayout(upperLayout);

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
		
		// Add components to upper panel
		upperPanel.add(new JLabel("Programmer"));
		upperPanel.add(new JButton("Hex 0"));
		
		
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

		
		
		
		this.add(upperPanel, BorderLayout.NORTH);
		
		this.add(lowerPanel, BorderLayout.SOUTH);
		
		//setSize(width, height);		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
}
