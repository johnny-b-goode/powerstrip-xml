package net.scientifichooliganism.xmlplugin;

import java.util.Date;
import java.text.SimpleDateFormat;

import net.scientifichooliganism.javaplug.annotations.Param;
import net.scientifichooliganism.javaplug.interfaces.*;
import net.scientifichooliganism.javaplug.vo.BaseAction;
import net.scientifichooliganism.javaplug.vo.BaseTask;
import net.scientifichooliganism.xmlplugin.bindings.*;

public class XMLPluginTest {

	private XMLPluginTest(){

	}

	public static void main(String args[]){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			XMLPlugin plugin = XMLPlugin.getInstance();

			Action action = new BaseAction();
			action.setName("My Action Name");
			action.setMethod("New method");
			action.setURL("google.com");
			action.setKlass("test class");
			action.setDescription("test description");
			action.setModule("Some module");
			System.out.println(plugin.stringFromObject(action));

			Task task = new BaseTask();
			task.setID("1");
			task.setLabel("test_label");
			task.setName("test_task-01");
			task.setDescription("a test task");
			task.setStartDate(sdf.parse("2016-01-01"));
			System.out.println(plugin.stringFromObject(task));
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}