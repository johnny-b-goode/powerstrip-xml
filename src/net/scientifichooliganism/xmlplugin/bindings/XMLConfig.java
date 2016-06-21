package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Configuration;
import net.scientifichooliganism.javaplug.vo.BaseConfiguration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="config")
public class XMLConfig extends BaseConfiguration implements Configuration {
	public XMLConfig () {
		super();
	}

	@Override
	@XmlElement(name="id")
	public void setID(int in) {
		super.setID(in);
	}
}