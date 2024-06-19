package com.faust.pronghorn.ws.repository;

import com.faust.pronghorn.model.profile.Service;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FaustineP on 23/05/2017.
 */
@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {

    Service findServiceByName(String name);

}
