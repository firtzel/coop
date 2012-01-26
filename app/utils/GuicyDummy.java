package utils;

import play.modules.guice.GuiceSupport;
import play.Logger;

import com.google.inject.*;

public class GuicyDummy extends GuiceSupport {

	protected Injector configure() {
		return Guice.createInjector(new AbstractModule() {
			public void configure() {
				bind(TestInter.class).to(Test.class);
				Logger.info("binding login manager");
//				bind(LoginManager.class).to(GaeLoginManager.class);
				bind(LoginManager.class).to(MockLoginManager.class);
			}
		});
	}
}
