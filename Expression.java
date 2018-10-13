import java.util.Scanner;
//grammar:
//expression = term (+- term...)
//term = factor (*/% factor)
//factor = number | ( expression )
//number = digit(digits...)

public class Expression 
{
	static String input;
	static int index;
	public static int number()
	{
		int numberValue = 0;
		int flag = 0;
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		if(input.charAt(index) == '-')
		{
			flag  = 1;
			++index;
		}
		while(input.charAt(index) >= '0' && input.charAt(index) <= '9')
		{
			numberValue = numberValue * 10 + input.charAt(index) - '0';
			if(index < (input.length() - 1))
				++index;
			else
				break;
		}
		if(flag == 1)
			numberValue = -numberValue;
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		return numberValue;
	}
	public static int factor()
	{
		int factorValue = 0;
		if(input.charAt(index) == '(')
		{
			++index;//delete ')'
			while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
				++index;
			factorValue = expression();
			if(index < (input.length() - 1))//delete ')'
				++index;
		}
		else
			factorValue = number();
		return factorValue;
	}
	public static int term()
	{
		int termValue = 0;
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		termValue = factor();
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		while(input.charAt(index) == '*' || input.charAt(index) == '/' || input.charAt(index) == '%')
		{
			if(input.charAt(index) == '*')
			{
				++index;
				while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
					++index;
				termValue = termValue * factor();
			}
			else if(input.charAt(index) == '/')
			{
				++index;
				while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
					++index;
				termValue = termValue / factor();
			}
			else if(input.charAt(index) == '%')
			{
				++index;
				while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
					++index;
				termValue = termValue % factor();
			}
			while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
				++index;
		}
		return termValue;
	}
	public static int expression()
	{
		int expressionValue = 0;
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		expressionValue = term();
		while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
			++index;
		while(input.charAt(index) == '+' || input.charAt(index) == '-')
		{
			if(input.charAt(index) == '+')
			{
				++index;
				while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
					++index;
				expressionValue = expressionValue + term();
			}
			else if(input.charAt(index) == '-')
			{
				++index;
				while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
					++index;
				expressionValue = expressionValue - term();
			}
			while(input.charAt(index) == ' ' || input.charAt(index) == '\t')
				++index;
		}
		return expressionValue;
	}
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int result = 0;
		index = 0;
		input = scanner.nextLine();
		result = expression();
		System.out.print(result);
	}
}
