package com.neo.rest;

import com.neo.horizon.HorizonCoordinateRetriever;
import com.neo.model.Coordinate;
import com.neo.model.Neo;
import com.neo.service.NEOService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping(value = "/rest/neo")
public class NEOResource {
    @Resource
    private NEOService neoService;

    @Resource
    private HorizonCoordinateRetriever horizonCoordinateRetriever;

    @RequestMapping(value = "/{neoid}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Neo describeNeoById(@PathVariable(value="neoid") Integer neoid) throws IOException {
        if(neoid!=null){
            return neoService.findById(neoid);
        }
        return null;
    }
    /*
    a (AU)  	Semi-major axis of the orbit in AU
e  	Eccentricity of the orbit
i (deg)  	Inclination of the orbit with respect to the ecliptic plane and the equinox of J2000 (J2000-Ecliptic) in degrees
w (deg)  	Argument of perihelion (J2000-Ecliptic) in degrees
Node (deg)  	Longitude of the ascending node (J2000-Ecliptic) in degrees
M (deg)  	Mean anomoly at epoch in degrees
q (AU)  	Perihelion distance of the orbit in AU
Q (AU)  	Aphelion distance of the orbit in AU
P (yr)  	Orbital period in Julian years
H (mag)  	Absolute V-magnitude
MOID (AU)  	Minimum orbit intersection distance (the minimum distance between the osculating orbits of the NEO and the Earth)
ref  	Orbital solution reference
class  	Object classification: NEA="Near-Earth Asteroid", AMO="Amor", APO="Apollo", ATE="Aten", or IEO="Interior Earth Object". A trailing "*" indicates the object is also a potentially hazardous asteroid. (see definitions)
     */

    /*
    JDTDB    Epoch Julian Date, Barycentric Dynamical Time
      X      x-component of position vector (AU)
      Y      y-component of position vector (AU)
      Z      z-component of position vector (AU)
      VX     x-component of velocity vector (AU/day)
      VY     y-component of velocity vector (AU/day)
      VZ     z-component of velocity vector (AU/day)
      LT     One-way down-leg Newtonian light-time (day)
      RG     Range; distance from coordinate center (AU)
      RR     Range-rate; radial velocity wrt coord. center (AU/day)
     */
    @RequestMapping(value = "/{neoid}/positions", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Coordinate getNeoPositionByIdAndTime(@PathVariable(value="neoid") Integer neoid,
                                             @RequestParam(value = "time", required = false) String time,
                                             @RequestParam(value = "refplane", required = false) String referencePlane)
            throws IOException {
        DateTime instant = (time==null)?DateTime.now():DateTimeFormat.forPattern("yyyy-MMM-dd-hh:mm:ss").parseDateTime(time);
        referencePlane = (referencePlane==null)?"frame":referencePlane.toLowerCase();
        return horizonCoordinateRetriever.coordinates(neoid, instant, referencePlane);
    }
}
