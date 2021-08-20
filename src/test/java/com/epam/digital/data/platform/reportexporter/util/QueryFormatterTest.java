package com.epam.digital.data.platform.reportexporter.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.digital.data.platform.reportexporter.model.Query;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class QueryFormatterTest {

  @Test
  void shouldReturnCorrectPageWithQueries() {
    var queries = stubQueries();

    var result = QueryFormatter.formatQueryList(queries);
    var resultQueries = result.getResults();

    assertThat(result.getPage()).isEqualTo(1);
    assertThat(result.getPageSize()).isEqualTo(2);
    assertThat(result.getCount()).isEqualTo(2);
    assertThat(resultQueries.size()).isEqualTo(2);
  }

  private Set<Query> stubQueries() {
    var first = new Query();
    first.setQuery("first");
    first.setName("first");

    var second = new Query();
    second.setQuery("second");
    second.setName("second");

    return Set.of(first, second);
  }
}
