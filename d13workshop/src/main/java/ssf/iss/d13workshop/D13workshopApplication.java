package ssf.iss.d13workshop;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class D13workshopApplication implements ApplicationRunner{ 
	// ./mvnw spring-boot:run -Dspring-boot.run.arguments="--dataDir=/Users/CelineNg/Desktop/d13workshop/data"

	public static void main(String[] args) {
		SpringApplication.run(D13workshopApplication.class, args);		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("dataDir")){
			final String dataDir = args.getOptionValues("dataDir").get(0); //get name of file to be created
			File fileDir = new File(dataDir);

			if (!fileDir.exists()){
				fileDir.mkdir();
				System.out.println("===" + fileDir.getAbsolutePath());
				System.out.println("===" + fileDir.getPath());
				System.out.println("===" + fileDir.getParent());
			}
		} else {
			System.out.println("--dataDir option is not specified");
		}
	}

}
