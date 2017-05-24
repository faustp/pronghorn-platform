package com.pccw.ad.pronghorn.ws.repository;

import com.pccw.ad.pronghorn.model.profile.Profile;
import com.pccw.ad.pronghorn.model.profile.Service;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FaustineP on 19/05/2017.
 */
@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    Profile findProfileByName(String name);

    Profile findProfileByNameAndServicesName(String name, String serviceName);
}
