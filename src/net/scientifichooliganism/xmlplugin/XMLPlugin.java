package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.interfaces.*;
import net.scientifichooliganism.xmlplugin.bindings.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public <T extends ValueObject> Node nodeFromObject(T object) {
		ValueObject vo = validateObject(object);

		DOMResult result = new DOMResult();

		try {
			JAXBContext jc = JAXBContext.newInstance(vo.getClass());
			Marshaller marshaller = jc.createMarshaller();

			marshaller.marshal(vo, result);
		}
		catch (Exception exc){
			exc.printStackTrace();
		}

		return result.getNode();
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

	private <T extends ValueObject> T validateObject(T object){
		Class annotationClass = javax.xml.bind.annotation.XmlRootElement.class;
		T ret = null;
		if(object.getClass().getAnnotation(annotationClass) != null){
			return object;
		} else {
			Class retClass = null;
			if(object instanceof Action){
				retClass = XMLAction.class;
			} else if(object instanceof Application){
				retClass = XMLApplication.class;
			} else if(object instanceof Block) {
				retClass = XMLBlock.class;
			} else if(object instanceof Configuration) {
				retClass = XMLConfig.class;
			} else if(object instanceof Environment) {
				retClass = XMLEnvironment.class;
			} else if(object instanceof Event) {
				retClass = XMLEvent.class;
			} else if(object instanceof Release) {
				retClass = XMLRelease.class;
			} else if(object instanceof Task) {
				retClass = XMLTask.class;
			} else if(object instanceof TaskCategory) {
				retClass = XMLTaskCategory.class;
			} else if(object instanceof Transaction) {
				retClass = XMLTransaction.class;
			} else if(object instanceof ValueObject) {
				retClass = XMLValueObject.class;
			}
			try {
				ret = (T)(retClass.getConstructor().newInstance());
				copyProperties(ret, object);
			} catch (Exception exc){
				exc.printStackTrace();
			}
		}
		return ret;
	}

	private <T extends ValueObject> void copyProperties(T target, T base){
		Method targetMethods[] = target.getClass().getMethods();
		for(Method m : targetMethods){
			if(m.getName().startsWith("set")){
				String getterString = m.getName().replace("set", "get");
				// Getter should have no parameters
				try {
					Method getter = base.getClass().getMethod(getterString);
					m.invoke(target, getter.invoke(base));
				} catch(InvocationTargetException exc) {
					// Many of these are generated however,
					// it does not affect us if these methods do not work.
					// The method not working in this case is what we want
				} catch (Exception exc){
					exc.printStackTrace();
				}
			}
		}
	}
}
