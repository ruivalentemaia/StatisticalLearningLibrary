package examples;

import java.io.IOException;

import jxl.read.biff.BiffException;
import multipleLinearRegression.Feature;

public class FeatureNormalizationExample {
	public static void main(String[] args) throws BiffException, IOException {
		double[] values = {1.0, 3.5, 6.6, 7.6, 7.9, 11.4, 12.1, 12.3};
		Feature f = new Feature(1, "x", values);
		f.printValues();
		f.normalizeWithinScale(1, 10);
		f.printValues();
	}
}
