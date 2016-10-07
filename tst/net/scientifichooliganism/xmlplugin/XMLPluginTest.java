package net.scientifichooliganism.xmlplugin;

import net.scientifichooliganism.javaplug.vo.Action;
import net.scientifichooliganism.javaplug.vo.Configuration;
import net.scientifichooliganism.javaplug.vo.MetaData;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		Action action = new Action();
		action.setName("My Action Name");
		action.setMethod("New method");
		action.setURL("google.com");
		action.setKlass("test class");
		action.setDescription("test description");
		action.setModule("Some module");
		String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
				"<net.scientifichooliganism.xmlplugin.bindings.XMLAction>" +
						"<description>test description</description>" +
						"<class>test class</class>" +
						"<method>New method</method>" +
						"<module>Some module</module>" +
						"<name>My Action Name</name>" +
						"<URL>google.com</URL>" +
				"</net.scientifichooliganism.xmlplugin.bindings.XMLAction>";

		assertEquals(expectedResult, plugin.stringFromObject(action));
	}

	@Test
	public void test02(){
		Configuration configuration = new Configuration();
		configuration.setID("2");
		configuration.setSequence(2);
		configuration.setValue("Test Value");
		configuration.setKey("test Key");
		MetaData md = new MetaData();
		md.setKey("key");
		md.setValue("value");
		md.setSequence(20);
		md.setID("20");
		MetaData md2 = new MetaData();
		md2.setKey("key2");
		md2.setValue("Value2");
		md2.setSequence(20);
		md2.setID("20");
		configuration.addMetaData(md);
		configuration.addMetaData(md2);

		String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
				"<net.scientifichooliganism.javaplug.vo.Configuration>" +
				"<ID>2</ID>" +
				"<key>test Key</key>" +
				"<sequence>2</sequence>" +
				"<value>Test Value</value>" +
				"</net.scientifichooliganism.javaplug.vo.Configuration>";
		String result = plugin.stringFromObject(configuration);

		Configuration back = (Configuration)plugin.objectFromString(result);
		assertEquals(expectedResult, result);
	}
}