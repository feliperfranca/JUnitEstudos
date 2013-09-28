package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {

	private File inputFile;

	public ReadXML(String inputFile) {
		this.inputFile = new File(inputFile);
	}

	public void read() {

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element:" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("usuario");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Id: " + eElement.getAttribute("id"));
					System.out.println("Nome: " + eElement.getElementsByTagName("nome").item(0).getTextContent());
					System.out.println("Telefone: " + eElement.getElementsByTagName("telefone").item(0).getTextContent());
					System.out.println("Idade: " + eElement.getElementsByTagName("idade").item(0).getTextContent());
					System.out.println("Estado: " + eElement.getElementsByTagName("estado").item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] getData() {
		Object[][] dados = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("usuario");

			dados = new String [nList.getLength()][5];

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					dados[i][0] = eElement.getAttribute("id");
					dados[i][1] = eElement.getElementsByTagName("nome").item(0).getTextContent();
					dados[i][2] = eElement.getElementsByTagName("telefone").item(0).getTextContent();
					dados[i][3] = eElement.getElementsByTagName("idade").item(0).getTextContent();
					dados[i][4] = eElement.getElementsByTagName("estado").item(0).getTextContent();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dados;
	}

	public static void main(String[] args) {
		ReadXML test = new ReadXML("src/xml/file.xml");
		test.read();
	}

}