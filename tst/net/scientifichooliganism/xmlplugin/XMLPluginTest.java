package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.interfaces.Action;
import net.scientifichooliganism.javaplug.interfaces.Task;
import net.scientifichooliganism.javaplug.vo.BaseAction;
import net.scientifichooliganism.javaplug.vo.BaseTask;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class XMLPluginTest {
    private SimpleDateFormat sdf;
	private XMLPlugin plugin;

	public XMLPluginTest(){
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
		String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<action>" +
						"<description>test description</description>" +
						"<class>test class</class>" +
						"<method>New method</method>" +
						"<module>Some module</module>" +
						"<name>My Action Name</name>" +
						"<URL>google.com</URL>" +
				"</action>";

		assertEquals(expectedResult, plugin.stringFromObject(action));
	}

	// TODO: Make ambiguous of timezone
	@Ignore
	@Test
	public void test02(){
		try {
			Task task = new BaseTask();
			task.setID("1");
			task.setLabel("test_label");
			task.setName("test_task-01");
			task.setDescription("a test task");
			task.setStartDate(sdf.parse("2016-01-01"));
			String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<task>" +
						"<id>1</id>" +
						"<label>test_label</label>" +
						"<concurrent>false</concurrent>" +
						"<description>a test task</description>" +
						"<exclusive>false</exclusive>" +
						"<name>test_task-01</name>" +
						"<startDate>2016-01-01T00:00:00-06:00</startDate>" +
					"</task>";
			assertEquals(expectedResult, plugin.stringFromObject(task));
		} catch (Exception exc){
			exc.printStackTrace();
            fail("Unexpected exception (" + exc.getClass().getSimpleName() + ") thrown.");
		}
	}
}