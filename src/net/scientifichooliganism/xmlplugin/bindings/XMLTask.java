package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Task;
import net.scientifichooliganism.javaplug.vo.BaseTask;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task")
public class XMLTask extends XMLValueObject implements Task {
    protected Task delegate;

    public XMLTask(){
        this(new BaseTask());
    }

    public XMLTask (Task task) {
        super(task);
        delegate = super.getDelegate();
    }
}
