package net.scientifichooliganism.xmlplugin.bindings;


import net.scientifichooliganism.javaplug.interfaces.Transaction;
import net.scientifichooliganism.javaplug.vo.BaseTransaction;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="transaction")
public class XMLTransaction extends BaseTransaction implements Transaction {
    public XMLTransaction(){
        super();
    }

    public void setID(int in){
        super.setID(in);
    }
}
