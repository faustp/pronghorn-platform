package com.faust.pronghorn.ws.controller;

import com.faust.pronghorn.model.profile.Service;
import com.faust.pronghorn.ws.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FaustineP on 23/05/2017.
 */
@RestController
@RequestMapping(path = "/profiles")
public class ServiceController {


    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(path = "/{name}/services/{serviceName}")
    public Service getServices(@PathVariable String name, @PathVariable String serviceName) {
        Service service = serviceRepository.findServiceByName(serviceName);
        return service;
    }
}
