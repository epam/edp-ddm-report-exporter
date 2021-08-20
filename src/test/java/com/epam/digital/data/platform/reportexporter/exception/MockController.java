package com.epam.digital.data.platform.reportexporter.exception;

import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.util.List;
import javax.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

  private MockService mockService;

  public MockController(MockService mockService) {
    this.mockService = mockService;
  }

  @GetMapping("/{slug_id}")
  public ResponseEntity<Resource> getArchive(@PathVariable("slug_id") String slugId) {
    var zip = mockService.getArchive(slugId);

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header("STUB", "STUB")
        .body(zip);
  }

  @PostMapping
  public ResponseEntity<List<Dashboard>> getDashboards(@Valid @RequestBody MockEntity entity) {
    var id = mockService.getDashboards();

    return ResponseEntity.ok().body(id);
  }
}
