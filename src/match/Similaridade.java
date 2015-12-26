package match;

import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Jaro;

public class Similaridade {
	public static int SimilaridadeCosseno(String str1, String str2){
		AbstractStringMetric metric = new CosineSimilarity();
		return (int) (metric.getSimilarity(str1, str2) * 100);
	}
	
	public static int DistanciaEuclidiana(String str1, String str2){
		AbstractStringMetric metric = new EuclideanDistance();
		return (int) (metric.getSimilarity(str1, str2) * 100);
	}
	public static int SmithWaterman(String str1, String str2){
		AbstractStringMetric metric = new SmithWaterman();
		return (int) (metric.getSimilarity(str1, str2) * 100);
	}
	public static int Jaro(String str1, String str2){
		AbstractStringMetric metric = new Jaro();
		return (int) (metric.getSimilarity(str1, str2) * 100);
	}

}
