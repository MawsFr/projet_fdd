import java.text.Normalizer;

import org.apache.commons.lang3.StringUtils;

public class Tools {
	public static void main(String[] args) {
		String s = "The Force AwakensÂ           ";
		System.out.println(StringUtils.stripAccents(s.trim().replaceAll("[ÀÁÂÃÄÈÉÊËÍÌÎÏÙÚÛÜÒÓÔÕÖÑÇªº§³²¹àáâãäèéêëíìîïùúûüòóôõöñç]", "")));
	}
	
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
}
