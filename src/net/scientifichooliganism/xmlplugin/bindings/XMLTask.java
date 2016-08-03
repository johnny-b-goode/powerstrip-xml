package net.scientifichooliganism.xmlplugin.bindings;

import net.scientifichooliganism.javaplug.interfaces.Task;
import net.scientifichooliganism.javaplug.vo.BaseTask;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.Duration;
import java.util.Date;

@XmlRootElement(name="task")
public class XMLTask extends XMLValueObject implements Task {
    public XMLTask() {
        this(new BaseTask());
    }

    public XMLTask(Task delegate){
        super(delegate);
    }

    @Override
    public String getName() {
        return ((Task)delegate).getName();
    }

    @Override
    public void setName(String s) {
        ((Task)delegate).setName(s);
    }

    @Override
    public String getDescription() {
        return ((Task)delegate).getDescription();
    }

    @Override
    public void setDescription(String s) {
        ((Task)delegate).setDescription(s);
    }

    @Override
    public boolean getConcurrent() {
        return ((Task)delegate).getConcurrent();
    }

    @Override
    public void setConcurrent(boolean b) {
        ((Task)delegate).setConcurrent(b);

    }

    @Override
    public boolean getExclusive() {
        return ((Task)delegate).getExclusive();
    }

    @Override
    public void setExclusive(boolean b) {
        ((Task)delegate).setExclusive(b);

    }

    @Override
    public Duration getScheduledDuration() {
        return ((Task)delegate).getScheduledDuration();
    }

    @Override
    public void setScheduledDuration(Duration duration) {
        ((Task)delegate).setScheduledDuration(duration);

    }

    @Override
    public Date getStartDate() {
        return ((Task)delegate).getStartDate();
    }

    @Override
    public void setStartDate(Date date) {
        ((Task)delegate).setStartDate(date);

    }

    @Override
    public Date getCompletedDate() {
        return ((Task)delegate).getCompletedDate();
    }

    @Override
    public void setCompletedDate(Date date) {
        ((Task)delegate).setCompletedDate(date);
    }
}
