package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Release;
import net.scientifichooliganism.javaplug.vo.BaseRelease;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="release")
public class XMLRelease extends XMLValueObject implements Release {
    public XMLRelease(){
        this(new BaseRelease());
    }

    public XMLRelease(Release delegate){
        super(delegate);
    }
}
