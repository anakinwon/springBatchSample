package com.pisien.batchSample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@EnableJpaAuditing(modifyOnCreate = false)     // @EnableJpaAuditing(modifyOnCreate = false) 시 UpdateID는 null로 저장된다.
class BatchSampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
