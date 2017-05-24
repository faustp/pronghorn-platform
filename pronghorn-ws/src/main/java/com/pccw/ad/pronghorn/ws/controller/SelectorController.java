package com.pccw.ad.pronghorn.ws.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by FaustineP on 22/05/2017.
 */
@RestController
@RequestMapping(path = "/profiles")
public class SelectorController {

    @RequestMapping(path = "/{name}/services/{serviceName}/selectors", method = POST, produces = APPLICATION_JSON_VALUE)
    public void addSelector(@RequestBody HashMap<String, String> selectors) {
    }


}
