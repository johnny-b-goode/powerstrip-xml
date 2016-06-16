package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.vo.BaseAction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class XMLAction extends XMLValueObject implements Action {
	protected Action delegate;

	public XMLAction() {
		this(new BaseAction());
	}
	public XMLAction (Action action) {
		super(action);
		delegate = super.getDelegate();
	}

	@Override
	@XmlElement(name="class")
	public void setKlass (String in) throws IllegalArgumentException {
		delegate.setKlass(in);
	}
}