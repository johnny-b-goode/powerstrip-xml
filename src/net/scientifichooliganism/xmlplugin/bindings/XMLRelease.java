package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Release;
import net.scientifichooliganism.javaplug.vo.BaseRelease;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name="release")
public class XMLRelease extends XMLValueObject implements Release {
    public XMLRelease(){
        this(new BaseRelease());
    }

    public XMLRelease(Release delegate){
        super(delegate);
    }

    @Override
    public String getApplication() {
        return ((Release)delegate).getApplication();
    }

    @Override
    public void setApplication(String s) {
        ((Release)delegate).setApplication(s);
    }

    @Override
    public String getName() {
        return ((Release)delegate).getName();
    }

    @Override
    public void setName(String s) {
        ((Release)delegate).setName(s);
    }

    @Override
    public String getDescription() {
        return ((Release)delegate).getDescription();
    }

    @Override
    public void setDescription(String s) {
        ((Release)delegate).setDescription(s);
    }

    @Override
    public Date getDueDate() {
        return ((Release)delegate).getDueDate();
    }

    @Override
    public void setDueDate(Date date) {
        ((Release)delegate).setDueDate(date);
    }

    @Override
    public Date getReleaseDate() {
        return ((Release)delegate).getReleaseDate();
    }

    @Override
    public void setReleaseDate(Date date) {
        ((Release)delegate).setReleaseDate(date);
    }
}
