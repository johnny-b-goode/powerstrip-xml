package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Block;
import net.scientifichooliganism.javaplug.vo.BaseBlock;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="block")
public class XMLBlock extends XMLValueObject implements Block {
    public XMLBlock() {
        this(new BaseBlock());
    }

    public XMLBlock(Block delegate){
        super(delegate);
    }

    @Override
    public String getObjectBlocked() {
        return ((Block)delegate).getObjectBlocked();
    }

    @Override
    public void setObjectedBlocked(String s) {
        ((Block)delegate).setObjectedBlocked(s);
    }

    @Override
    public String getInstanceBlocked() {
        return ((Block)delegate).getInstanceBlocked();
    }

    @Override
    public void setInstanceBlocked(String s) {
        ((Block)delegate).setInstanceBlocked(s);
    }
}
