package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Application;
import net.scientifichooliganism.javaplug.vo.BaseApplication;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="application")
public class XMLApplication extends XMLValueObject implements Application {
    Application delegate;

    public XMLApplication(){
        this(new BaseApplication());
    }

    public XMLApplication(Application delegate){
        super(delegate);
        this.delegate = delegate;
    }
}
