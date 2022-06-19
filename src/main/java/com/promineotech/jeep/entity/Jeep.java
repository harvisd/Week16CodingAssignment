/**
 * 
 */
package com.promineotech.jeep.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harvi
 *
 */
@Data
@Builder/**
   * @param long1
   * @return
   */

@NoArgsConstructor
@AllArgsConstructor

public class Jeep {

  @JsonIgnore
  public Long getModelPK() {
    return modelPK;
  }
  
  private Long modelPK; 
  private JeepModel modelId;
  private String trimLevel;
  private int numDoors;
  private int wheelSize;
  private BigDecimal basePrice;
  
  }
