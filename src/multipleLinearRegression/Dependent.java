package multipleLinearRegression;

public class Dependent {
	private double[] dataValues; //array with the data values.
	private double mean; //mean of the data values.
	private int n; //number of elements.
	private double variance;
	private double standardDev;
	
	public double[] getDataValues() {
		return dataValues;
	}
	
	public void setDataValues(double[] dataValues) {
		this.dataValues = dataValues;
	}
	
	public double getMean() {
		return mean;
	}
	
	public void setMean(double mean) {
		this.mean = mean;
	}
	
	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public double getStandardDev() {
		return standardDev;
	}

	public void setStandardDev(double standardDev) {
		this.standardDev = standardDev;
	}

	public Dependent(double[] y) {
		double sumOfValues = 0;
		if(y.length != 0) {
			this.n = y.length;
			for(int i = 0; i < y.length; i++){
				sumOfValues += y[i];
			}
			sumOfValues = sumOfValues / (double) y.length;
			this.mean = sumOfValues;
			this.dataValues = new double[y.length];
			this.dataValues = y;
			
			//calculates variance of the dataValues array.
			sumOfValues = 0;
			for(int i = 0; i < y.length; i++){
				sumOfValues += Math.pow(y[i]-this.mean, 2);
			}
			this.variance = sumOfValues / (double) y.length;
			this.standardDev = Math.sqrt(this.variance);
		}
		else {
			try {
				finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
