import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InstructionProcessor {
	
	public Queue<Instruction> instructieLijst;

	public Queue<Instruction> getInstructions(String fileVar) {
		instructieLijst = new LinkedList<>();
		try {
			File file;
			if(fileVar.equals("3 processen")) file = new File("Instructions_30_3.xml");
			else if(fileVar.equals("4 processen")) file = new File("Instructions_20000_4.xml");
			else file = new File("Instructions_20000_20.xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("instruction");
			for(int itr = 0; itr < nl.getLength(); itr++) {
				Node node = nl.item(itr);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					int pID = Integer.parseInt(e.getElementsByTagName("processID").item(0).getTextContent());
					String op = e.getElementsByTagName("operation").item(0).getTextContent();
					int add = Integer.parseInt(e.getElementsByTagName("address").item(0).getTextContent());
					instructieLijst.add(new Instruction(pID, op, add));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return instructieLijst;
	}
}
