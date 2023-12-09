package ssf.iss.day13class;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13classApplication implements ApplicationRunner { // runners are used to run the logic on application startup
	// ApplicationRunner run() will get executed, just after applicationcontext is created and before spring boot application startup.
	//it accepts the argument, which are passed at time of server startup.

	public static void main(String[] args) {
		SpringApplication.run(Day13classApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if (args.containsOption("dataDir")){
			final String dataDir =args.getOptionValues("dataDir").get(0);
			File fileDir = new File(dataDir);

			if (!fileDir.exists()){
				fileDir.mkdir();
				System.out.println("===" + fileDir.getAbsolutePath());
				System.out.println("===" + fileDir.getPath());
				System.out.println("===" + fileDir.getParent());
			} else {
				System.out.println(fileDir.getAbsolutePath());
			}
		}
	}

}
