package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Configuration;
import net.scientifichooliganism.javaplug.vo.BaseConfiguration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="config")
public class XMLConfig extends XMLValueObject implements Configuration {
	Configuration delegate;

	public XMLConfig () {
		this(new BaseConfiguration());
	}

	public XMLConfig(Configuration delegate){
		super(delegate);
		this.delegate = delegate;
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
	public int getSequence() {
		return delegate.getSequence();
	}

	@Override
	public void setSequence(int in) {
		delegate.setSequence(in);
	}

	@Override
	public String getKey() {
		return delegate.getKey();
	}

	@Override
	public void setKey(String in) {
		delegate.setKey(in);
	}

	@Override
	public String getValue() {
		return delegate.getValue();
	}

	@Override
	public void setValue(String in) {
		delegate.setValue(in);
	}
}
