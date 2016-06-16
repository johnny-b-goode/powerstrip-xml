package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Configuration;
import net.scientifichooliganism.javaplug.vo.BaseConfiguration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="config")
public class XMLConfig extends XMLValueObject implements Configuration {
	Configuration delegate;

	public XMLConfig(){
		this(new BaseConfiguration());
	}

	public XMLConfig (Configuration configuration) {
		super(configuration);
		delegate = super.getDelegate();
	}
}