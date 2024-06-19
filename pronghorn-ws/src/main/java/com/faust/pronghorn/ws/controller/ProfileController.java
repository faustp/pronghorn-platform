package com.faust.pronghorn.ws.controller;

import com.faust.pronghorn.model.profile.Profile;

import com.faust.pronghorn.ws.reponse.ResponseMessage;
import com.faust.pronghorn.ws.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by FaustineP on 22/05/2017.
 */
@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {


    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseMessage<Iterable<Profile>> getProfiles() {
        return profileService.getProfiles();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseMessage<String> addProfile(@RequestBody Profile profile) {
        return profileService.addProfile(profile);
    }


    @RequestMapping(path = "/{name}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseMessage<Profile> getProfile(@PathVariable String name) {
        return profileService.getProfileByName(name);
    }

}
