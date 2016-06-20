package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.vo.BaseAction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class XMLAction extends BaseAction implements Action {
	public XMLAction () {
		super();
	}

	@Override
	@XmlElement(name="id")
	public void setID(int in) {
		super.setID(in);
	}

	@Override
	@XmlElement(name="class")
	public void setKlass (String in) throws IllegalArgumentException {
		super.setKlass(in);
	}
}