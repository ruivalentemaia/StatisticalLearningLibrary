package examples;

import java.io.IOException;

import jxl.read.biff.BiffException;
import multipleLinearRegression.Feature;
import multipleLinearRegression.MultipleLinearRegression;
import multipleLinearRegression.StepwiseSelector;

public class MultipleRegressionExample {

	public static void main(String[] args) throws BiffException, IOException {
		MultipleLinearRegression reg = new MultipleLinearRegression("CrimeRatePerMillionHabitantsInDifferentAmericanCities.xls");
		reg.doMultipleLinearRegression();
		StepwiseSelector sel = new StepwiseSelector(reg, "AdjustedRSquared");
		sel.forwardSelection();
		reg.createAllPossibleInteractions();
		System.out.println();
	}

}
