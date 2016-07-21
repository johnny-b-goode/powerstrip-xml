package net.scientifichooliganism.xmlplugin.bindings;


import net.scientifichooliganism.javaplug.interfaces.TaskCategory;
import net.scientifichooliganism.javaplug.vo.BaseTaskCategory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task_category")
public class XMLTaskCategory extends XMLValueObject implements TaskCategory {
    public XMLTaskCategory() {
        this(new BaseTaskCategory());
    }

    public XMLTaskCategory(TaskCategory delegate){
        super(delegate);
    }
}
