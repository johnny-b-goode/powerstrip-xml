package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.xmlplugin.bindings.XMLValueObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class XMLTranslator {

    public static <T extends XMLValueObject> Node nodeFromObject(T object) {
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

	public static <T extends XMLValueObject> String stringFromObject(T object){
		return nodeFromObject(object).toString();
	}

	public static <T extends XMLValueObject> T objectFromString(String string){
		Document result;
		T ret = null;
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

		return (T)ret;
	}

    /*Turn a NodeList into an object*/
	public static <T extends XMLValueObject> T objectFromNode (Node n) {
		if (n == null) {
			throw new RuntimeException ("objectFromNodeList(NodeList) NodeList is null");
		}

		if (n.getChildNodes().getLength() <= 0) {
			throw new RuntimeException ("objectFromNodeList(NodeList) NodeList is empty");
		}

		T ret = null;
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
//			System.out.println("Klass: " + klass.getName());
			for(Annotation annotation : klass.getAnnotations()){
//				System.out.println("    annotation: " + annotation.toString());
			}
			for(Method m : klass.getMethods()){
//				System.out.println("    Method: " + m.getName());
				for(Annotation a : m.getAnnotations()){
//					System.out.println("        annotation: " + a);
				}
			}
			JAXBContext context = JAXBContext.newInstance(klass);
			Unmarshaller outlaw = context.createUnmarshaller();
			T obj = (T)(klass.cast(outlaw.unmarshal(n)));
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
}
