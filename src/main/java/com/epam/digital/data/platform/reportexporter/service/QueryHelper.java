package com.epam.digital.data.platform.reportexporter.service;

import static com.epam.digital.data.platform.reportexporter.util.ResponseHandler.handleResponse;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import com.epam.digital.data.platform.reportexporter.client.QueryClient;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import com.epam.digital.data.platform.reportexporter.model.Query;
import com.epam.digital.data.platform.reportexporter.model.Widget;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class QueryHelper {

  private final QueryClient queryClient;

  public QueryHelper(QueryClient queryClient) {
    this.queryClient = queryClient;
  }

  public Set<Query> getUtilQueries(Dashboard dashboard) {
    var queryIds = getUtilQueryIds(dashboard);
    var queries = getQueries(getQueryCount());

    return queries.stream()
        .filter(query -> queryIds.contains(query.getId()))
        .collect(toSet());
  }

  private Set<Integer> getUtilQueryIds(Dashboard dashboard) {
    return dashboard.getWidgets().stream()
        .flatMap(this::getParameterQueries)
        .collect(toSet());
  }

  private int getQueryCount() {
    return handleResponse(queryClient.getQueries(1)).getCount();
  }

  private List<Query> getQueries(int queryCount) {
    return handleResponse(queryClient.getQueries(queryCount)).getResults();
  }

  private Stream<Integer> getParameterQueries(Widget widget) {
    var options = widget.getVisualization().getQuery().getOptions();
    var queries = new HashSet<Integer>();

    var parameters = (List<Map<String, Object>>) options.get("parameters");

    for (var parameter : parameters) {
      if (parameter.get("type").equals("query")) {
        queries.add((Integer) parameter.get("queryId"));
      }
    }

    return queries.stream();
  }
}
