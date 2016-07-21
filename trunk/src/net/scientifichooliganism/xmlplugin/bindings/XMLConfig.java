package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Configuration;
import net.scientifichooliganism.javaplug.vo.BaseConfiguration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="config")
public class XMLConfig extends XMLValueObject implements Configuration {
	public XMLConfig () {
		this(new BaseConfiguration());
	}

	public XMLConfig(Configuration delegate){
		super(delegate);
	}

	@Override
	public String getModule() {
		return ((Configuration)delegate).getModule();
	}

	@Override
	public void setModule(String in) {
		((Configuration)delegate).setModule(in);
	}

	@Override
	public int getSequence() {
		return ((Configuration)delegate).getSequence();
	}

	@Override
	public void setSequence(int in) {
		((Configuration)delegate).setSequence(in);
	}

	@Override
	public String getKey() {
		return ((Configuration)delegate).getKey();
	}

	@Override
	public void setKey(String in) {
		((Configuration)delegate).setKey(in);
	}

	@Override
	public String getValue() {
		return ((Configuration)delegate).getValue();
	}

	@Override
	public void setValue(String in) {
		((Configuration)delegate).setValue(in);
	}
}
