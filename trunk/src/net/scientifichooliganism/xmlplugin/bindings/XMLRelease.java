package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Release;
import net.scientifichooliganism.javaplug.vo.BaseRelease;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="release")
public class XMLRelease extends BaseRelease implements Release {
    public XMLRelease(){
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
