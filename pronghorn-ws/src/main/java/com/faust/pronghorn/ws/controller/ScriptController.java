package com.faust.pronghorn.ws.controller;

import com.faust.pronghorn.model.tc.Script;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by FaustineP on 22/05/2017.
 */
@RestController
@RequestMapping(path = "/profiles")
public class ScriptController {


    @RequestMapping(path = "/{name}/tcs/{id}/scripts", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Script> getScripts() {

        return null;
    }

    @RequestMapping(path = "/{name}/tcs/{id}/scripts", method = POST, produces = APPLICATION_JSON_VALUE)
    public Script addScript(@RequestBody Script script) {
        return null;
    }

    @RequestMapping(path = "/{name}/tcs/{id}/scripts", method = PUT, produces = APPLICATION_JSON_VALUE)
    public Script updateScript(@RequestBody Script script) {
        return null;
    }
}
