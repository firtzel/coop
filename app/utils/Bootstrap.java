package utils;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.Logger;

@OnApplicationStart(async = false)
public class Bootstrap extends Job {

	public static void loadInitialData() {
		// TODO for each model, check if there are instances of it - otherwise,
		// load the proper YAML file
		// if(User.count() == 0) {
		// SienaFixtures.loadModels("initial-data.yml");
		// }
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
