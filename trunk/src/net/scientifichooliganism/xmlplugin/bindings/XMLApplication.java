package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Application;
import net.scientifichooliganism.javaplug.vo.BaseApplication;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="application")
public class XMLApplication extends XMLValueObject implements Application {
    public XMLApplication(){
        this(new BaseApplication());
    }

    public XMLApplication(Application delegate){
        super(delegate);
    }

    @Override
    public String getName() {
        return ((Application)delegate).getName();
    }

    @Override
    public void setName(String s) {
        ((Application)delegate).setName(s);
    }

    @Override
    public String getDescription() {
        return ((Application)delegate).getDescription();
    }

    @Override
    public void setDescription(String s) {
        ((Application)delegate).setDescription(s);
    }
}
