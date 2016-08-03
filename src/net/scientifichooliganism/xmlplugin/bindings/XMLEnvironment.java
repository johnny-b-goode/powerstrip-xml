package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Environment;
import net.scientifichooliganism.javaplug.vo.BaseEnvironment;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="environment")
public class XMLEnvironment extends XMLValueObject implements Environment{
    public XMLEnvironment() {
        this(new BaseEnvironment());
    }

    public XMLEnvironment(Environment delegate){
        super(delegate);
    }

    @Override
    public String getName() {
        return ((Environment)delegate).getName();
    }

    @Override
    public void setName(String s) {
        ((Environment)delegate).setName(s);
    }

    @Override
    public String getDescription() {
        return ((Environment)delegate).getDescription();
    }

    @Override
    public void setDescription(String s) {
        ((Environment)delegate).setDescription(s);
    }
}
