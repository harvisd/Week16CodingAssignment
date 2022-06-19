/**
 * 
 */
package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

/**
 * @author harvi
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0_Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1_Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class CreateOrderTest {

  @Autowired
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;

  @Test
  void testCreateOrderReturnsSuccess201() {
    //Given: 
    String body = createOrderBody();

    String uri = String.format("http://localhost:%d/orders", serverPort);
  
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

    ResponseEntity<Order> response =
        restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);
    
    //Then:
   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
   assertThat(response.getBody()).isNotNull();
    
    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("ATTAWAY_HECKTOR");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getOptions()).hasSize(5);
  }

  protected String createOrderBody() {

    //@formatter:off
    return "{\n"
    + "  \"customer\":\"ATTAWAY_HECKTOR\",\n"
    + "  \"model\":\"WRANGLER\",\n"
    + "  \"trim\":\"Sport\",\n"
    + "  \"doors\":4,\n"
    + "  \"color\":\"EXT_SANGRIA\",\n"
    + "  \"engine\":\"3_6_HYBRID\",\n"
    + "  \"tire\":\"37_YOKOHAMA\",\n"
    + "  \"options\":[\n"
    + "    \"DOOR_BODY_REAR\",\n"
    + "    \"EXT_MOPAR_COLOR_FLARE\",\n"
    + "    \"EXT_TARA_CARRIER\",\n"
    + "    \"INT_MOPAR_RADIO\",\n"
    + "    \"INT_QUAD_LINER\"\n"
    + "  ]\n"
    + "}";
    
//    return "{\n"
//    + "  \"customer\":\"ATTAWAY_HECKTOR\",\n"
//    + "  \"model\":\"WRANGLER\",\n"
//    + "  \"trim\":\"Sport\",\n"
//    + "  \"doors\":4,\n"
//    + "  \"options\":[\n"
//    + "   \"WHEEL_TERA_NOMAD\",\n"
//    + "   \"STOR_BESTOP_ORGANIZER\"\n"
//    + "      ]\n"
//    + "}";
//@formatter:on
  }
}
