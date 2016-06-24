package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.interfaces.Plugin;
import net.scientifichooliganism.javaplug.interfaces.ValueObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.StringReader;

public class XMLPlugin implements Plugin {
	private static XMLPlugin instance;

	private XMLPlugin(){

	}

	public static XMLPlugin getInstance(){
		if(instance == null){
			instance = new XMLPlugin();
		}
		return instance;
	}

    public Node nodeFromObject(Object object) {
		DOMResult result = new DOMResult();

		try {
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = jc.createMarshaller();

			marshaller.marshal(object, result);
		}
		catch (Exception exc){
			exc.printStackTrace();
		}

		return result.getNode();
	}

	public String stringFromObject(Object object){
		return nodeFromObject(object).toString();
	}

	public Object objectFromString(String string){
		Document result;
		Object ret = null;
		try {
			result = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new InputSource(new StringReader(string)));

			if(!result.hasChildNodes()) {
				throw new RuntimeException("objectFromString(String) String does not contain a child");
			}

			ret = objectFromNode((Node) result.getChildNodes());
		}
		catch (Exception exc){
			exc.printStackTrace();
		}

		return ret;
	}

	public Object objectFromNode(Object object){
		return objectFromNode((Node)object);
	}

    /*Turn a NodeList into an object*/
	public Object objectFromNode (Node n) {
//		System.out.println("In objectFromNode");
//		System.out.println("Node: " + n);
		if (n == null) {
			throw new RuntimeException ("objectFromNodeList(NodeList) NodeList is null");
		}

		if (n.getChildNodes().getLength() <= 0) {
			throw new RuntimeException ("objectFromNodeList(NodeList) NodeList is empty");
		}

		Object ret = null;
		String type = n.getNodeName().trim();
		type = type.substring(0,1).toUpperCase() + type.substring(1);

		//child.getNodeName() should denote the object, so cast to an object of this type
		//
		//options
		//	1.) use AC to lookup appropriate methods
		//		the ac doesn't have core data types in at this point, unless
		//		I go back and add them, which would be quite a bit of stuff.
		//	2.) use reflection to map elements to properties
		//	3.) use JAXB

		if (type == null) {
			throw new RuntimeException("objectFromNodeList(NodeList) unable to determine object type");
		}

		try {
			Class klass = Class.forName("net.scientifichooliganism.xmlplugin.bindings.XML" + type);
			JAXBContext context = JAXBContext.newInstance(klass);
			Unmarshaller outlaw = context.createUnmarshaller();
			ValueObject obj = (ValueObject) (klass.cast(outlaw.unmarshal(n)));
			//set the label on the object.
			String label = n.getBaseURI();

			if (label == null) {
				throw new RuntimeException("objectFromNodeList(NodeList) label is null");
			}

			if (label.length() <= 0) {
				throw new RuntimeException("objectFromNodeList(NodeList) label is empty");
			}

			label = label.substring(label.indexOf("/") + 1).trim();

			if (label.length() <= 0) {
				throw new RuntimeException("objectFromNodeList(NodeList) label is empty after doing things to it");
			}

			obj.setLabel(label);
			//System.out.println(obj);
			ret = obj;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		return ret;
	}

	@Override
	public String[][] getActions() {
		return new String[0][];
	}
}
