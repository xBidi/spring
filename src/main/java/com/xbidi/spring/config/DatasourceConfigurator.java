package com.xbidi.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/** @author diegotobalina created on 24/06/2020 */
@Configuration
@Slf4j
public class DatasourceConfigurator {

  @Value("${spring.datasource.url}")
  private String datasourceUrl;

  @Value("${spring.datasource.ssl}")
  private String datasourceSsl;

  @Bean
  public DataSource dataSource() throws URISyntaxException {

    // jdbc url
    URI uri = new URI(datasourceUrl);
    String prefix = "jdbc:postgresql://";
    String username = uri.getUserInfo().split(":")[0];
    String password = uri.getUserInfo().split(":")[1];
    String host = uri.getHost();
    int port = uri.getPort();
    String path = uri.getPath();
    String dbUrl = String.format("%s%s:%d%s?sslmode=%s", prefix, host, port, path, datasourceSsl);

    // create datasource
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(dbUrl);
    dataSourceBuilder.username(username);
    dataSourceBuilder.password(password);
    return dataSourceBuilder.build();
  }
}
