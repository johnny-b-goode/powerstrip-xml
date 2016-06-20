package net.scientifichooliganism.xmlplugin.bindings;


import net.scientifichooliganism.javaplug.interfaces.Transaction;
import net.scientifichooliganism.javaplug.vo.BaseTransaction;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="transaction")
public class XMLTransaction extends XMLValueObject implements Transaction {
    protected Transaction delegate;

    public XMLTransaction(){
        this(new BaseTransaction());
    }

    public XMLTransaction (Transaction transaction) {
        super(transaction);
        delegate = super.getDelegate();
    }
}