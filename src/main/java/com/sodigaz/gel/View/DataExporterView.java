/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

import com.sodigaz.gel.Service.CarService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.sodigaz.gel.Entity.Car;

/**
 *
 * @author issouf.ouedraogo
 */
@ManagedBean
public class DataExporterView implements Serializable {
     
    private List<Car> cars;
         
    @ManagedProperty("#{carService}")
    private CarService service;
     
    @PostConstruct
    public void init() {
        cars = service.createCars(100);
    }
 
    public List<Car> getCars() {
        return cars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
}
