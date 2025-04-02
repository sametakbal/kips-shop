package com.akbal.kips.be;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KipsBeApplicationTests {

	@Test
	void contextLoads() {
		int a = 5;
		Assertions.assertEquals(2+3, a);
	}

}
