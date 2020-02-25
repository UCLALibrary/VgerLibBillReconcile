package edu.ucla.library.libservices.webservices.invoices.reconcile.generators;

import edu.ucla.library.libservices.webservices.invoices.reconcile.beans.PaidInvoice;
import edu.ucla.library.libservices.webservices.invoices.reconcile.db.mappers.PaidMapper;
import edu.ucla.library.libservices.webservices.invoices.reconcile.db.source.DataSourceFactory;

import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PaidGenerator
{
  private static final String QUERY = "query.bill";

  private Properties props;
  private DriverManagerDataSource ds;
  private List<PaidInvoice> payments;

  public PaidGenerator()
  {
    super();
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createBillSource(getProps());
  }

  public void setProps(Properties props)
  {
    this.props = props;
  }

  private Properties getProps()
  {
    return props;
  }

  @SuppressWarnings("unchecked")
  public List<PaidInvoice> getPayments()
  {
    makeConnection();
    payments = new JdbcTemplate(ds).query(getProps().getProperty(QUERY), new PaidMapper());
    return payments;
  }
}
