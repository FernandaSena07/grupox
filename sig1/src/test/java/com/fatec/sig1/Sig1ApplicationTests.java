package com.fatec.sig1;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fatec.sig1.model.Ong;
@SpringBootTest
class Sig1ApplicationTests {

	@Test
	void contextLoads() {
		Ong myClass = new Ong();
		  assertNull(myClass.getDataCadastro());  // JUnit assertion
	}

}
