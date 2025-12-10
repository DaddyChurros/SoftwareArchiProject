package fr.insa.ms.SecuriteRestaurant.model;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Historique {
	private final List<Alerte> alertes = new CopyOnWriteArrayList<>();
	private final AtomicLong idGenerator = new AtomicLong(1);
	
	public Alerte createAlerte(AlerteType type, AlerteRequest request) {
		Alerte alerte = new Alerte();
		alerte.setId(idGenerator.getAndIncrement());
		alerte.setType(type);
		alerte.setSourceService(request.getSourceService());
		alerte.setMessage(request.getMessage());
		alerte.setSeverity(request.getSeverity());
		alerte.setDate(LocalTime.now());
		
		triggerNotification(alerte); 
		alertes.add(alerte);
		return alerte;
	}
	
	public List<Alerte> getAllAlertes(){
		return alertes;
	}
	private void triggerNotification(Alerte alerte) {
		//actions sur alerte
		System.out.println("ALERTE "+ alerte.getSeverity()+ "-" + alerte.getMessage());
	}
}
