import java.text.Normalizer;

public class Tools {
	public static void main(String[] args) {
		String s = "The Force AwakensÂ           ";
		System.out.println(stripAccents(s));
	}
	
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
}
