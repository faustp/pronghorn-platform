package com.pccw.ad.pronghorn.message.interfaces;

import java.util.List;

/**
 * Created by FaustineP on 12/05/2017.
 */
public interface Routable {

    void setRoute(List<String> route);

    List<String> getRoute();
}
