package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Task;
import net.scientifichooliganism.javaplug.vo.BaseTask;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task")
public class XMLTask extends XMLValueObject implements Task {
    public XMLTask() {
        this(new BaseTask());
    }

    public XMLTask(Task delegate){
        super(delegate);
    }
}
