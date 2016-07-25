package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.interfaces.*;
import net.scientifichooliganism.javaplug.vo.BaseAction;
import net.scientifichooliganism.xmlplugin.bindings.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

public class XMLPlugin implements Plugin {
	private static XMLPlugin instance;

	private XMLPlugin(){

	}

	public static void main(String args[]){
		Action action = new BaseAction();
		action.setName("My Action Name");
		action.setMethod("New method");
		action.setURL("google.com");

		action.setKlass("test class");
		action.setDescription("test description");
		action.setModule("Some module");

		XMLPlugin plugin = new XMLPlugin();
		plugin.nodeFromObject(action);
	}

	public static XMLPlugin getInstance(){
		if(instance == null){
			instance = new XMLPlugin();
		}
		return instance;
	}

    public <T extends ValueObject> Node nodeFromObject(T object) {
		ValueObject vo = validateObject(object);
		Document doc = null;


		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.newDocument();

			JAXBContext jc = JAXBContext.newInstance(vo.getClass());
			Marshaller marshaller = jc.createMarshaller();
			Binder<Node> binder = jc.createBinder();

			marshaller.marshal(vo, doc);
		}
		catch (Exception exc){
			exc.printStackTrace();
		}

		return doc.getFirstChild();
	}

	public <T extends ValueObject> String stringFromObject(T object){
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
			XMLValueObject obj = (XMLValueObject) (klass.cast(outlaw.unmarshal(n)));
			//set the label on the object.
			URL url = URI.create(n.getBaseURI()).toURL();
			String label = URLDecoder.decode(url.toString(), "UTF-8");

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
			ret = obj.getDelegate();
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

	private <T extends ValueObject> T validateObject(T object){
		Class annotationClass = javax.xml.bind.annotation.XmlRootElement.class;
		T ret = null;
		if(object.getClass().getAnnotation(annotationClass) != null){
			return object;
		} else {
			// TODO: reconsider if this can be a switch
			if(object instanceof Action){
				ret = (T)(new XMLAction((Action)object));
			} else if(object instanceof Application){
				ret = (T)(new XMLApplication((Application)object));
			} else if(object instanceof Block) {
				ret = (T)(new XMLBlock((Block)object));
			} else if(object instanceof Configuration) {
				ret = (T)(new XMLConfiguration((Configuration)object));
			} else if(object instanceof Environment) {
				ret = (T)(new XMLEnvironment((Environment)object));
			} else if(object instanceof Event) {
				ret = (T)(new XMLEvent((Event)object));
			} else if(object instanceof Release) {
				ret = (T)(new XMLRelease((Release)object));
			} else if(object instanceof Task) {
				ret = (T)(new XMLTask((Task)object));
			} else if(object instanceof TaskCategory) {
				ret = (T)(new XMLTaskCategory((TaskCategory)object));
			} else if(object instanceof Transaction) {
				ret = (T)(new XMLTransaction((Transaction)object));
			} else if(object instanceof ValueObject) {
				ret = (T)(new XMLValueObject((ValueObject)object));
			}
		}
		return ret;
	}

}
