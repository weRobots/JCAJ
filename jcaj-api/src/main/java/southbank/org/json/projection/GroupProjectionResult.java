package southbank.org.json.projection;

public interface GroupProjectionResult {

	public String getGroupedProperty();

	public Object getGroupedValue();

	public String getDisplayKey();

	public Object getValue();
}
