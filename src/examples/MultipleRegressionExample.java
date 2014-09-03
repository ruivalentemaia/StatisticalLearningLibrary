package examples;

import java.io.IOException;

import jxl.read.biff.BiffException;
import multipleLinearRegression.Feature;
import multipleLinearRegression.MultipleLinearRegression;
import multipleLinearRegression.StepwiseSelector;

public class MultipleRegressionExample {

	public static void main(String[] args) throws BiffException, IOException {
		/*MultipleLinearRegression reg = new MultipleLinearRegression("CrimeRatePerMillionHabitantsInDifferentAmericanCities.xls");
		reg.doMultipleLinearRegression();
		StepwiseSelector sel = new StepwiseSelector(reg, "AdjustedRSquared");
		sel.forwardSelection();
		reg.createAllPossibleInteractions();
		System.out.println();
		*/
		
		double[] values = {1.0, 3.5, 6.6, 7.6, 7.9, 11.4, 12.1, 12.3};
		Feature f = new Feature(1, "x", values);
		f.printValues();
		f.normalizeWithinScale(1, 10);
		f.printValues();
	}

}
