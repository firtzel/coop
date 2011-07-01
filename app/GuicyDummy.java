import com.google.inject.Injector;

import play.modules.guice.GuiceSupport;
import com.google.inject.*;

public class GuicyDummy extends GuiceSupport {
	protected Injector configure() {
		return Guice.createInjector(
		// your modules should come here
				);
	}
}
