package net.scientifichooliganism.xmlplugin.bindings;


import net.scientifichooliganism.javaplug.interfaces.TaskCategory;
import net.scientifichooliganism.javaplug.vo.BaseTaskCategory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task_category")
public class XMLTaskCategory extends XMLValueObject implements TaskCategory {
    protected TaskCategory delegate;

    public XMLTaskCategory(){
        this(new BaseTaskCategory());
    }

    public XMLTaskCategory (TaskCategory taskCategory) {
        super(taskCategory);
        delegate = super.getDelegate();
    }
}
