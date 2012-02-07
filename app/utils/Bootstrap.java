package utils;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.modules.siena.SienaFixtures;
import play.Logger;

@OnApplicationStart(async = false)
public class Bootstrap extends Job {

	public static void loadInitialData() {
		Logger.info("running Bootstrap.loadInitialData()");
		SienaFixtures.deleteDatabase();
		SienaFixtures.loadModels("initial-data.yml");
	}

	@Override
	public void doJob() throws Exception {
		Logger.info("running Bootstrap.doJob()");
		loadInitialData();
	}
}
