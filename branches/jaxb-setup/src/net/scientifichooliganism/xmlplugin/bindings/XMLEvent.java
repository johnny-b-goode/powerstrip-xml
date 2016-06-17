package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Event;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
public class XMLEvent extends XMLValueObject implements Event {
    protected Event delegate;

    public XMLEvent (Event event) {
        super(event);
        delegate = super.getDelegate();
    }
}
