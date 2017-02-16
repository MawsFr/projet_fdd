import extensions.CSVFile;


public class Parser {

	private int[] stringColumns = {1, 2, 7, 10, 11, 12, 15, 17, 18, 20, 21, 22};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Parser().processFile("movie_metadata.csv");
	}
	
	public void processFile(String name) {
		this.processStrings(name, ',');
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

}
