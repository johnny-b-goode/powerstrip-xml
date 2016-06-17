package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.ValueObject;
import net.scientifichooliganism.javaplug.vo.BaseValueObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public class XMLValueObject implements ValueObject {
    protected ValueObject delegate;

    public XMLValueObject(){
        this(new BaseValueObject());
    }

    public XMLValueObject(ValueObject object) {
        delegate = object;
    }

    @Override
    public <T extends ValueObject> T getDelegate(){
        return (T)delegate;
    }

    @Override
    @XmlElement(name="id")
    public void setID(int in){
        delegate.setID(in);
    }
}
