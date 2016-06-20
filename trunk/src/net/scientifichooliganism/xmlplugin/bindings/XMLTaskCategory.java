package net.scientifichooliganism.xmlplugin.bindings;


import net.scientifichooliganism.javaplug.interfaces.TaskCategory;
import net.scientifichooliganism.javaplug.vo.BaseTaskCategory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="task_category")
public class XMLTaskCategory extends BaseTaskCategory implements TaskCategory {
    public XMLTaskCategory(){
        super();
    }

    @Override
    @XmlElement(name="id")
    public void setID(int in){
        super.setID(in);
    }
}
