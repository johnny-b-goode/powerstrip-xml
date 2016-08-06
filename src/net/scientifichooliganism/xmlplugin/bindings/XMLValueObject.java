package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.MetaData;
import net.scientifichooliganism.javaplug.interfaces.ValueObject;
import net.scientifichooliganism.javaplug.vo.BaseValueObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Vector;

@XmlRootElement(name="value_object")
public class XMLValueObject implements ValueObject {
    ValueObject delegate;

    public XMLValueObject(){
        this(new BaseValueObject());
    }

    public XMLValueObject(ValueObject delegate){
        Vector<MetaData> xmlMetaData = new Vector<>();
        for(MetaData md : delegate.getMetaData()){
            xmlMetaData.add(new XMLMetaData(md));
        }
        delegate.getMetaData().clear();
        delegate.getMetaData().addAll(xmlMetaData);
        this.delegate = delegate;
    }

    public ValueObject getDelegate(){
        Vector<MetaData> mdDelegates = new Vector<>();
        for(MetaData md : delegate.getMetaData()) {
            mdDelegates.add((MetaData)((XMLMetaData)md).getDelegate());
        }
        delegate.getMetaData().clear();
        delegate.getMetaData().addAll(mdDelegates);
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
    public void addMetaData(MetaData md){
        delegate.addMetaData(md);
    }

    @Override
    @XmlElement(name="meta-data", type=XMLMetaData.class)
    public Collection<MetaData> getMetaData(){
        return delegate.getMetaData();
    }

    @Override
    public void removeMetaData(MetaData md){
        delegate.removeMetaData(md);
    }
}

