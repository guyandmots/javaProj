package com.myorg.javacourse;

public class MathJa {

	private int radius, hypotenuse, base, exp;
	private double angleB;
	
	public static double calcCircleArea(int radiusVal)
	{
		return Math.PI*radiusVal*radiusVal;
	}
	
	public static double calcOpposite(double angleBVal, int hypotenuseVal)
	{
		return Math.sin(Math.toRadians(angleBVal))*hypotenuseVal;
	}
	
	public static long calcPower(int baseVal, int expVal)
	{
		int res=1;
		for(int i=0;i<expVal;i++)
			res*=baseVal;
		return res;
	}
	
	public String getResults()
	{
		String line1, line2, line3, resultStr;
		line1= ("Calculation 1: area of circle with radius " +radius+" is:"+ MathJa.calcCircleArea(30));
		line2=("lenght of opposite where angle is: "+" 30"+ "is "+MathJa.calcOpposite(30, 50));
		line3=("power of "+ base+" with exp "+ exp+" is "+MathJa.calcPower(2,3));
		resultStr=line1+"<br>"+line2+"<br>"+line3;
		return resultStr;
	}
}
