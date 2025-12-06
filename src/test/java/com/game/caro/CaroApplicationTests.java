package com.game.caro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CaroApplicationTests {

	@Test
	void contextLoads() {
		String sayHellp = "Hello Wrolds"; 
		assertEquals("Hello Wrolds", sayHellp);
	}

}
