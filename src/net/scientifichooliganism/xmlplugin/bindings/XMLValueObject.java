package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.ValueObject;
import net.scientifichooliganism.javaplug.vo.BaseValueObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="value_object")
public class XMLValueObject implements ValueObject {
    ValueObject delegate;

    public XMLValueObject(){
        this(new BaseValueObject());
    }

    public XMLValueObject(ValueObject delegate){
        this.delegate = delegate;
    }

    public ValueObject getDelegate(){
        return delegate;
    }

    @Override
    public String getID(){
        return delegate.getID();
    }

    @Override
    @XmlElement(name="id")
    public void setID(String in){
        delegate.setID(in);
    }

    @Override
    public String getLabel() {
        return delegate.getLabel();
    }

    @Override
    public void setLabel(String in) {
        delegate.setLabel(in);
    }


    @Override
    public int compareTo(Object o) {
        return delegate.compareTo(o);
    }
}
