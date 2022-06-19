/**
 * 
 */
package com.promineotech.jeep.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author harvi
 *
 */
@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {

  @Autowired
  private JeepSalesDao jeepSalesDao;

  public List<Jeep> fetchJeeps(JeepModel model, String trim){
    log.info("The service was called for model={} and the trim={}", model, trim);
    
    List<Jeep> jeeps = jeepSalesDao.fetchJeeps(model, trim);
    
    return jeeps;
  }
  
  
}
