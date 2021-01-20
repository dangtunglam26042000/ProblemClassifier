import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args) {
		String url = "http://problemclassifier.appspot.com/";
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";

		// CSV file header
		final String FILE_HEADER = "id,link,type";
		final String fileName = "data/1.csv";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER);

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new Country object list to the CSV file
			try {
				Document doc = Jsoup.connect(url).get();
				for (int i = 1; i <= 22; i++) {
					Element tab = doc.getElementById("tab" + i);
					Elements trs = tab.getElementsByTag("tr");
					for (Element tr : trs) {
						Elements tds = tr.getElementsByTag("td");
						String link = tr.select("a").attr("href");
						String id = tds.get(0).text();
						String type = tds.get(1).text();
						fileWriter.append(id);
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(link);
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(type);
						fileWriter.append(NEW_LINE_SEPARATOR);
					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
}
