package edu.ucla.library.libservices.webservices.invoices.reconcile.main;

import edu.ucla.library.libservices.webservices.invoices.reconcile.beans.PaidInvoice;
import edu.ucla.library.libservices.webservices.invoices.reconcile.db.mappers.PaidMapper;
import edu.ucla.library.libservices.webservices.invoices.reconcile.db.source.DataSourceFactory;

import edu.ucla.library.libservices.webservices.invoices.reconcile.generators.PaidGenerator;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.log4j.Logger;

public class Reconciler
{
  private static final String QUERY = "query.vgr";
  private static final Logger logger = Logger.getLogger( Reconciler.class );

  private static Properties props;
  private static DriverManagerDataSource ds;
  private static List<PaidInvoice> payments;

  public Reconciler()
  {
    super();
  }

  public static void main(String[] args)
  {
    loadProperties( args[ 0 ] );
    makeConnection();
    loadPayments();
    updateVger();
  }

  public static Properties getProps()
  {
    return props;
  }

  private static void loadProperties(String propFile)
  {
    props = new Properties();
    try
    {
      props.load( new FileInputStream( new File( propFile ) ) );
    }
    catch ( IOException ioe )
    {
      logger.fatal( "problem with props file" + ioe.getMessage() );
      System.exit( -1 );
    }
  }

  private static void makeConnection()
  {
    ds = DataSourceFactory.createVgerSource(getProps());
  }

  private static void loadPayments()
  {
    PaidGenerator generator;

    generator = new PaidGenerator();
    generator.setProps(getProps());
    payments = generator.getPayments();
  }

  private static void updateVger()
  {
    for (PaidInvoice thePayment: payments)
    {
      int count;
      
      System.out.println("processing invoice " + thePayment.getInvoiceNumber());
      logger.info("processing invoice " + thePayment.getInvoiceNumber());
      count =
        new JdbcTemplate(ds)
        .update(getProps().getProperty(QUERY), new Object[] { thePayment.getPaymentDate(), thePayment.getInvoiceNumber() });
      if (count == 0)
      {
        System.out.println("\tno updates for invoice " + thePayment.getInvoiceNumber());
        logger.info("\tno updates for invoice " + thePayment.getInvoiceNumber());
      }
    }
  }
}
