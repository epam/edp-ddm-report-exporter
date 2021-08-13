package com.epam.digital.data.platform.reportexporter;

import com.epam.digital.data.platform.reportexporter.controller.ReportController;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class SimpleTest {

	@Test
	void testControllerMethods() {
		var controller = new ReportController();

		var result = controller.getAllDashboards();
		try {
			controller.getDashboardArchive("stub");
		} catch (IOException e) {

		}

		Assertions.assertThat(result.getBody().size()).isEqualTo(1);
	}

}
