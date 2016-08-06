@XmlJavaTypeAdapters({
	@XmlJavaTypeAdapter(value=DateAdapter.class, type=DateAdapter.class)
})
package net.scientifichooliganism.xmlplugin;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;