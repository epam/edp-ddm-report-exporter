package com.epam.digital.data.platform.reportexporter.util;

import com.epam.digital.data.platform.reportexporter.model.Page;
import com.epam.digital.data.platform.reportexporter.model.Query;
import java.util.ArrayList;
import java.util.Set;

public class QueryFormatter {

  private QueryFormatter() {}

  public static Page<Query> formatQueryList(Set<Query> queries) {
    var queryPage = new Page<Query>();

    queryPage.setPage(1);
    queryPage.setPageSize(queries.size());
    queryPage.setCount(queries.size());
    queryPage.setResults(new ArrayList<>(queries));

    return queryPage;
  }
}
