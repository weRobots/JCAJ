package southbank.org.json.restriction;

import java.util.Map;

public interface Restriction {

	public boolean apply(Map<String, Object> result);
}
