package hello;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import hello.model.ui.contract.UIAirContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/complete")
public class GreetingController {

    private static Logger LOG = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final RestValidator restValidator;

    @Autowired
    public GreetingController(RestValidator restValidator) {
        this.restValidator = restValidator;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST, headers = "Accept=application/json")
    public Long createAirContract(@RequestBody UIAirContract contract) {
        restValidator.validate(contract);
        Random r = new Random();
        return new Long((r.nextInt(1000)));
    }
}
