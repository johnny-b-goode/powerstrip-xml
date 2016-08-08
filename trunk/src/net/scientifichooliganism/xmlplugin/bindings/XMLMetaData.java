package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.MetaData;
import net.scientifichooliganism.javaplug.vo.BaseMetaData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="meta-data")
public class XMLMetaData extends XMLValueObject implements MetaData {
    public XMLMetaData(){
        this(new BaseMetaData());
    }

    public XMLMetaData(MetaData metaData){
        super(metaData);
    }

    @Override
    public String getObject() {
        return ((MetaData)delegate).getObject();
    }

    @Override
    public void setObject(String s) {
        ((MetaData)delegate).setObject(s);
    }

    @Override
    public String getObjectID() {
        return ((MetaData)delegate).getObjectID();
    }

    @Override
    public void setObjectID(String s) {
        ((MetaData)delegate).setObject(s);
    }

    @Override
    public int getSequence() {
        return ((MetaData)delegate).getSequence();
    }

    @Override
    public void setSequence(int i) {
        ((MetaData)delegate).setSequence(i);
    }

    @Override
    public String getKey() {
        return ((MetaData)delegate).getKey();
    }

    @Override
    public String getValue() {
        return ((MetaData)delegate).getValue();
    }

    @Override
    public void setValue(String s) {
        ((MetaData)delegate).setValue(s);
    }

    @Override
    public void setKey(String s) {
        ((MetaData)delegate).setKey(s);
    }
}
