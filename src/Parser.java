import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import extensions.CSVFile;


public class Parser extends Observable {

	private int[] stringColumns = {1, 2, 7, 10, 11, 12, 15, 17, 18, 20, 21, 22};
	private Set<String> movieGenres = new HashSet();
	
	private CSVFile csvFile;
	private File file;
	private boolean firstLineHeader;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			new Parser().processFile(args[0]);
		} else {
			new Parser().processFile("movie_metadata");
		}
	}
	
	public void processFile(String name) {
		//this.processStrings(name, ',');
		try {
			this.createMovieGenres(name, ',');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("trololo");
		}
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
	
	public void createMovieGenres(String name, char separator) throws Exception {
		CSVFile file = new CSVFile(name + ".csv", separator);
		
		
		for(int i=1;i<file.rowCount();i++) {
			String genres = file.getCell(i, Constants.INPUT_GENRES_POSITION);
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
			
			//Movie genre arrays created
			//Time to create the movie genres columns in the file
			
			System.out.println("movie genres array size : " + movieGenres.size());
		}		
		for (Iterator iterator = movieGenres.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		recreate(name);
	}
	
	/**
	 * Je te laisse mettre cette fonction o� ca te va, j'ai pas trop trop regard� la structure du projet pour etre honn�te ^^"
	 * En gros ce qu'elle fait c'est qu'elle lit le fichier, et pour chaque ligne, elle ajoute les movie genres pour le moement (tout en omettant de mettre l'ancienne valeur des genres ;)
	 * @throws Exception 
	 */
	public void recreate(String name) throws Exception {
		boolean header = true;
		CSVReader reader = new CSVReader(new FileReader(name + ".csv"));
		CSVWriter writer = new CSVWriter(new FileWriter(name + "_output.csv"), ',');
		String[] entries = null;
		
		while((entries = reader.readNext()) != null) {
			ArrayList<String> list = new ArrayList(Arrays.asList(entries));
			//locally save temp data : 
			String genres = entries[Constants.INPUT_GENRES_POSITION];
			//Remove unused data :
			list.remove(Constants.INPUT_GENRES_POSITION); //Remove movie genres
			if(header) {
				for(String genre : movieGenres) {
					list.add(genre);
				}
				header = false;
			} else {
				for(String genre : movieGenres) {
					if(genres.contains(genre)) {
						list.add("true");
					} else {
						list.add("false");
					}
				}
			}
			String[] array = list.toArray(new String[0]);
			writer.writeNext(array);
		}
		writer.close();
		reader.close();
	}
	
	
	public void loadFile(File file, char delimiter, boolean firstLineHeader) {
		this.firstLineHeader = firstLineHeader;
		this.file = file;
		this.csvFile = new CSVFile(file.getAbsolutePath(), delimiter);
		
		setChanged();
		notifyObservers(Fenetre.FILE_LOADED);
		
	}

	@Override
	public String toString() {
		return "Parser [stringColumns=" + Arrays.toString(stringColumns) + ", movieGenres=" + movieGenres + ", csvFile="
				+ csvFile + ", file=" + file + ", firstLineHeader=" + firstLineHeader + "]";
	}

	public int[] getStringColumns() {
		return stringColumns;
	}

	public void setStringColumns(int[] stringColumns) {
		this.stringColumns = stringColumns;
	}

	public Set<String> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(Set<String> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public CSVFile getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(CSVFile csvFile) {
		this.csvFile = csvFile;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isFirstLineHeader() {
		return firstLineHeader;
	}

	public void setFirstLineHeader(boolean firstLineHeader) {
		this.firstLineHeader = firstLineHeader;
	}
	
	

}
