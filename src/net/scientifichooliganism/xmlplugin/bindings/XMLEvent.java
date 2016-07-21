package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Event;
import net.scientifichooliganism.javaplug.vo.BaseEvent;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
public class XMLEvent extends XMLValueObject implements Event{
    public XMLEvent() {
        this(new BaseEvent());
    }

    public XMLEvent(Event delegate){
        super(delegate);
    }
}
