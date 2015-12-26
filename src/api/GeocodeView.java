package api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


//Api do Google Maps
@ManagedBean
public class GeocodeView {
     
    private MapModel geoModel;
    private String centerGeoMap = "41.850033, -87.6500523";
     
    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
    }
     
    //M�todo de integra��o com a API do Google, utilizando componentes da PrimeFaces
    public void onGeocode(GeocodeEvent event) throws InterruptedException {
        List<GeocodeResult> results = event.getResults();
         
        new Thread().sleep(500);
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }
     
    public MapModel getGeoModel() {
        return geoModel;
    }
 
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

}