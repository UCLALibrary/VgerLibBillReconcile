package edu.ucla.library.libservices.webservices.invoices.reconcile.beans;

public class PaidInvoice
{
  private String invoiceNumber;
  private String paymentDate;
  
  public PaidInvoice()
  {
    super();
  }

  public void setInvoiceNumber(String invoiceNumber)
  {
    this.invoiceNumber = invoiceNumber;
  }

  public String getInvoiceNumber()
  {
    return invoiceNumber;
  }

  public void setPaymentDate(String paymentDate)
  {
    this.paymentDate = paymentDate;
  }

  public String getPaymentDate()
  {
    return paymentDate;
  }
}
