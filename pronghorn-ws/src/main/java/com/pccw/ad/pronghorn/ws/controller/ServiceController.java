package com.pccw.ad.pronghorn.ws.controller;

import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.profile.Service;
import com.pccw.ad.pronghorn.ws.repository.ProfileRepository;
import com.pccw.ad.pronghorn.ws.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;

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
