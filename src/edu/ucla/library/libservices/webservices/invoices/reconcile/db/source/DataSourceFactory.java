package edu.ucla.library.libservices.webservices.invoices.reconcile.db.source;

import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
    super();
  }

  public static DriverManagerDataSource createVgerSource(Properties props)
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName("oracle.jdbc.OracleDriver");
    ds.setUrl( props.getProperty( "vger.url" ) );
    ds.setUsername( props.getProperty( "vger.user" ) );
    ds.setPassword( props.getProperty( "vger.password" ) );

    return ds;
  }

  public static DriverManagerDataSource createBillSource(Properties props)
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName("oracle.jdbc.OracleDriver");
    ds.setUrl( props.getProperty( "bill.url" ) );
    ds.setUsername( props.getProperty( "bill.user" ) ); 
    ds.setPassword( props.getProperty( "bill.password" ) ); 

    return ds;
  }
}
