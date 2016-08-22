package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.interfaces.Task;
import net.scientifichooliganism.javaplug.vo.BaseAction;
import net.scientifichooliganism.javaplug.vo.BaseTask;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class XMLPluginTest {
    private SimpleDateFormat sdf;
	private XMLPlugin plugin;

	private XMLPluginTest(){
		sdf = null;
		plugin = null;
	}

	@Before
	public void init(){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		plugin = XMLPlugin.getInstance();
	}

	@Test
	public void constructorTest(){
		assertNotNull(sdf);
		assertNotNull(plugin);
	}

	@Test
	public void test01(){
		Action action = new BaseAction();
		action.setName("My Action Name");
		action.setMethod("New method");
		action.setURL("google.com");
		action.setKlass("test class");
		action.setDescription("test description");
		action.setModule("Some module");
		System.out.println(plugin.stringFromObject(action));
	}

	@Test
	public void test02(){
		try {
			Task task = new BaseTask();
			task.setID("1");
			task.setLabel("test_label");
			task.setName("test_task-01");
			task.setDescription("a test task");
			task.setStartDate(sdf.parse("2016-01-01"));
			System.out.println(plugin.stringFromObject(task));
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
}