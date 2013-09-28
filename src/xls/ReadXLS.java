package xls;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadXLS {

	private File	inputFile;

	public ReadXLS(String inputFile) {
		this.inputFile = new File(inputFile);
	}

	public void read() throws IOException {
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputFile);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if (type == CellType.LABEL) {
						System.out.println("I got a label " + cell.getContents());
					}

					if (type == CellType.NUMBER) {
						System.out.println("I got a number " + cell.getContents());
					}

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public Object[][] getData() {
		Object[][] dados = null;
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputFile);
			Sheet sheet = w.getSheet(0);

			dados = new String[sheet.getRows() - 1][sheet.getColumns()];

			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					dados[i - 1][j] = cell.getContents();
				}
			}

		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		return dados;
	}

	public static void main(String[] args) throws IOException {
		ReadXLS test = new ReadXLS("src/xls/file.xls");
		test.read();
	}

}
