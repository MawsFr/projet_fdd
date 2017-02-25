import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

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
			this.createArffFile(name, ',');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("trololo");
		}
	}

	public void createArffFile(String name, char c) throws IOException {
		RandomAccessFile f = new RandomAccessFile(new File(name + "_output_arff.arff"), "rws");
		RandomAccessFile r = new RandomAccessFile(new File(name + "_output.csv"), "r");
		f.seek(0); // to the beginning
		String header = "@relation movies\n\n"
				+"@attribute color STRING\n"
				+"@attribute director_name STRING\n"
				+"@attribute num_critic_for_reviews NUMERIC\n"
				+"@attribute duration NUMERIC\n"
				+"@attribute director_facebook_likes NUMERIC\n"
				+"@attribute actor_3_facebook_likes NUMERIC\n"
				+"@attribute actor_2_name STRING NUMERIC\n"
				+"@attribute actor_1_facebook_likes NUMERIC\n"
				+"@attribute gross NUMERIC\n"
				+"@attribute actor_1_name STRING\n"
				+"@attribute movie_title STRING\n"
				+"@attribute num_voted_users NUMERIC\n"
				+"@attribute cast_total_facebook_likes NUMERIC\n"
				+"@attribute actor_3_name STRING\n"
				+"@attribute facenumber_in_poster NUMERIC\n"
				+"@attribute plot_keywords STRING\n"
				+"@attribute movie_imdb_link STRING\n"
				+"@attribute num_user_for_reviews NUMERIC\n"
				+"@attribute language STRING\n"
				+"@attribute country STRING\n"
				+"@attribute content_rating STRING\n"
				+"@attribute budget NUMERIC\n"
				+"@attribute title_year NUMERIC\n"
				+"@attribute actor_2_facebook_likes NUMERIC\n"
				+"@attribute imdb_score NUMERIC\n"
				+"@attribute aspect_ratio NUMERIC\n"
				+"@attribute movie_facebook_likes NUMERIC\n"
				+"@attribute Genre_Film-Noir {TRUE, FALSE}\n"
				+"@attribute Genre_Action {TRUE, FALSE}\n"
				+"@attribute Genre_War {TRUE, FALSE}\n"
				+"@attribute Genre_History {TRUE, FALSE}\n"
				+"@attribute Genre_Western {TRUE, FALSE}\n"
				+"@attribute Genre_Documentary {TRUE, FALSE}\n"
				+"@attribute Genre_Sport {TRUE, FALSE}\n"
				+"@attribute Genre_Thriller {TRUE, FALSE}\n"
				+"@attribute Genre_News {TRUE, FALSE}\n"
				+"@attribute Genre_Biography {TRUE, FALSE}\n"
				+"@attribute Genre_Comedy {TRUE, FALSE}\n"
				+"@attribute Genre_Mystery {TRUE, FALSE}\n"
				+"@attribute Genre_Musical {TRUE, FALSE}\n"
				+"@attribute Genre_Short {TRUE, FALSE}\n"
				+"@attribute Genre_Adventure {TRUE, FALSE}\n"
				+"@attribute Genre_Horror {TRUE, FALSE}\n"
				+"@attribute Genre_Romance {TRUE, FALSE}\n"
				+"@attribute Genre_Sci-Fi {TRUE, FALSE}\n"
				+"@attribute Genre_Drama {TRUE, FALSE}\n"
				+"@attribute Genre_Music {TRUE, FALSE}\n"
				+"@attribute Genre_Game-Show {TRUE, FALSE}\n"
				+"@attribute Genre_Crime {TRUE, FALSE}\n"
				+"@attribute Genre_Fantasy {TRUE, FALSE}\n"
				+"@attribute Genre_Animation {TRUE, FALSE}\n"
				+"@attribute Genre_Family {TRUE, FALSE}\n"
				+"@attribute Genre_Reality-TV {TRUE, FALSE}\n"
				+"@attribute ratio_rentabilite NUMERIC\n"
//				+"@attribute ratio_rentabilitee_disc NUMERIC\n"
//				+"@attribute score_IMBD_disc NUMERIC\n\n"
				+"@data\n";

		header = header.replace("\n", "\r\n");
		f.write(header.getBytes());
		String s;
		boolean h = true;
		while((s = r.readLine()) != null) {
			if(h) {
				h = false;
				continue;
			}
			f.write((s + "\n").replace("\n", "\r\n").getBytes());
			System.out.println(s);
		}
		f.close();
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
			String genres = file.getCell(i, Constants.INPUT_GENRES_POSITION).trim();
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
					movieGenres.add(parts[j].trim());
				else
					System.out.println(parts[j] + " skipped");
			}

			//Movie genre arrays created
			//Time to create the movie genres columns in the file

			System.out.println("movie genres array size : " + movieGenres.size());
		}		
		for (Iterator<String> iterator = movieGenres.iterator(); iterator.hasNext();) {
			String string = iterator.next().trim();
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
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(name + ".csv"), "UTF-8"), ',');
		CSVWriter writer = new CSVWriter(new FileWriter(name + "_output.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
		String[] entries = null;

		String[] headerString;
		while((entries = reader.readNext()) != null) {
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(entries));
			//locally save temp data : 
			String genres = entries[Constants.INPUT_GENRES_POSITION];
			//Remove unused data :
			if(header) {
				headerString = entries;
				for(String genre : movieGenres) {
					list.add("Genre_" + genre);
				}
				list.add("ratio_rentabilite");
//				list.add("ratio_rentabilitee_disc");
//				list.add("score_IMBD_disc");
				header = false;
			} else {
				for(String genre : movieGenres) {
					if(genres.contains(genre)) {
						list.add("TRUE");
					} else {
						list.add("FALSE");
					}
				}
				for(String s : list) {
					try {
						double d = Double.parseDouble(s);
						System.out.println(d);
					} catch (NumberFormatException e) {
						if(!s.equals("?") && !s.equals("TRUE") && !s.equals("FALSE")) {
							StringBuilder sb = new StringBuilder();
							String accentremove = StringUtils.stripAccents(new String(s).replaceAll("[ÀÁÂÃÄÈÉÊËÍÌÎÏÙÚÛÜÒÓÔÕÖÑÇªº§³²¹àáâãäèéêëíìîïùúûüòóôõöñç]", " ")).trim().toLowerCase();
							sb.append("\"").append(accentremove).append("\"");
							list.set(list.indexOf(s), sb.toString());
						}
					}
				}
				try{
					double gross = Double.parseDouble(list.get(8));
					double budget = Double.parseDouble(list.get(22));
					list.add("" + (gross / budget));
				} catch (Exception e) {
					list.add("?");
				}
			}
//			list.remove(Constants.INPUT_GENRES_POSITION); //Remove movie genres
			String[] array = list.toArray(new String[0]);
			writer.writeNext(array, false);
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
