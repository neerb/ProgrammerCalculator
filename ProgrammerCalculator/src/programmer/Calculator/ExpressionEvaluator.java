package programmer.Calculator;

import java.util.*;

public class ExpressionEvaluator 
{
	Stack<Character> operatorStack;
	Stack<Long> operandStack;
	
	public ExpressionEvaluator()
	{
		// Stack contains operators
		operatorStack = new Stack<Character>();
		
		// Stack contains operands
		operandStack = new Stack<Long>();
	}
	
	// Checks if a character is a digit
	private boolean isDigit(char c)
	{
		String digits = "0123456789ABCDEF";
		
		for(int i = 0; i < digits.length(); i++)
		{
			if(digits.charAt(i) == c)
				return true;
		}
		
		return false;
	}
	
	// Checks if a character is an addition or subtraction operator
	private boolean isPlusMinus(char c)
	{
		return c == '+' || c == '-';
	}
	
	// Checks if a character is a quotient or multiplication operator
	private boolean isMultDivOrMod(char c)
	{
		return 	c == '*' || c == '/' ||
				c == '%';
	}
	
	// Checks for open parenthesis
	private boolean isOpenParenthesis(char c)
	{
		return c == '(';
	}
	
	// Checks for close parenthesis
	private boolean isCloseParenthesis(char c)
	{
		return c == ')';
	}
	
	// Processes the current operator
	void processOperator()
	{
		char operator = operatorStack.pop();
		
		long operandOne = operandStack.pop();
		long operandTwo = operandStack.pop();
		
		if(operator == '+')
		{
			operandStack.push(operandOne + operandTwo);
		}
		else if(operator == '-')
		{
			operandStack.push(operandTwo - operandOne);
		}
		else if(operator == '*')
		{
			operandStack.push(operandOne * operandTwo);
		}
		else if(operator == '/')
		{
			if(operandOne != 0)
				operandStack.push(operandTwo / operandOne);
			else
				operandStack.push(new Long(0));
		}
		else if(operator == '%')
		{
			if(operandOne != 0)
				operandStack.push(operandTwo % operandOne);
			else
				operandStack.push(new Long(0));			
		}
	}

	// Solves equation argument passed to parameter
	public long SolveExpression(String expression)
	{
		System.out.println(expression);
		
		long result = 0;
		
		for(int i = 0; i < expression.length(); i++)
		{
			char currChar = expression.charAt(i);
			
			// Processes operators until the operator stack is empty
			if(isPlusMinus(currChar))
			{
				while (!operatorStack.isEmpty() &&
				          (operatorStack.peek() == '+' || 
				           operatorStack.peek() == '-' ||
				           operatorStack.peek() == '*' ||
				           operatorStack.peek() == '/' ||
				           operatorStack.peek() == '%'))
				{
					processOperator();
				}
				
				System.out.println("Operator " + currChar);
				
				operatorStack.push(currChar);
			}
			
			else if(isMultDivOrMod(currChar))
			{
				while(!operatorStack.isEmpty() && isMultDivOrMod(operatorStack.peek()))
				{
					processOperator();
				}
				
				operatorStack.push(currChar);
			}
			else if(isDigit(currChar))
			{				
				String numericString = "";
				
				while(i < expression.length() && isDigit(expression.charAt(i)))
				{
					numericString += expression.charAt(i);
					
					System.out.println(expression.charAt(i) + "");

					i++;
				}
				
				i--;
				
				long toLong = Long.parseLong(numericString);
				
				operandStack.push(toLong);
				
				System.out.println("Added to operand stack number " + toLong);
			}
			else if(isOpenParenthesis(currChar))
			{
				operatorStack.push(currChar);
			}
			else if(isCloseParenthesis(currChar))
			{
				while(!isOpenParenthesis(operatorStack.peek()))
				{
					processOperator();
				}
				
				operatorStack.pop();
			}
		}
		
		while(!operatorStack.isEmpty())
		{
			processOperator();
		}
		
		result = operandStack.pop();
		
		operatorStack.clear();
		operandStack.clear();
		
		// Returns solved equation
		return result;
	}
}
