package entertainment.damir.summer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

@SpringBootApplication
public class SummerApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SummerApplication.class, args);




		context.stop();
	}

}
