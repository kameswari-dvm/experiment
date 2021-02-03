package files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDocToFile {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		URL url = new URL("https://services.mascus.com/api/getexport.aspx?exportid=autogilles");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document doc = db.parse(url.openStream());
		writeXmlDocumentToXmlFile(doc,"mascus");
	}
		private static void writeXmlDocumentToXmlFile(Document xmlDocument, String fileName)
		{
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer;
		    try {
		        transformer = tf.newTransformer();
		         
		        //Uncomment if you do not require XML declaration
		        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		         
		        //Write XML to file
		        FileOutputStream outStream = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/test/resource"+fileName+".xml")); 
		 
		        transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
		        System.out.println("Tranferred xml file content into local file");
		    } 
		    catch (TransformerException e) 
		    {
		        e.printStackTrace();
		    }
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		    }
		}
	}


