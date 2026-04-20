package com.nttoan.handmadeshop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class HandmadeshopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationStartsSuccessfully() {
		// Test that the application context loads without errors
		assertThat(true).isTrue();
	}
}
