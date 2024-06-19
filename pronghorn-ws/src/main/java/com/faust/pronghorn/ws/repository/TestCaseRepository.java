package com.faust.pronghorn.ws.repository;

import com.faust.pronghorn.model.tc.TestCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by FaustineP on 03/04/2017.
 */
@Repository
public interface TestCaseRepository extends CrudRepository<TestCase, Long> {

    TestCase findByIdentifier(String identifier);

}
