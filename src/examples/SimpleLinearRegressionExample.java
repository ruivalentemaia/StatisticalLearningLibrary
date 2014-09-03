package examples;

import simpleLinearRegression.SimpleLinearRegression;

public class SimpleLinearRegressionExample {
	
	public static void main(String [] args) {
		double[] x = { 36, 49, 64, 81, 100};
		double[] y = { 26, 157, 1100, 8801, 79210};
		SimpleLinearRegression reg = new SimpleLinearRegression(x,y, "Aptitude Test");
		reg.doSimpleLinearRegression();
		reg.printSimpleRegression();
		reg.predict(12);
	}
}
