package southbank.org.json;

import java.util.Map;

public class GreaterThan implements Restriction {

	private final String table;
	private final String property;
	private final Double value;

	public GreaterThan(String table, String property, Double value) {
		this.table = table;
		this.property = property;
		this.value = value;
	}

	@Override
	public boolean apply(Map<String, Object> result) {

		if (result == null || result.isEmpty())
			return false;

		Object object = result.get(this.table + "." + this.property);

		if (object == null)
			object = result.get(this.property);

		if ((object == null) || !(object instanceof Double))
			return false;

		return (Double) object > this.value;
	}
}
