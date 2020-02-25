package edu.ucla.library.libservices.webservices.invoices.reconcile.db.mappers;

import edu.ucla.library.libservices.webservices.invoices.reconcile.beans.PaidInvoice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PaidMapper
  implements RowMapper
{
  public PaidMapper()
  {
    super();
  }

  @Override
  public Object mapRow(ResultSet rs, int i)
    throws SQLException
  {
    PaidInvoice bean;

    bean = new PaidInvoice();
    bean.setInvoiceNumber(rs.getString("invoice_number"));
    bean.setPaymentDate(rs.getString("payment_date"));
    
    return bean;
  }
}
