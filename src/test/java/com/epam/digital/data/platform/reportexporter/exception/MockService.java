package com.epam.digital.data.platform.reportexporter.exception;

import static org.mockito.Mockito.mock;

import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.util.List;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.io.ByteArrayResource;

@TestComponent
public class MockService {

  public ByteArrayResource getArchive(String slug) {
    return mock(ByteArrayResource.class);
  }

  public List<Dashboard> getDashboards() {
    return mock(List.class);
  }

}
