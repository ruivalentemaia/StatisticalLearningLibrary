package multipleLinearRegression;


public class Feature {
	private int id;
	private String name; //name of the feature.
	private double[] dataValues; //array with the data values;
	private double mean; //mean of the data values.
	private double variance;
	private double standardDev;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
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

	public double getStandardDev() {
		return standardDev;
	}

	public void setStandardDev(double standardDev) {
		this.standardDev = standardDev;
	}
	
	/*
	 * 
	 */
	public Feature(int id, String n, double[] x) {
		float sumOfValues = 0;
		this.setId(id);
		
		if((n.equals("")) || (n.equals(null))) {
			this.name = "x" + id;
		}
		else this.name = n;
		
		if(x.length != 0) {
			this.dataValues = x;
			for(int i = 0; i < this.dataValues.length; i++) {
				sumOfValues += dataValues[i];
			}
			sumOfValues = sumOfValues / (float) this.dataValues.length;
			this.mean = sumOfValues;
			
			//calculates variance of the dataValues array.
			sumOfValues = 0;
			for(int i = 0; i < dataValues.length; i++){
				sumOfValues += Math.pow(dataValues[i]-this.mean, 2);
			}
			this.setVariance(sumOfValues / (double) dataValues.length);
			this.setStandardDev(Math.sqrt(this.variance));
			
		}
		else {
			try {
				finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * 
	 */
	public double[] calculatePValueInterval(double tStatistic, int df){
		double [] pValue = new double[2];
		double intervalRight = 0;
		double intervalLeft = 0;
		
		//t-values table contents.
		double[][] tTable = {{0.000,1.000,1.376,1.963, 3.078, 6.314, 12.71, 31.82, 63.66, 318.31, 636.62},
							 {0.000, 0.816, 1.061, 1.386, 1.886, 2.920, 4.303, 6.965, 9.925, 22.327, 31.599},
							 {0.000, 0.765, 0.978, 1.250, 1.638, 2.353, 3.182, 4.541, 5.841, 10.215, 12.924},
							 {0.000, 0.741, 0.941, 1.190, 1.533, 2.132, 2.776, 3.747, 4.604, 7.173, 8.610},
							 {0.000, 0.727, 0.920, 1.156, 1.476, 2.015, 2.571, 3.365, 4.032, 5.893, 6.869},
							 {0.000, 0.718, 0.906, 1.134, 1.440, 1.943, 2.447, 3.143, 3.707, 5.208, 5.959},
							 {0.000, 0.711, 0.896, 1.119, 1.415, 1.895, 2.365, 2.998, 3.499, 4.785, 5.408},
							 {0.000, 0.706, 0.889, 1.108, 1.397, 1.860, 2.306, 2.896, 3.355, 4.501, 5.041},
							 {0.000, 0.703, 0.883, 1.100, 1.383, 1.833, 2.262, 2.821, 3.250, 4.297, 4.781},
							 {0.000, 0.700, 0.879, 1.093, 1.372, 1.812, 2.228, 2.764, 3.169, 4.144, 4.587},
							 {0.000, 0.697, 0.876, 1.088, 1.363, 1.796, 2.201, 2.718, 3.106, 4.025, 4.437},
							 {0.000, 0.695, 0.873, 1.083, 1.356, 1.782, 2.179, 2.681, 3.055, 3.930, 4.318},
							 {0.000, 0.694, 0.870, 1.079, 1.350, 1.771, 2.160, 2.650, 3.012, 3.852, 4.221},
							 {0.000, 0.692, 0.868, 1.076, 1.345, 1.761, 2.145, 2.624, 2.977, 3.787, 4.140},
							 {0.000, 0.691, 0.866, 1.074, 1.341, 1.753, 2.131, 2.602, 2.947, 3.733, 4.073},
							 {0.000, 0.690, 0.865, 1.071, 1.337, 1.746, 2.120, 2.583, 2.921, 3.686, 4.015},
							 {0.000, 0.689, 0.863, 1.069, 1.333, 1.740, 2.110, 2.567, 2.898, 3.646, 3.965},
							 {0.000, 0.688, 0.862, 1.067, 1.330, 1.734, 2.101, 2.552, 2.878, 3.610, 3.922},
							 {0.000, 0.688, 0.861, 1.066, 1.328, 1.729, 2.093, 2.539, 2.861, 3.579, 3.883},
							 {0.000, 0.687, 0.860, 1.064, 1.325, 1.725, 2.086, 2.528, 2.845, 3.552, 3.850},
							 {0.000, 0.686, 0.859, 1.063, 1.323, 1.721, 2.080, 2.518, 2.831, 3.527, 3.819},
							 {0.000, 0.686, 0.858, 1.061, 1.321, 1.717, 2.074, 2.508, 2.819, 3.505, 3.792},
							 {0.000, 0.685, 0.858, 1.060, 1.319, 1.714, 2.069, 2.500, 2.807, 3.485, 3.768},
							 {0.000, 0.685, 0.857, 1.059, 1.318, 1.711, 2.064, 2.492, 2.797, 3.467, 3.745},
							 {0.000, 0.684, 0.856, 1.058, 1.316, 1.708, 2.060, 2.485, 2.787, 3.450, 3.725},
							 {0.000, 0.684, 0.856, 1.058, 1.315, 1.706, 2.056, 2.479, 2.779, 3.435, 3.707},
							 {0.000, 0.684, 0.855, 1.057, 1.314, 1.703, 2.052, 2.473, 2.771, 3.421, 3.690},
							 {0.000, 0.683, 0.855, 1.056, 1.313, 1.701, 2.048, 2.467, 2.763, 3.408, 3.674},
							 {0.000, 0.683, 0.854, 1.055, 1.311, 1.699, 2.045, 2.462, 2.756, 3.396, 3.659},
							 {0.000, 0.683, 0.854, 1.055, 1.310, 1.697, 2.042, 2.457, 2.750, 3.385, 3.646},
							 {0.000, 0.681, 0.851, 1.050, 1.303, 1.684, 2.021, 2.423, 2.704, 3.307, 3.551},
							 {0.000, 0.679, 0.848, 1.045, 1.296, 1.671, 2.000, 2.390, 2.660, 3.232, 3.460},
							 {0.000, 0.678, 0.846, 1.043, 1.292, 1.664, 1.990, 2.374, 2.639, 3.195, 3.416},
							 {0.000, 0.677, 0.845, 1.042, 1.290, 1.660, 1.984, 2.364, 2.626, 3.174, 3.390},
							 {0.000, 0.675, 0.842, 1.037, 1.282, 1.646, 1.962, 2.330, 2.581, 3.098, 3.300}};
		
		//t-values two-tails header
		//double[] twoTailsHeader = {1.00, 0.50, 0.40, 0.30, 0.20, 0.10, 0.05, 0.02, 0.01, 0.002, 0.001};
		
		//t-values one-tail header
		double[] oneTailHeader = {0.5, 0.25, 0.2, 0.15, 0.10, 0.05, 0.025, 0.01, 0.005, 0.001, 0.0005};
		
		//t-values Z footer
		//double[] ZFooter = {0.0, 0.674, 0.842, 1.036, 1.282, 1.645, 1.960, 2.326, 2.576, 3.090, 3.291};
		
		//t-values confidence level footer
		//double[] confidenceLevelFooter ={0, 0.5, 0.6, 0.7, 0.8, 0.9, 0.95, 0.98, 0.99, 0.998, 0.999};
		
		if(tStatistic > 0){
			if(df <= 30){
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[df-1][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[df-1][i] >= tStatistic) {
						intervalRight = tTable[df-1][i];
						intervalLeft = tTable[df-1][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if( (df > 30) && (df <= 40)){
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[30][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[30][i] >= tStatistic) {
						intervalRight = tTable[30][i];
						intervalLeft = tTable[30][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 40) && (df <= 60)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[31][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[31][i] >= tStatistic) {
						intervalRight = tTable[31][i];
						intervalLeft = tTable[31][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 60) && (df <= 80)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[32][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[32][i] >= tStatistic) {
						intervalRight = tTable[32][i];
						intervalLeft = tTable[32][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 80) && (df <= 100)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[33][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[33][i] >= tStatistic) {
						intervalRight = tTable[33][i];
						intervalLeft = tTable[33][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 100) && (df <= 1000)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[34][i] < tStatistic)){
						intervalRight = 0.001;
						break;
					}
					if(tTable[34][i] >= tStatistic) {
						intervalRight = tTable[34][i];
						intervalLeft = tTable[34][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			intervalRight = 2.0 * intervalRight;
			intervalLeft = 2.0 * intervalLeft;
			pValue[0] = intervalLeft;
			pValue[1] = intervalRight;
			
			double temp = 0;
			if(pValue[0] > pValue[1]){
				temp = pValue[0];
				pValue[0] = pValue[1];
				pValue[1] = temp;
			}
		}
		else if(tStatistic == 0){
			pValue[0] = 0;
			pValue[1] = 0;
		}
		else {
			if(df <= 30){
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[df-1][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[df-1][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[df-1][i];
						intervalLeft = tTable[df-1][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if( (df > 30) && (df <= 40)){
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[30][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[30][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[30][i];
						intervalLeft = tTable[30][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 40) && (df <= 60)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[31][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[31][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[31][i];
						intervalLeft = tTable[31][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 60) && (df <= 80)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[32][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[32][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[32][i];
						intervalLeft = tTable[32][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 80) && (df <= 100)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[33][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[33][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[33][i];
						intervalLeft = tTable[33][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			
			else if((df > 100) && (df <= 1000)) {
				for(int i = 0; i < tTable[0].length; i++){
					if( (i == tTable.length-1) && (tTable[34][i] < Math.abs(tStatistic))){
						intervalRight = 0.001;
						break;
					}
					else if(tTable[34][i] >= Math.abs(tStatistic)) {
						intervalRight = tTable[34][i];
						intervalLeft = tTable[34][i-1];
					}
					if(intervalRight > 0){
						intervalRight = oneTailHeader[i];
						intervalLeft = oneTailHeader[i-1];
						break;
					}
				}
			}
			intervalRight = 2.0 * intervalRight;
			intervalLeft = 2.0 * intervalLeft;
			pValue[0] = intervalLeft;
			pValue[1] = intervalRight;
			
			double temp = 0;
			if(pValue[0] > pValue[1]){
				temp = pValue[0];
				pValue[0] = pValue[1];
				pValue[1] = temp;
			}
		}
		
		return pValue;
	}
	
	
	/*
	 * Returns the minimum value of the dataValues array.
	 */
	public double getMinimum() {
		double min = this.getDataValues()[0];
		for(int i = 1; i < this.getDataValues().length; i++){
			if(this.getDataValues()[i] < min)
				min = this.getDataValues()[i];
		}
		return min;
	}
	
	
	/*
	 * Returns the maximum value of the dataValues array.
	 */
	public double getMaximum(){
		double max = this.getDataValues()[0];
		for(int i = 1; i < this.getDataValues().length; i++){
			if(this.getDataValues()[i] > max)
				max = this.getDataValues()[i];
		}
		return max;
	}
	
	
	/*
	 * Builds an array of intervals for the buildHistogram function,
	 * based on the dataValues distribution's standard deviation.
	 */
	private double[] buildIntervals(){
		int sizeNeeded = 0;
		double currentValue = this.getMinimum();
		while(currentValue < this.getMaximum()){
			currentValue += this.getStandardDev();
			sizeNeeded++;
		}
		
		//declares the array.
		double[] intervals = new double[sizeNeeded+1];
		int counter = 1;
		currentValue = this.getMinimum();
		intervals[0] = this.getMinimum();
		while(currentValue < this.getMaximum()){
			intervals[counter] = currentValue + this.getStandardDev();
			currentValue += this.getStandardDev();
			counter++;
		}
		intervals[sizeNeeded] = this.getMaximum();
		
		return intervals;
	}
	
	/*
	 * Prints the values of this Feature.
	 */
	public void printValues() {
		String debugPrinter = "\n[ ";
		for(int i = 0; i < this.getDataValues().length; i++){
			if(i != this.getDataValues().length-1)
				debugPrinter += this.getDataValues()[i] + ", ";
			else debugPrinter += this.getDataValues()[i];
		}
		debugPrinter+=" ]";
		System.out.println(debugPrinter);
	}
	
	
	/*
	 * Prints the intervals obtained in order to build the histogram.
	 */
	public void printIntervals() {
		double[] intervals = this.buildIntervals();
		double current = intervals[0];
		double next = intervals[0] + this.getStandardDev();
		for(int i = 1; i < intervals.length; i++){
			System.out.println("[ " + current + ", " + next + " ]");
			if(i < intervals.length-1){
				current = intervals[i];
				next = intervals[i+1];
			}
			else {
				current = next;
			}
		}
	}
	
	
	/*
	 * Builds an histogram of the dataValues array, based on the
	 * intervals array returned by the buildIntervals method. Returns
	 * an array of ints, containing the number of elements for each one 
	 * of the intervals.
	 */
	public int[] buildHistogram() {
		double[] intervals = new double[1];
		int[] histogram = new int[1];
		double min = 0, max = 0;
		
		if(this.buildIntervals().length > 1) {
			intervals = new double[this.buildIntervals().length];
			histogram = new int[this.buildIntervals().length-1];
			intervals = this.buildIntervals();
		}
		else {
			intervals[0] = this.getMinimum();
		}
		
		//declares it and fills it with zeros.
		for(int i = 0; i < histogram.length; i++)
			histogram[i] = 0;
		
		//increments the right interval in the histogram.
		for(int i = 0; i < this.getDataValues().length; i++){
			for(int j = 0; j < intervals.length; j++){
				if(j != intervals.length-1){
					min = intervals[j];
					max = intervals[j+1];
					
					if( (this.getDataValues()[i] >= min) && (this.getDataValues()[i] < max))
						histogram[j]++;
				}
				else {
					min = intervals[j];
					max = intervals[j] + 1;
					
					if( (this.getDataValues()[i] >= min) && (this.getDataValues()[i] < max))
						if(intervals.length > 1)
							histogram[j-1]++;
						else histogram[j]++;
				}
			}
		}
		
		//DEBUG
		for(int i = 0; i < histogram.length; i++){
			System.out.println("Elements in interval " + (i+1) + " = " + histogram[i]);
		}
		
		return histogram;
	}
	
	/*
	 * Computes the log of all the elements of this feature.
	 */
	public void computeLog() {
		String newName = "log " + this.getName();
		this.setName(newName);
		for(int i = 0; i < this.getDataValues().length; i++){
			this.getDataValues()[i] = Math.log(this.getDataValues()[i]);
		}
	}
	
	/*
	 * Computes the e (opposite operation of natural log) of all the 
	 * elements of this feature.
	 */
	public void computeExp(){
		String newName = "e^" + this.getName();
		this.setName(newName);
		for(int i = 0; i < this.getDataValues().length; i++){
			this.getDataValues()[i] = Math.round(Math.exp(this.getDataValues()[i]) * 10000.0) / 10000.0;
		}
	}
	
	/*
	 * Let xi be each one of the values in the dataValues array.
	 * This method computes xi^a, where a is an integer parameter.
	 * Calculated through Math.pow for each element of the dataValues
	 * array.
	 */
	public void computeExponential(int a){
		String newName = "pow(" + this.getName() + ", " + a + ")";
		this.setName(newName);
		for(int i = 0; i < this.getDataValues().length; i++){
			this.getDataValues()[i] = Math.pow(this.getDataValues()[i], a);
		}
	}
	
	/*
	 * Scales the values of this Feature between 0 and 1, using
	 * the min-max scaling.
	 * http://nbviewer.ipython.org/github/rasbt/pattern_classification/blob/master/preprocessing/about_standardization_normalization.ipynb#About-Min-Max-scaling
	 * 
	 */
	public void normalize(){
		double min = this.getMinimum();
		double max = this.getMaximum();
		for(int i = 0; i < this.getDataValues().length; i++){
			this.getDataValues()[i] = (this.getDataValues()[i] - min) / (max - min);
		}
	}
	
	/*
	 * Normalizes the values of this Feature using the Z-score function:
	 * Z = (Xi - mean(X)) / StdDev(X).
	 */
	public void normalizeWithZscore() {
		double mean = this.getMean();
		double stdDev = this.getStandardDev();
		for(int i = 0; i < this.getDataValues().length; i++) {
			this.getDataValues()[i] = (this.getDataValues()[i] - mean) / stdDev;
		}
	}
	
	/*
	 * Normalizes the values of this Feature within an interval
	 * passed as parameter according to the following formula:
	 * Xi = lower + (Xi-min(X))*(higher-lower) /(max(X)-min(X)).
	 */
	public void normalizeWithinScale(int lower, int higher) {
		double max = this.getMaximum();
		double min = this.getMinimum();
		for(int i = 0; i < this.getDataValues().length; i++){
			this.getDataValues()[i] = lower + (this.getDataValues()[i] - min) * (higher-lower) / (max-min);
		}
	}
}