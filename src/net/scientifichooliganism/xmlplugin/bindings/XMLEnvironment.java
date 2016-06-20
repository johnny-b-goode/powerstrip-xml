package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Environment;
import net.scientifichooliganism.javaplug.vo.BaseEnvironment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="environment")
public class XMLEnvironment extends BaseEnvironment implements Environment {
    public XMLEnvironment(){
        super();
    }

    @Override
    @XmlElement(name="id")
    public void setID(int in){
        super.setID(in);
    }
}
