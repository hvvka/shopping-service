package com.hania;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
@RestController
public class ShoppingController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/shopping")
    @ResponseBody
    public Article sayHello(@RequestParam(name = "item") String item,
                            @RequestParam(name = "amount", required = false, defaultValue = "0") int amount) {
        return new Article(counter.incrementAndGet(), item, amount);
    }
}
