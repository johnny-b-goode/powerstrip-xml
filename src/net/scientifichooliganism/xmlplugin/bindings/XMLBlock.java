package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Block;
import net.scientifichooliganism.javaplug.vo.BaseBlock;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="block")
public class XMLBlock extends XMLValueObject implements Block {
    Block delegate;

    public XMLBlock(){
        this(new BaseBlock());
    }

    public XMLBlock (Block block){
        super(block);
        delegate = super.getDelegate();
    }
}
