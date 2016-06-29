package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Event;
import net.scientifichooliganism.javaplug.vo.BaseEvent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
public class XMLEvent extends BaseEvent implements Event {
    public XMLEvent () {
        super();
    }

    @Override
    @XmlElement(name="id")
    public void setID(int in){
        super.setID(in);
    }

    @Override
    public int getID(){
        return super.getID();
    }
}
