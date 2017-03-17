package by.epam.l08.example;

import java.util.Date;

public class GenericMethodOverload
{
	public static <Type> void method(Type obj)
	{
		System.out.println("<Type> void method(Type obj)");
	}

	public static void method(Number obj)
	{
		System.out.println("void method(Number obj)");
	}

	public static void method(Integer obj)
	{
		System.out.println("void method(Integer obj)");
	}

	public static void method(String obj)
	{
		System.out.println("void method(String obj)");
	}

	public static void main(String[] args)
	{
		Number number = new Integer(1);
		Integer integer = new Integer(2);
		method(number);
		method(integer);
		method(23);
		method("string");
		method(new Date());
	}
}
