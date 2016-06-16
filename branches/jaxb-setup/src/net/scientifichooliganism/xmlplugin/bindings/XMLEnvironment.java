package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Environment;
import net.scientifichooliganism.javaplug.vo.BaseEnvironment;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="environment")
public class XMLEnvironment extends XMLValueObject implements Environment{
    Environment delegate;

    public XMLEnvironment(){
        this(new BaseEnvironment());
    }

    public XMLEnvironment (Environment environment) {
        super(environment);
        delegate = super.getDelegate();
    }
}
