package com.pccw.ad.pronghorn.ws.service;

import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.ws.reponse.ResponseMessage;
import com.pccw.ad.pronghorn.ws.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by FaustineP on 23/05/2017.
 */
@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ResponseMessage<Iterable<Profile>> getProfiles() {
        Iterable<Profile> profiles = profileRepository.findAll();
        return new ResponseMessage<>(HttpStatus.OK.value(), null, profiles);
    }

    public ResponseMessage<String> addProfile(Profile profile) {
        profileRepository.save(profile);
        return new ResponseMessage<>(HttpStatus.ACCEPTED.value(), null, null);
    }

    public ResponseMessage<Profile> getProfileByName(String name) {
        return new ResponseMessage<>(HttpStatus.OK.value(), null, profileRepository.findProfileByName(name));
    }
}
