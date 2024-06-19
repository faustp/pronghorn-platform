package com.faust.pronghorn.ws.repository;

import com.faust.pronghorn.model.profile.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FaustineP on 19/05/2017.
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Profile findProfileByName(String name);

    Profile findProfileByNameAndServicesName(String name, String serviceName);
}
