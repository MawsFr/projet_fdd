import ijava.Files;
import extensions.CSVFile;


public class Parser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVFile file = new CSVFile("movie_metadata.csv", ',');
		for(int j = 0; j < file.rowCount(); ++j) {
			for(int i = 0; i < file.columnCount(); ++i) {
				System.out.print(file.getCell(j, i) + "		|	");
			}
			System.out.println();
		}

	}

}
