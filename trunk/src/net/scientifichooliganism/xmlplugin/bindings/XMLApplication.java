package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Application;
import net.scientifichooliganism.javaplug.vo.BaseApplication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="application")
public class XMLApplication extends BaseApplication implements Application {
    public XMLApplication(){
        super();
    }

    @Override
    @XmlElement(name="id")
    public void setID(int in){
        super.setID(in);
    }

    @Override
    public int getID(){
        return super.getID();
    }
}
