package net.scientifichooliganism.xmlplugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class DateAdapter extends XmlAdapter<String, Date> {
	//TODO: This isn't (shouldn't be) thread safe, instantiate sdf when used instead.
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss+Z");

	@Override
	public String marshal (Date d) throws Exception {
		return sdf.format(d);
	}

	@Override
	public Date unmarshal (String s) throws Exception {
		return sdf.parse(s);
	}
}