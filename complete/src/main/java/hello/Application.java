package hello;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warning");
        log.error("error");
        SpringApplication.run(Application.class, args);
    }
}
