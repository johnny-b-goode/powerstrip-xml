package net.scientifichooliganism.xmlplugin;

import com.sun.org.apache.xerces.internal.impl.xs.opti.NodeImpl;
import net.scientifichooliganism.javaplug.annotations.Param;
import net.scientifichooliganism.javaplug.interfaces.Plugin;
import net.scientifichooliganism.javaplug.util.JavaLogger;
import net.scientifichooliganism.javaplug.util.LumberJack;
import net.scientifichooliganism.javaplug.util.SpringBoard;
import net.scientifichooliganism.javaplug.vo.Action;
import net.scientifichooliganism.xmlplugin.bindings.XMLAction;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.StringWriter;

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

    public Node nodeFromObject(@Param(name="object") Object object) {
        Class klass = object.getClass();
		Node node = new NodeImpl();
		try {
			JAXBElement jaxbElement = new JAXBElement(new QName(klass.getName()), klass, object);
			JAXBContext context = JAXBContext.newInstance(klass);

			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(jaxbElement, node);

		} catch (Exception exc){
			logger.logException(exc);
		}

		return node;
	}

	public String stringFromObject(@Param(name="object") Object object){
		Class klass = object.getClass();
		StringWriter sw = new StringWriter();

        if(object instanceof Action){
			klass = XMLAction.class;
			object = new XMLAction((Action)object);
		}
		try {
			JAXBElement jaxbElement = new JAXBElement(new QName(klass.getName()), klass, object);

			JAXBContext context = JAXBContext.newInstance(klass);

			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(jaxbElement, sw);

		} catch (Exception exc){
			logger.logException(exc);
		}

		return sw.toString();
	}


	public Object objectFromString(@Param(name="node") String string){
		Document result;
		Object ret = null;
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			result = factory.newDocumentBuilder().parse(new InputSource(new StringReader(string)));

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
        return objectFromNode((Node) node);
	}

    /*Turn a NodeList into an object*/
	public Object objectFromNode (@Param(name="node") Node n) {
		if (n == null) {
			throw new RuntimeException("objectFromNodeList(NodeList) NodeList is null");
		}
		if (n.getChildNodes().getLength() <= 0) {
			throw new RuntimeException("objectFromNodeList(NodeList) NodeList is empty");
		}

		String type = n.getNodeName().trim();
		Class klass = null;
		try {
			klass = Class.forName(type);
		} catch (Exception exc) {
            logger.warn(type + " not found, attempting to use default package");
            String defaultType = "net.scientifichooliganism.javaplug.vo." + type;
			try {
				klass = Class.forName(defaultType);
			} catch (Exception exc2){
                logger.logException(exc2);
			}
		}

		Object ret = null;
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(klass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			ret = unmarshaller.unmarshal(n, klass);
		} catch (Exception exc) {
			logger.logException(exc);
		}

		if (ret == null) {
			throw new RuntimeException("XMLPlugin.objectFromNode() could not unmarshal object");
		}

		if (ret.getClass() == JAXBElement.class) {
			JAXBIntrospector introspector = jc.createJAXBIntrospector();
			ret =  introspector.getValue(ret);
		}

		if (ret.getClass() == XMLAction.class) {
			return ((XMLAction)ret).getDelegate();
		} else {
			return ret;
		}
	}

	@Override
	public String[][] getActions() {
		return new String[0][];
	}

}
