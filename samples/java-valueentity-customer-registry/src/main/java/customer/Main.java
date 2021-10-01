/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package customer;

import com.akkaserverless.javasdk.AkkaServerless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import customer.action.CustomerActionImpl;
import customer.domain.CustomerValueEntity;
import customer.view.CustomerByEmailView;
import customer.view.CustomerByNameView;
import customer.view.CustomerSummaryByNameView;
import customer.view.CustomersResponseByNameView;

public final class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  // tag::register[]
  public static AkkaServerless createAkkaServerless() {
    return AkkaServerlessFactory.withComponents(
      // end::register[]
      CustomerValueEntity::new,
      CustomerActionImpl::new,
      CustomerByEmailView::new,
      CustomerByNameView::new,
      CustomerSummaryByNameView::new,
      CustomersResponseByNameView::new);
      /*
      // tag::register[]
      return AkkaServerlessFactory.withComponents(
        CustomerValueEntity::new,
        CustomerByNameView::new);
      // end::register[]
      */
      // tag::register[]
      
  }
  // end::register[]

  public static void main(String[] args) throws Exception {
    LOG.info("starting the Akka Serverless service");
    createAkkaServerless().start();
  }
}