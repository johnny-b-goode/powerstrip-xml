package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.vo.BaseAction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="action")
public class XMLAction extends XMLValueObject implements Action {
	Action delegate;

	public XMLAction () {
		this(new BaseAction());
	}

	public XMLAction(Action delegate){
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public void setName(String in) {
		delegate.setName(in);
	}

	@Override
	public String getDescription() {
		return delegate.getDescription();
	}

	@Override
	public void setDescription(String in) {
		delegate.setDescription(in);
	}

	@Override
	public String getModule() {
		return delegate.getModule();
	}

	@Override
	public void setModule(String in) {
		delegate.setModule(in);
	}

	@Override
	public String getKlass() {
		return delegate.getKlass();
	}

	@Override
	@XmlElement(name="class")
	public void setKlass(String in) {
		delegate.setKlass(in);
	}

	@Override
	public String getURL() {
		return delegate.getURL();
	}

	@Override
	public void setURL(String in) {
		delegate.setURL(in);
	}

	@Override
	public String getMethod() {
		return delegate.getMethod();
	}

	@Override
	public void setMethod(String in) {
		delegate.setMethod(in);
	}
}