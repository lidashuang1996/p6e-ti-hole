package club.p6e.ti.hole.follower;

import club.p6e.ti.hole.follower.action.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.Comparator;
import java.util.Map;

@Configurable
@ServletComponentScan
@SpringBootApplication
public class P6eTiHoleFollowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(P6eTiHoleFollowerApplication.class, args);
	}

	@Bean
	public ChromeBrowserOpenAction beanChromeBrowserOpenAction() {
		return new ChromeBrowserOpenAction("1-1-1", "/Users/admin/Documents/2022/ti/chromedriver");
	}

	@Bean
	public ChromeBrowserCloseAction beanChromeBrowserCloseAction() {
		return new ChromeBrowserCloseAction();
	}

	@Bean
	public ApplicationRunner init(ApplicationContext applicationContext) {
		return args -> {
			final MetadataAction metadata = new MetadataAction();
			metadata.setBaseUrl("http://127.0.0.1:9233");
			metadata.setApplicationContext(applicationContext);
			final Map<String, OpenAction> map = applicationContext.getBeansOfType(OpenAction.class);
			map.values().stream().sorted(Comparator.comparing(
					item -> ((OrderAction) item).order())).forEach(item -> item.execute(metadata));
		};
	}
}
