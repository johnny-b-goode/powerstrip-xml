package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.vo.Action;

import javax.xml.bind.annotation.XmlElement;

public class XMLAction {
    Action delegate;

	public XMLAction () {
		this(new Action());
	}

	public XMLAction(Action delegate){
		this.delegate = delegate;
	}

	public Action getDelegate(){
		return delegate;
	}

	public String getName() {
		return (delegate).getName();
	}

	public void setName(String in) {
		(delegate).setName(in);
	}

	public String getDescription() {
		return (delegate).getDescription();
	}

	public void setDescription(String in) {
		(delegate).setDescription(in);
	}

	public String getModule() {
		return (delegate).getModule();
	}

	public void setModule(String in) {
		(delegate).setModule(in);
	}

	public String getKlass() {
		return (delegate).getKlass();
	}

	@XmlElement(name="class")
	public void setKlass(String in) {
		(delegate).setKlass(in);
	}

	public String getURL() {
		return (delegate).getURL();
	}

	public void setURL(String in) {
		(delegate).setURL(in);
	}

	public String getMethod() {
		return (delegate).getMethod();
	}

	public void setMethod(String in) {
		(delegate).setMethod(in);
	}
}