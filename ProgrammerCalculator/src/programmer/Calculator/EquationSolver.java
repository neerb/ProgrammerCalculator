package programmer.Calculator;

import java.util.*;

public class EquationSolver 
{
	Stack<Character> operatorStack;
	Stack<Long> operandStack;
	
	public EquationSolver()
	{
		operatorStack = new Stack<Character>();
		operandStack = new Stack<Long>();
	}
	
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
	
	private boolean isPlusMinus(char c)
	{
		return c == '+' || c == '-';
	}
	
	private boolean isMultDivOrMod(char c)
	{
		return 	c == '*' || c == '/' ||
				c == '%';
	}
	
	private boolean isOpenParenthesis(char c)
	{
		return c == '(';
	}
	
	private boolean isCloseParenthesis(char c)
	{
		return c == ')';
	}
	
	private int getOrderPrecedence(char c)
	{
		if(c == '*' || c == '/' || c == '%')
			return 2;
		else if(c == '+' || c == '-')
			return 1;
		// Parentheses
		else
			return 3;
	}
	
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
			operandStack.push(operandTwo / operandOne);
		}
		else if(operator == '%')
		{
			operandStack.push(operandTwo % operandOne);
		}
	}

	
	public long SolveExpression(String expression)
	{
		System.out.println(expression);
		
		long result = 0;
		
		for(int i = 0; i < expression.length(); i++)
		{
			char currChar = expression.charAt(i);
			
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
				
				System.out.println("Expression " + currChar);

				
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
				
				//operandStack.add(toLong);
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
		
		return result;
	}
}
