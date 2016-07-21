package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.vo.BaseAction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class XMLAction extends XMLValueObject implements Action {
	public XMLAction () {
		this(new BaseAction());
	}

	public XMLAction(Action delegate){
		super(delegate);
	}

	@Override
	public String getName() {
		return ((Action)delegate).getName();
	}

	@Override
	public void setName(String in) {
		((Action)delegate).setName(in);
	}

	@Override
	public String getDescription() {
		return ((Action)delegate).getDescription();
	}

	@Override
	public void setDescription(String in) {
		((Action)delegate).setDescription(in);
	}

	@Override
	public String getModule() {
		return ((Action)delegate).getModule();
	}

	@Override
	public void setModule(String in) {
		((Action)delegate).setModule(in);
	}

	@Override
	public String getKlass() {
		return ((Action)delegate).getKlass();
	}

	@Override
	@XmlElement(name="class")
	public void setKlass(String in) {
		((Action)delegate).setKlass(in);
	}

	@Override
	public String getURL() {
		return ((Action)delegate).getURL();
	}

	@Override
	public void setURL(String in) {
		((Action)delegate).setURL(in);
	}

	@Override
	public String getMethod() {
		return ((Action)delegate).getMethod();
	}

	@Override
	public void setMethod(String in) {
		((Action)delegate).setMethod(in);
	}
}