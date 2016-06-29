package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Block;
import net.scientifichooliganism.javaplug.vo.BaseBlock;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="block")
public class XMLBlock extends BaseBlock implements Block {
    public XMLBlock(){
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
