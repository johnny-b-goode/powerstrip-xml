package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.annotations.Param;
import net.scientifichooliganism.javaplug.interfaces.*;
import net.scientifichooliganism.javaplug.util.JavaLogger;
import net.scientifichooliganism.javaplug.util.LumberJack;
import net.scientifichooliganism.javaplug.util.SpringBoard;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;

public class XMLPlugin implements Plugin {
	private static XMLPlugin instance;
	private LumberJack logger;

	private XMLPlugin(){
		logger = JavaLogger.getInstanceForContext(this.getClass().getName());
	}

	public static XMLPlugin getInstance(){
		if(instance == null){
			instance = new XMLPlugin();
		}
		return instance;
	}

    public <T extends ValueObject> Node nodeFromObject(@Param(name="object") T object) {
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
			logger.logException(exc, SpringBoard.ERROR);
		}

		return doc.getFirstChild();
	}

	public String stringFromObject(@Param(name="object") Object object){
	    String ret = "";

		if(object instanceof Collection){
			for(Object o : (Collection)object){
				ret += stringFromObject(o);
			}
        } else {
			Node node = nodeFromObject((ValueObject)object);
			try {
				StringWriter writer = new StringWriter();
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(new DOMSource(node), new StreamResult(writer));
				ret = writer.toString();
			} catch (Exception exc) {
				logger.logException(exc, SpringBoard.ERROR);
			}
		}

		return ret;
	}

	public Object objectFromString(@Param(name="node") String string){
		Document result;
		Object ret = null;
		try {
			result = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new InputSource(new StringReader(string)));

			if(!result.hasChildNodes()) {
				throw new RuntimeException("objectFromString(String) String does not contain a child");
			}

			ret = objectFromNode((Node) result.getDocumentElement());
		}
		catch (Exception exc){
            logger.logException(exc, SpringBoard.ERROR);
		}

		return ret;
	}

	public Object objectFromNode(@Param(name="node") Object node){
		return objectFromNode((Node)node);
	}

    /*Turn a NodeList into an object*/
	public Object objectFromNode (@Param(name="node") Node n) {
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
			Class klass = classFromNodeName(type);
			if(klass == null){
				throw new RuntimeException("XMLPlugin.objectFromNode(Node) does not know about class: " + type);
			}
			JAXBContext context = JAXBContext.newInstance(klass, XMLMetaData.class);
			Unmarshaller outlaw = context.createUnmarshaller();
			XMLValueObject obj = (XMLValueObject) (klass.cast(outlaw.unmarshal(n)));
			//set the label on the object.
			if(n.getBaseURI() != null) {
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
			}
			//System.out.println(obj);
			ret = obj.getDelegate();
		}
		catch (Exception exc) {
            logger.logException(exc, SpringBoard.ERROR);
		}

		return ret;
	}

	private Class classFromNodeName(String nodeName){
		switch(nodeName){
			case "action":
			    return XMLAction.class;
			case "application":
				return XMLApplication.class;
			case "block":
				return XMLBlock.class;
			case "configuration":
				return XMLConfiguration.class;
			case "environment":
				return XMLEnvironment.class;
			case "event":
				return XMLEvent.class;
			case "meta-data":
				return XMLMetaData.class;
			case "release":
				return XMLRelease.class;
			case "task":
				return XMLTask.class;
			case "task_category":
				return XMLTaskCategory.class;
			case "transaction":
				return XMLTransaction.class;
			case "value_object":
				return XMLValueObject.class;
		}

		return null;
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
				ret = (T) (new XMLEvent((Event) object));
			} else if(object instanceof MetaData){
				ret = (T) (new XMLMetaData((MetaData)object));
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
