/* This code was generated by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */
package customer.domain;

import com.akkaserverless.javasdk.eventsourcedentity.EventSourcedEntity;
import com.akkaserverless.javasdk.eventsourcedentity.EventSourcedEntityContext;
import com.akkaserverless.javasdk.testkit.EventSourcedResult;
import com.google.protobuf.Empty;
import customer.api.CustomerApi;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import scala.jdk.javaapi.CollectionConverters;

import static org.junit.Assert.*;

public class CustomerEntityTest {

  @Test
  public void exampleTest() {
    CustomerEntityTestKit testKit = CustomerEntityTestKit.of(CustomerEntity::new);
    // use the testkit to execute a command
    // of events emitted, or a final updated state:
    // EventSourcedResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the emitted events
    // ExpectedEvent actualEvent = result.getNextEventOfType(ExpectedEvent.class);
    // assertEquals(expectedEvent, actualEvent)
    // verify the final state after applying the events
    // assertEquals(expectedState, testKit.getState());
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void createTest() {
    CustomerEntityTestKit testKit = CustomerEntityTestKit.of(CustomerEntity::new);
    // EventSourcedResult<Empty> result = testKit.create(Customer.newBuilder()...build());
  }


  @Test
  public void changeNameTest() {
    CustomerEntityTestKit testKit = CustomerEntityTestKit.of(CustomerEntity::new);
    // EventSourcedResult<Empty> result = testKit.changeName(ChangeNameRequest.newBuilder()...build());
  }


  @Test
  public void changeAddressTest() {
    CustomerEntityTestKit testKit = CustomerEntityTestKit.of(CustomerEntity::new);
    // EventSourcedResult<Empty> result = testKit.changeAddress(ChangeAddressRequest.newBuilder()...build());
  }


  @Test
  public void getCustomerTest() {
    CustomerEntityTestKit testKit = CustomerEntityTestKit.of(CustomerEntity::new);
    // EventSourcedResult<Customer> result = testKit.getCustomer(GetCustomerRequest.newBuilder()...build());
  }

}