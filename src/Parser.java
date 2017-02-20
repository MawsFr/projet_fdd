import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import extensions.CSVFile;


public class Parser {

	private int[] stringColumns = {1, 2, 7, 10, 11, 12, 15, 17, 18, 20, 21, 22};
	private Set<String> movieGenres = new HashSet();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			new Parser().processFile(args[0]);
		} else {
			new Parser().processFile("movie_metadata.csv");
		}
	}
	
	public void processFile(String name) {
		//this.processStrings(name, ',');
		this.createMovieGenres(name, ',');
	}
	
	public void processStrings(String name, char separator) {
		CSVFile file = new CSVFile(name, separator);
		for(int j = 0; j < file.rowCount(); ++j) {
			for(int i = 0; i < stringColumns.length; ++i) {
				System.out.print(file.getCell(j, stringColumns[i] - 1) + "		|	");
			}
			System.out.println();
		}
		
	}
	
	public void createMovieGenres(String name, char separator) {
		CSVFile file = new CSVFile(name, separator);
		for(int i=1;i<file.rowCount();i++) {
			String genres = file.getCell(i, 9);
			System.out.println("Movie title : " + file.getCell(i, 11));
			System.out.println("Movie genres : " + genres);
			/*
			 * String string = "004-034556";
			String[] parts = string.split("-");
			String part1 = parts[0]; // 004
			String part2 = parts[1]; // 034556
			 */
			String[] parts = genres.split("\\|");
			for(int j = 0;j<parts.length;j++) {
				if(!movieGenres.contains(parts[j]))
					movieGenres.add(parts[j]);
				else
					System.out.println(parts[j] + " skipped");
			}
			System.out.println("movie genres array size : " + movieGenres.size());
		}		
		for (Iterator iterator = movieGenres.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
	
	
	public void loadFile(File file, String delimiter, String headerDelimiter, boolean trim, String stringSurrounder, String emptyReplacer) {
		
	}

}
